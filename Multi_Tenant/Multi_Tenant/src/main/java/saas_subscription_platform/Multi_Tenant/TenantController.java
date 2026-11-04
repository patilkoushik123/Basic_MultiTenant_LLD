package saas_subscription_platform.Multi_Tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantRegistry tenantRegistry;

    @Autowired
    public TenantController(TenantRegistry tenantRegistry) {
        this.tenantRegistry = tenantRegistry;
    }

    // Endpoint to fetch tenant settings: GET http://localhost:8080/api/tenants/tenant-alpha
    @GetMapping("/{tenantId}")
    public TenantSettings getTenantSettings(@PathVariable String tenantId) {
        return tenantRegistry.getSettings(tenantId);
    }

    // Endpoint to register/save a new tenant: POST http://localhost:8080/api/tenants/register?tenantId=tenant-gamma&currency=USD&analytics=true
    @PostMapping("/register")
    public String registerTenant(
            @RequestParam String tenantId, 
            @RequestParam String currency, 
            @RequestParam boolean analytics) {
        
        TenantSettings settings = new TenantSettings(currency, analytics);
        tenantRegistry.registerTenant(tenantId, settings);
        
        return "Successfully registered tenant: " + tenantId;
    }
}