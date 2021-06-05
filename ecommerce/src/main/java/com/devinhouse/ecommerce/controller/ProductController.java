package com.devinhouse.ecommerce.controller;

import java.util.List;

import com.devinhouse.ecommerce.dto.ProductDTO;
import com.devinhouse.ecommerce.entity.Product;
import com.devinhouse.ecommerce.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProducts() {
        List<Product> products = productService.getAll();
        List<ProductDTO> productDTOs = products.stream().map(product -> new ProductDTO(product)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProduct(@PathVariable Integer id) {
        Product product;
        try {
            product = productService.getById(id);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.save(productDTO.toEntity());
        return ResponseEntity.status(HttpStatus.OK).body(new ProductDTO(product));
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateProduct(@RequestBody ProductDTO productDTO) {
        Product product;
        try {
            product = productService.update(productDTO.toEntity());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ProductDTO(product));
    }

    @PatchMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> patchProduct(@RequestBody ProductDTO productDTO) {
        Product product;
        try {
            product = productService.patch(productDTO.toEntity());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ProductDTO(product));
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success.");
    }

}
