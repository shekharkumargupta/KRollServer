package com.kroll.web.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kroll.domain.Product;
import com.kroll.service.ProductService;

@RestController("/product")
public class ProductController {


	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public String test() {
		return "Test success!";
	}

	@RequestMapping(value = "findAllMasterProducts/{companyId}", method = RequestMethod.GET, produces = "application/json")
	public Collection<Product> findAllMasterProducts(@PathVariable("companyId") long companyId) {
		Collection<Product> products = productService.findAllMasterProducts(companyId);
		return products;
	}

}
