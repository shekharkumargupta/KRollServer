package com.kroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kroll.dao.ProductImageDAO;
import com.kroll.domain.ProductImage;
import com.kroll.service.ProductImageService;

@Service
@Transactional
public class ProductImageServiceImpl implements ProductImageService{

    @Autowired
    private ProductImageDAO productImageDAO;
    
    @Override
    public ProductImage create(ProductImage image) {
        return productImageDAO.create(image);
    }

    @Override
    public ProductImage update(ProductImage image) {
        return productImageDAO.update(image);
    }

    @Override
    public ProductImage findById(long id) {
        return productImageDAO.findById(id);
    }

    @Override
    public ProductImage findByProductId(long id) {
        return productImageDAO.findByProductId(id);
    }

    @Override
    public List<ProductImage> findAll() {
        return productImageDAO.findAll();
    }

    //@Autowired
    //private ProductImageService productImageDAO;

    /*
    public Response findByImageId(final Long id, HttpServletResponse response)
            throws IOException {

        return Response.ok().entity(new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                ProductImage image = new ProductImage();
                image = productImageDAO.findByProductId(id);
                byte[] content = image.getImage();
                output.write(content);
                output.flush();
            }
        }).build();
    }
    */
}
