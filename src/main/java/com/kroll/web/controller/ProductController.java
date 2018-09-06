package com.kroll.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kroll.domain.Product;
import com.kroll.service.ProductService;

@RestController
@RequestMapping(value = "/product")
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
