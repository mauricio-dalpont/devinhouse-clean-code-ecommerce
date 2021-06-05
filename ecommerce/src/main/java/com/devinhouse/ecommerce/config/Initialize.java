package com.devinhouse.ecommerce.config;

import com.devinhouse.ecommerce.entity.Cart;
import com.devinhouse.ecommerce.entity.Product;
import com.devinhouse.ecommerce.entity.Supplier;
import com.devinhouse.ecommerce.repository.CartRepository;
import com.devinhouse.ecommerce.repository.ProductRepository;
import com.devinhouse.ecommerce.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Initialize implements CommandLineRunner {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CartRepository cartRepository;

    @Override
    public void run(String... args) throws Exception {
        int amountOfSuppliers = 2;
        int amountOfProducts = 10;

        for (int i = 0; i < amountOfSuppliers; i++) {
            Supplier supplier = new Supplier();
            supplier.setName("Supplier " + i);
            supplierRepository.save(supplier);
        }

        for (int i = 0; i < amountOfProducts / 2; i++) {
            var product = createNewProduct(i, 1);
            productRepository.save(product);
        }

        for (int i = amountOfProducts / 2 + 1; i < amountOfProducts; i++) {
            var product = createNewProduct(i, 2);
            productRepository.save(product);
        }

        Cart cart = new Cart();
        cart.setProducts(productRepository.findAll());
        cartRepository.save(cart);

    }

    private Product createNewProduct(Integer index, Integer supplierId) {
        Product product = new Product();
        product.setName("Product " + index);
        product.setPrice(Math.random() * (index + 1));
        product.setSupplier(supplierRepository.getById(supplierId));
        return product;
    }
}
