package com.kroll.web.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kroll.domain.Product;
import com.kroll.service.ProductService;

@RestController("/product")
public class ProductController {

	
	@Autowired
	private ProductService productService;


	@RequestMapping
	public @ResponseBody  Collection<Product> prepareTestData(){
		throw new UnsupportedOperationException("Yet to be implemented");
	}

	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Collection<Product> findAll() {
		List<Product> products = new ArrayList<>(productService.findAll()) ;
		return products;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Product create(@RequestBody Product product) {
		return productService.create(product);
	}


}
