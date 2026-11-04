package saas_subscription_platform.Multi_Tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TenantRegistry acts as a centralized, Singleton-scoped component managed by Spring.
 * 
 * Low-Level Design (LLD) Architecture:
 * - Singleton Scope: Spring guarantees a single instance of this class exists in memory 
 *   per Spring application context, providing a unified access point for all tenant configuration lookups.
 * - Stateless Gateway Pattern: Unlike traditional in-memory singletons that store state 
 *   in a local HashMap, this registry delegates state management to PostgreSQL. This 
 *   ensures absolute data consistency and safety when scaling horizontally across multiple server instances.
 */
@Component
public class TenantRegistry {

    private final TenantRepository tenantRepository;

    /**
     * Constructor-based dependency injection for the Spring Data JPA repository.
     * Spring automatically handles wiring the single shared instance of TenantRepository.
     */
    @Autowired
    public TenantRegistry(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    /**
     * Retrieves tenant configurations from the database. 
     * Because this component is a Singleton, any service calling this method shares 
     * the exact same instance while safely fetching live, persistent data on demand.
     * 
     * @param tenantId The unique identifier of the tenant
     * @return TenantSettings containing currency and analytics flags (or system fallback defaults)
     */
    public TenantSettings getSettings(String tenantId) {
        // Query the central PostgreSQL database; fallback to default if the tenant is not found
        TenantEntity entity = tenantRepository.findById(tenantId).orElse(null);

        if (entity == null) {
            return new TenantSettings("INR", false); // Fallback default configuration
        }

        return new TenantSettings(entity.getCurrency(), entity.isAnalyticsEnabled());
    }

    /**
     * Registers or updates tenant settings in the database.
     * Leverages Spring Data JPA's primary key mapping for seamless upsert behavior.
     * 
     * @param tenantId The unique tenant identifier (acts as the Primary Key)
     * @param settings The configuration settings to store or overwrite
     */
    public void registerTenant(String tenantId, TenantSettings settings) {
        TenantEntity entity = new TenantEntity();
        entity.setTenantId(tenantId);
        entity.setCurrency(settings.getCurrency());
        entity.setAnalyticsEnabled(settings.isAnalyticsEnabled());
        
        // Performs an Upsert: Inserts a new row if tenantId is new, or updates it if it already exists
        tenantRepository.save(entity);
    }
}