package com.kroll.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.kroll.constants.AppEnum;

@Entity
@XmlRootElement
public class ShoppingCart {

    @Id
    @GeneratedValue
    private long id;
    @OneToMany(fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;
    private AppEnum.ShoppingCartStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    private double total;

    @ManyToOne
    private Login customer;

    public void addItem(OrderItem item) {
        if (orderItems != null) {
            orderItems.add(item);
        } else {
            orderItems = new ArrayList<OrderItem>();
            orderItems.add(item);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public AppEnum.ShoppingCartStatus getStatus() {
        return status;
    }

    public void setStatus(AppEnum.ShoppingCartStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Login getCustomer() {
        return customer;
    }

    public void setCustomer(Login customer) {
        this.customer = customer;
    }
    
    public double getTotal() {
    	double total = 0;
    	for(OrderItem item: orderItems){
    		total = total + (item.getTotalAmount()) ;
    	}
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
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
        ShoppingCart other = (ShoppingCart) obj;
        if (id != other.id)
            return false;
        return true;
    }

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", orderItems=" + orderItems + ", status=" + status + ", createdAt="
				+ createdAt + ", total=" + total + ", customer=" + customer + "]";
	}

    
}
