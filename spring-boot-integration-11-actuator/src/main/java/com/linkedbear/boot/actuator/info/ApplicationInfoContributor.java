package com.linkedbear.boot.actuator.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApplicationInfoContributor implements InfoContributor {
    
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("appName", "spring-boot-integration-11-actuator-contributor");
        Map<String, Object> data = new HashMap<>();
        data.put("version", "1.0.0");
        builder.withDetails(data);
    }
}
