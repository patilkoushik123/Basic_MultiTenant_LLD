package saas_subscription_platform.Multi_Tenant;

import org.springframework.stereotype.Service;

@Service
public class BillingService {

    private final Config config;

    // Spring automatically injects the singleton Config bean here
    public BillingService(Config config) {
        this.config = config;
    }

    public void displayCurrency() {
        System.out.println("Default Billing Currency: " + config.getDefaultCurrency());
    }
}