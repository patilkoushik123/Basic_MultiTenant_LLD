package saas_subscription_platform.Multi_Tenant;

import org.springframework.stereotype.Component;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
@Component
@ConfigurationProperties(prefix = "billing")
@Data
public class Config {

	private String defaultCurrency;
}