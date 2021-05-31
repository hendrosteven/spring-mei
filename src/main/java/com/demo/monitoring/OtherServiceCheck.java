package com.demo.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class OtherServiceCheck implements HealthIndicator{

    private boolean status = true;

    @Override
    public Health health() {
        if(!isOtherServiceHealthGood()){
            return Health.down()
            .withDetail("OtherService", "Other Service is not available")
            .build();
        }
        return Health.up()
            .withDetail("OtherService", "Other Service is running")
            .build();
    }

    public boolean isOtherServiceHealthGood(){
        //logic untuk manipulasi status
        return status = !status;
    }
    
}
