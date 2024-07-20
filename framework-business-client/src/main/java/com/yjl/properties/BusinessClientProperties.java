package com.yjl.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/7/20 16:47
 */
@Data
@Component
@ConfigurationProperties(prefix = "business.client")
public class BusinessClientProperties {
    private String url;
    private String appSecret;
    private String feignLogLevel;
}
