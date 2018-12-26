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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Product> findAllMasterProducts(long companyId) {
		return productDAO.findAllMasterProducts(companyId);
	}

	@Override
	public Collection<Product> findAllItems(long productId) {
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
		// TODO Auto-generated method stub
		return null;
	}

}
