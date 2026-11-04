package saas_subscription_platform.Multi_Tenant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TenantSettings {
    private String currency;
    private boolean analyticsEnabled;
}