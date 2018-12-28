package com.kroll.web.controller;

import com.kroll.domain.Product;
import com.kroll.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/product")
public class ProductController {


	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public String test() {
		return "Test success!";
	}


	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product create(@RequestBody Product product) {
		productService.create(product);
		return product;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product update(@RequestBody Product product) {
		productService.update(product);
		return product;
	}

	@RequestMapping(value = "findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product findById(@PathVariable(value = "id") long id) {
		return productService.findById(id);
	}

	@RequestMapping(value = "findAllMasterProducts/{companyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Product> findAllMasterProducts(@PathVariable("companyId") long companyId) {
		Collection<Product> products = productService.findAllMasterProducts(companyId);
		return products;
	}

	@RequestMapping(value = "findAllItems/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Product> findAllItems(@PathVariable(value = "productId") long productId) {
		return productService.findAllItems(productId);
	}

	@RequestMapping(value = "findAllProducts/{companyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Product> findAllProducts(@PathVariable(value = "companyId") long companyId) {
		return productService.findAllProducts(companyId);
	}

	@RequestMapping(value = "findProductsByName/{productName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Product> findProductsByName(@PathVariable(value = "productName") String productName) {
		return productService.findProductsByName(productName);
	}

	@RequestMapping(value = "findItemsByName/{itemName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Product> findItemsByName(String itemName) {
		return productService.findItemsByName(itemName);
	}

	@RequestMapping(value = "findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Product> findAll(){
		Collection<Product> products = productService.findAll();
		return products;
	}

}
