package com.devinhouse.ecommerce.dto;

import com.devinhouse.ecommerce.entity.Supplier;

public class SupplierDTO {

    private Integer id;
    private String name;

    public SupplierDTO() {}

    public SupplierDTO(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
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

}
