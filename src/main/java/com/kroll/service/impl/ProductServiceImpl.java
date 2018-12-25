package com.kroll.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kroll.dao.ProductDAO;
import com.kroll.domain.Product;
import com.kroll.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired 
	private ProductDAO productDAO;
	
	
	@Override
	public Product create(Product product) {
		return productDAO.save(product);
	}

	@Override
	public Product update(Product product) {
		return productDAO.save(product);
	}

	@Override
	public Product findById(long id) {
		return productDAO.findById(id).get();
	}

	@Override
	public Collection<Product> findAllMasterProducts(long companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Product> findAllItems(long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Product> findAllProducts(long companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Product> findProductsByName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Product> findItemsByName(String itemName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Product> findAll() {
		return productDAO.findAll();
	}

}
