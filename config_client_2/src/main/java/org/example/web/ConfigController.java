package org.example.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

    @Value("${service.message}")
    private String serviceMessage;

    @Value("${shared.message}")
    private String sharedMessage;

    @GetMapping("/config")
    public String getConfig() {
        return "Service Message: [" + serviceMessage + "]<br>" +
                "Shared Message: [" + sharedMessage + "]";
    }
}
