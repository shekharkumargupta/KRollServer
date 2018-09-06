/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kroll.constants;

/**
 *
 * @author shekharkumar
 */
public class AppEnum {

    public static enum UserType {
        CUSTOMER("Customer", 0),
        MANAGER("Manager", 1),
        DELIVERY_BOY("Delivery Boy", 2),
        BUSINESS_ADMIN("Business Admin", 3),
        APPLICATION_ADDMIN("Application Admin",4);

        private final int index;
        private final String name;
        
        UserType(String name, int index) {
            this.name = name;
            this.index = index;
        }
        
        public String getName(){
            return this.name;
        }

        public int getIndex() {
            return this.index;
        }
    }
    
    public static enum ShoppingCartStatus {
        TRANSIENT("Transient", 0),
        PERSISTENCE("Persistence", 1),
        DETACHED("Detached", 2);

        private final int index;
        private final String name;
        
        ShoppingCartStatus(String name, int index) {
            this.name = name;
            this.index = index;
        }
        
        public String getName(){
            return this.name;
        }

        public int getIndex() {
            return this.index;
        }
    }
    
    public static enum OrderStatus {
    	GENERATED("Generated", 0),
        RECEIVED("Received", 1),
        PROCESSING("Processing", 2),
        DISPATCHED("Dispatched", 3),
        ON_THE_WAY("OnTheWay", 4),
        DELIVERED("Delivered", 5),
        RETURNED("Returned", 6);

        private final int index;
        private final String name;
        
        OrderStatus(String name, int index) {
            this.name = name;
            this.index = index;
        }
        
        public String getName(){
            return this.name;
        }

        public int getIndex() {
            return this.index;
        }
    }
    

    public static enum MessageType {
    	CUSTOMER_ORDER("Order", 0);

        private final int index;
        private final String name;
        
        MessageType(String name, int index) {
            this.name = name;
            this.index = index;
        }
        
        public String getName(){
            return this.name;
        }

        public int getIndex() {
            return this.index;
        }
    }
    
    
}
