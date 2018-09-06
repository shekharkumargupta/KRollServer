package com.kroll.web.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kroll.domain.ShoppingCart;
import com.kroll.service.ShoppingCartService;

@RestController
@RequestMapping(value = "/cart")
public class ShoppingCartController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private ShoppingCartService cartService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String test() {
        return "Test success!";
    }

    @RequestMapping(value = "findByCartId/{cartId}", method = RequestMethod.GET, produces = "application/json")
    public ShoppingCart findByCartId(@PathVariable("cartId") long cartId) {
        ShoppingCart cart = cartService.findById(cartId);
        logger.info("findByCartId/{cartId}: " + cart);
        return cart;
    }

    @RequestMapping(value = "findByLoginId/{loginId}", method = RequestMethod.GET, produces = "application/json")
    public ShoppingCart findByCustomerLoginId(@PathVariable("loginId") String customerLoginId) {
        ShoppingCart cart = cartService.findByCustomerLoginId(customerLoginId);
        logger.info("findByLoginId/{loginId}: " + cart);
        return cart;
    }

    @RequestMapping(value = "addItemToCart/{loginId}/{itemId}/{qty}", method = RequestMethod.POST, produces = "application/json")
    public ShoppingCart addItemToCart(@PathVariable("loginId") String loginId, @PathVariable("itemId") long itemId,
            @PathVariable("qty") int qty) {
        ShoppingCart cart = cartService.addItem(loginId, itemId, qty);
        System.out.println("addItemToCart/{loginId}/{itemId}/{qty}: " + cart);
        logger.info("addItemToCart/{loginId}/{itemId}/{qty}: " + cart);
        return cart;
    }

}
