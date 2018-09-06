package com.kroll.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Product {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	@Column(length = 600)
	private String description;
	private float price;
	private boolean item;
	private boolean master;
	private boolean available;
	
	@OneToMany(fetch = FetchType.LAZY)
    private List<ProductImage> images;

	@ManyToOne
	private Company company;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Product> items;
	
	
	@Transient
	private boolean selected;

	
	public Product() {
	}

	public Product(String name, boolean isItem, float price, boolean master, boolean available, Company company) {
		this.name = name;
		this.item = isItem;
		this.price = price;
		this.master = master;
		this.available = available;
		this.company = company;
	}

	public void addItem(Product product) {
		if (items != null) {
			items.add(product);
		} else {
			items = new ArrayList<Product>();
			items.add(product);
		}
	}
	
	public void addImage(ProductImage productImage){
	    if(images != null){
	        this.images.add(productImage);
	    }else{
	        images = new ArrayList<ProductImage>();
	        images.add(productImage);
	    }
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}

	public boolean isItem() {
		return item;
	}

	public void setItem(boolean item) {
		this.item = item;
	}

	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", item="
                + item + ", master=" + master + ", available=" + available + ", company=" + company + ", items=" + items
                + "]";
    }

  

    
	

}
