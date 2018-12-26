package com.kroll.web.controller;

import com.kroll.constants.ActionConstants;
import com.kroll.domain.CustomerOrder;
import com.kroll.domain.Envelope;
import com.kroll.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private final Logger logger = Logger.getLogger(getClass().getName());

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String test() {
        return "Test success!";
    }

    @RequestMapping(value = "findByCartId/{orderId}", method = RequestMethod.GET, produces = "application/json")
    public CustomerOrder findByCartId(@PathVariable("orderId") long orderId) {
        CustomerOrder customerOrder = orderService.findById(orderId);
        logger.info("findByCartId/{orderId}: " + customerOrder);
        return customerOrder;
    }

    @RequestMapping(value = "findByLoginId/{loginId}", method = RequestMethod.GET, produces = "application/json")
    public CustomerOrder findByCustomerLoginId(@PathVariable("loginId") String customerLoginId) {
        CustomerOrder customerOrder = null;
        // todo CustomerOrder order = orderService.findByCustomerLoginId(customerLoginId);
        logger.info("findByLoginId/{loginId}: " + customerOrder);
        return customerOrder;
    }

    @RequestMapping(value = "makeOrder/{cartId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public CustomerOrder makeOrder(@PathVariable("cartId") long cartId, @RequestBody CustomerOrder customerOrder) {

        customerOrder = orderService.create(customerOrder);

        Envelope envelope = new Envelope();
        envelope.setMessageType(ActionConstants.ORDER_GENERATED);
        envelope.setSentAt(Calendar.getInstance().getTime());
        envelope.setSenderLoginId(customerOrder.getCustomer().getLoginId());
        envelope.setCustomerOrder(customerOrder);

        System.out.println("makeOrder/{cartId}: " + customerOrder);
        logger.info("makeOrder/{cartId}: " + customerOrder);
        return customerOrder;
    }
}
