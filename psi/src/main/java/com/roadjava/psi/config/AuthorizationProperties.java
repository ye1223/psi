package com.roadjava.psi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 权限url配置
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Configuration
@ConfigurationProperties(prefix = "auth")
@Data
public class AuthorizationProperties {
    /**
     * 忽略权限拦截器的url
     */
    private List<String> ignoreHandlers;
}
