package com.devinhouse.ecommerce.service;

import java.util.List;
import com.devinhouse.ecommerce.entity.Product;
import com.devinhouse.ecommerce.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Integer id) throws ObjectNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto inexistente."));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) throws Exception {
        Product databaseProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Produto inexistente."));
        if (!product.validateFields())
            throw new Exception("Fields not valid.");
        databaseProduct.updateFields(product);
        return productRepository.save(databaseProduct);
    }

    public Product patch(Product product) throws Exception {
        Product databaseProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Produto inexistente."));
        databaseProduct.updateFields(product);
        return productRepository.save(databaseProduct);
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

}
