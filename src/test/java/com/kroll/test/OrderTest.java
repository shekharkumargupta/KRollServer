package com.kroll.test;

import org.hibernate.Session;

import com.kroll.constants.AppEnum;
import com.kroll.domain.Login;
import com.kroll.domain.CustomerOrder;
import com.kroll.domain.ShoppingCart;
import com.kroll.util.HibernateUtil;

public class OrderTest {

    public static void makeOrder(long cartId) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        ShoppingCart cart = (ShoppingCart) session.createQuery("select s from ShoppingCart s where s.id = ?")
        		.setParameter(0, cartId)
                .uniqueResult();
        Login customer = cart.getCustomer();
        System.out.println(cart);

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setCart(cart);
        customerOrder.setStatus(AppEnum.OrderStatus.GENERATED);
        session.save(customerOrder);

        cart.setStatus(AppEnum.ShoppingCartStatus.DETACHED);
        session.update(cart);

        customer.getPerson().setAddress(customerOrder.getAddress());
        customer.getPerson().setFullName(customerOrder.getReceiverName());
        customer.getPerson().setEmail(customerOrder.getEmail());
        session.update(customer);
        session.getTransaction().commit();
        session.close();

    }

    public static void main(String args[]) {
        makeOrder(57);
        makeOrder(61);
    }
}
