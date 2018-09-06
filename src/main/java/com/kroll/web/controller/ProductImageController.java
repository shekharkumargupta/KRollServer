package com.kroll.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kroll.domain.ProductImage;
import com.kroll.service.ProductImageService;

@RestController
@RequestMapping(value = "/productImage")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String test() {
        return "Test success!";
    }

    @RequestMapping(value = "findByProductId/{id}", method = RequestMethod.GET, produces = { "image/jpg", "image/png" })
    public void findByImageId(@PathVariable(value = "id") final Long id, HttpServletResponse response) {

        /*
         * return Response.ok().entity(new StreamingOutput() {
         * 
         * @Override public void write(OutputStream output) throws IOException,
         * WebApplicationException { ProductImage image = new ProductImage();
         * image = productImageService.findByProductId(id); byte[] content =
         * image.getImage(); output.write(content); output.flush(); }
         * }).build();
         */

        ProductImage image = new ProductImage();
        image = productImageService.findByProductId(id);
        byte[] content = image.getImage();
        String name = "EmployeeImage";
        response.setContentType("image/jpeg");
        response.setContentLength(content.length);

        response.setHeader("Content-Disposition", "inline; filename=\"" + name + "\"");

        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            input = new BufferedInputStream(new ByteArrayInputStream(content));
            output = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[8192];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } catch (IOException e) {
            System.out.println("There are errors in reading/writing image stream " + e.getMessage());
        } finally {
            if (output != null)
                try {
                    output.close();
                } catch (IOException ignore) {
                }
            if (input != null)
                try {
                    input.close();
                } catch (IOException ignore) {
                }
        }
        

    }

}
