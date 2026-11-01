package saas_subscription_platform.Multi_Tenant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    private final BillingService billingService;

    // Spring injects the BillingService here
    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping("/currency")
    public String getCurrency() {
        // This will print to your Eclipse/Spring console
        billingService.displayCurrency();
        
        // This will return the response back to whoever calls the API
        return "Current Default Currency is printed in the console!";
    }
}