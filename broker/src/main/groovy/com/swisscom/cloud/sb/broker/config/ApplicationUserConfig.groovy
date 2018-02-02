package com.swisscom.cloud.sb.broker.config

import groovy.transform.CompileStatic
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@CompileStatic
@Configuration
@ConfigurationProperties(prefix = 'com.swisscom.cloud.sb.broker.security')
class ApplicationUserConfig {
    List<GuidUserConfig> platformUsers

    @Override
    String toString() {
        return "ApplicationUserConfig{" +
                "platformUsers=" + platformUsers +
                "}"
    }
}
