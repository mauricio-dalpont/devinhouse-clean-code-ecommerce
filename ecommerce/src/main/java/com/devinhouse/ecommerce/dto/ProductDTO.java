package com.devinhouse.ecommerce.dto;

import java.io.Serializable;

import com.devinhouse.ecommerce.entity.Product;
import com.devinhouse.ecommerce.entity.Supplier;

public class ProductDTO implements Serializable {

    private Integer id;
    private String name;
    private double price;
    private SupplierDTO supplier;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.supplier = new SupplierDTO(product.getSupplier());
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SupplierDTO getSupplier() {
        return this.supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public Product toEntity() {
        return new Product(this.id, this.name, this.price,
                this.supplier == null ? null : new Supplier(this.supplier.getId(), this.supplier.getName()));
    }
}
