package com.devinhouse.ecommerce.entity;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    private Supplier supplier;

    public Product() {
    }

    public Product(Integer id, String name, double price, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.supplier = supplier;
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

    public Supplier getSupplier() {
        return this.supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public boolean validateFields() {
        Field[] fields = Product.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (field.get(this) == null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

    public void updateFields(Product newProduct) {
        Field[] fields = Product.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (field.get(newProduct) != null) {
                    field.set(this, field.get(newProduct));
                }
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}