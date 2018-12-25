package com.kroll.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.kroll.constants.AppEnum;



@Entity
@XmlRootElement
public class CustomerOrder {

    @Id
    @GeneratedValue
    private long id;
    private String receiverName;
    private String contactNumber;
    private String email;

    @Embedded
    private Address address;

    @OneToOne
    private ShoppingCart cart;
    
    @Enumerated(EnumType.STRING)
    private AppEnum.OrderStatus status;

    @ManyToOne
    private Login customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public AppEnum.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(AppEnum.OrderStatus status) {
        this.status = status;
    }

    public Login getCustomer() {
        return customer;
    }

    public void setCustomer(Login customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerOrder other = (CustomerOrder) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CustomerOrder [id=" + id + ", receiverName=" + receiverName + ", address=" + address
                + ", contactNumber=" + contactNumber + ", email=" + email + ", cart=" + cart + ", status=" + status
                + ", customer=" + customer + "]";
    }

}
