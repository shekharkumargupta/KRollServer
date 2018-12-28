package com.kroll.seed;

import com.kroll.constants.AppEnum;
import com.kroll.domain.Login;
import com.kroll.domain.OrderItem;
import com.kroll.domain.Product;
import com.kroll.domain.ShoppingCart;
import com.kroll.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SeedShoppingCart {

    private static void createCart(String customerLoginId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("Select l from Login l where l.loginId = '" + customerLoginId + "'");
        Login customer = (Login) query.uniqueResult();
        System.out.println("Customer: "+customer.getId());


        query = session.createQuery("Select p from Product p where" + " p.name like ? and p.item =?")
                .setParameter(0, "Non Veg" + "%").setParameter(1, false);
        @SuppressWarnings("unchecked")
        List<Product> productList = (List<Product>) query.list();
        System.out.println("productList: size: "+productList.size());

        Product category = productList.get(0);

        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for(Product product: category.getItems()){
            OrderItem item = new OrderItem();
            item.setItem(product);
            item.setQty(1);
            session.save(item);
            orderItems.add(item);
        }

        System.out.println("orderItems: size: "+orderItems.size());

        ShoppingCart cart = new ShoppingCart();
        cart.setOrderItems(orderItems);
        cart.setCreatedAt(Calendar.getInstance().getTime());
        cart.setCustomer(customer);
        cart.setStatus(AppEnum.ShoppingCartStatus.PERSISTENCE);
        session.save(cart);
        System.out.println("cart: "+cart.getId());
        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args)  {
        createCart("8802736199");
        createCart("9868351070");
    }
}
