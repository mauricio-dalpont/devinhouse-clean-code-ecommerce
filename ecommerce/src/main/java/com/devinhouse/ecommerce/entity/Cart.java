package com.devinhouse.ecommerce.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    @ManyToMany
    private List<Product> products;

    public Cart() {
    }

    public Cart(Integer id, List<Product> products) {
        this.id = id;
        this.products = products;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        List<Product> products = this.products == null ? new ArrayList<Product>() : this.products;
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
