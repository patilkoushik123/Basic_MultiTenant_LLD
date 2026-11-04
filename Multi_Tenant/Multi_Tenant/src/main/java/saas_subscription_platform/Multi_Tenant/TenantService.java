package saas_subscription_platform.Multi_Tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService {

    private final TenantRegistry tenantRegistry;

    @Autowired
    public TenantService(TenantRegistry tenantRegistry) {
        this.tenantRegistry = tenantRegistry;
    }

    public void processTenantRequest(String tenantId) {
        TenantSettings settings = tenantRegistry.getSettings(tenantId);
        System.out.println("Processing for Tenant: " + tenantId);
        System.out.println("Currency: " + settings.getCurrency());
        System.out.println("Analytics Enabled: " + settings.isAnalyticsEnabled());
    }
}