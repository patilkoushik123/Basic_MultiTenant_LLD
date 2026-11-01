package saas_subscription_platform.Multi_Tenant;

import org.springframework.stereotype.Component;

@Component
public class Config {

    public String getDefaultCurrency() {
        return "INR";
    }
}