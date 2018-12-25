package com.kroll.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ProductImage implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    private long id;
    @Lob
    @Column(length = 20971520)
    private byte[] image;
    private String description;
    private long productId;

    public ProductImage(){}
    
    public ProductImage(String description){
    	this.description = description;
    }
    
	public long getId() {
    return id;
    }

    public void setId(long id) {
    this.id = id;
    }

    public byte[] getImage() {
    return image;
    }

    public void setImage(byte[] image) {
    this.image = image;
    }

    public String getDescription() {
    return description;
    }

    public void setDescription(String description) {
    this.description = description;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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
        ProductImage other = (ProductImage) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ProductImage [id=" + id + ", productId=" + productId + "]";
    }

   
}
