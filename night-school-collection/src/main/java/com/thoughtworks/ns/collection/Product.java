package com.thoughtworks.ns.collection;

import com.google.common.base.Objects;

import static com.google.common.base.Objects.equal;

public class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return equal(this.name, product.name)
                && equal(this.price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, price);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
