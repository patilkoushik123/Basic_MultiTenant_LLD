package saas_subscription_platform.Multi_Tenant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tenant_configs")
@Data
public class TenantEntity {

    @Id
    private String tenantId;
    
    private String currency;
    private boolean analyticsEnabled;
}