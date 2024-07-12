package com.example.payment.Model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "NOTIFICATION")
public interface NotificationServiceClient {
    @RequestMapping(method = RequestMethod.POST, value = "/notifications")
    void createNotification(@RequestBody NotificationDTO notification);
}
