package com.kroll.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kroll.dao.ProductDAO;
import com.kroll.domain.Product;
import com.kroll.service.ProductService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDAO productDAO;

	public String test() {
		return "Success";
	}

	@Override
	public Product create(Product product) {
		productDAO.save(product);
		return product;
	}

	@Override
	public Product update(Product product) {
		productDAO.save(product);
		return product;
	}

	@Override
	public Product findById(long id) {
		return productDAO.findById(id).get();
	}

	public Collection<Product> findAllMasterProducts(long companyId) {
		return productDAO.findAllMasterProducts(companyId);
	}

	@Override
	public Collection<Product> findAllItems(long productId) {
		return productDAO.findAllItems(productId);
	}

	@Override
	public Collection<Product> findAllProducts(long companyId) {
		return productDAO.findAllProducts(companyId);
	}

	@Override
	public Collection<Product> findProductsByName(String productName) {
		return productDAO.findProductsByName(productName);
	}

	@Override
	public Collection<Product> findItemsByName(String itemName) {
		return productDAO.findItemsByName(itemName);
	}

	@Override
	public Collection<Product> findAll() {
		return productDAO.findAll();
	}

}
