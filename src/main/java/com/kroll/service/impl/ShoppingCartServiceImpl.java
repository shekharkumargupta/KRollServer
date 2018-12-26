package com.kroll.service.impl;

import com.kroll.constants.AppEnum;
import com.kroll.dao.LoginDAO;
import com.kroll.dao.OrderItemDAO;
import com.kroll.dao.ProductDAO;
import com.kroll.dao.ShoppingCartDAO;
import com.kroll.domain.Login;
import com.kroll.domain.OrderItem;
import com.kroll.domain.Product;
import com.kroll.domain.ShoppingCart;
import com.kroll.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDAO shoppingCartDAO;
    @Autowired
    private OrderItemDAO orderItemDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private LoginDAO loginDAO;

    @Override
    public ShoppingCart create(ShoppingCart cart) {
        return shoppingCartDAO.save(cart);
    }

    @Override
    public ShoppingCart update(ShoppingCart cart) {
        return shoppingCartDAO.save(cart);
    }

    @Override
    public ShoppingCart remove(ShoppingCart cart) {
        shoppingCartDAO.delete(cart);
        return cart;
    }

    @Override
    public ShoppingCart findById(long shoppingCartId) {
        return shoppingCartDAO.findById(shoppingCartId).get();
    }

    @Override
    public ShoppingCart findByCustomerLoginId(String customerLoginId) {
        return shoppingCartDAO.findByCustomerLoginId(customerLoginId);
    }

    @Override
    public List<ShoppingCart> findAllCartByCustomerLoginId(String customerLoginId) {
        return shoppingCartDAO.findAllCartByCustomerLoginId(customerLoginId);
    }

    @Override
    public ShoppingCart findByOrderId(String orderId) {
        return shoppingCartDAO.findByOrderId(orderId);
    }

    @Override
    public ShoppingCart addItem(String loginId, long itemId, int qty) {
        Product item = productDAO.findById(itemId).get();
        ShoppingCart cart = shoppingCartDAO.findByCustomerLoginId(loginId);
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setQty(qty);

        if (cart != null) {
            orderItemDAO.save(orderItem);
            cart.addItem(orderItem);
            shoppingCartDAO.save(cart);
        } else {
            Login customer = loginDAO.findByLoginId(loginId);
            cart = new ShoppingCart();
            cart.setStatus(AppEnum.ShoppingCartStatus.PERSISTENCE);
            cart.setCustomer(customer);
            orderItemDAO.save(orderItem);
            cart.addItem(orderItem);
            shoppingCartDAO.save(cart);
        }
        return cart;
    }
}
