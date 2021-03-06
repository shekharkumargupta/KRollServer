package com.kroll.web.controller;

import com.kroll.domain.CustomerOrder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @RequestMapping(value = "sendOrderToManager/{loginId}/{orderId}", method = RequestMethod.GET, produces = "application/json")
    public CustomerOrder sendOrderToManager(
            @PathVariable("loginId") String loginId,
            @PathVariable("orderId") long orderId) {

        return new CustomerOrder();
    }
}
