package com.devinhouse.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import com.devinhouse.ecommerce.entity.Cart;

public class CartDTO {

    private Integer id;
    private List<ProductDTO> products;

    public CartDTO() {
    }

    public CartDTO(Cart cart) {
        this.id = cart.getId();
        this.products = new ArrayList<ProductDTO>();
        if (cart.getProducts().size() == 0)
            this.products = new ArrayList<ProductDTO>();
        cart.getProducts().forEach(product -> addProduct(new ProductDTO(product)));
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ProductDTO> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public void addProducts(List<ProductDTO> products) {
        for (ProductDTO productDTO : products) {
            this.products.add(productDTO);
        }
    }

    public void addProduct(ProductDTO productDTO) {
        this.products.add(productDTO);
    }

    public double getTotal() {
        double total = 0;
        for (ProductDTO product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public Cart toEntity() {
        return new Cart(this.id, this.products.stream().map((product) -> product.toEntity()).toList());
    }
}
