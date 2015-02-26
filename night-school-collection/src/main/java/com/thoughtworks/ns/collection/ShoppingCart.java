package com.thoughtworks.ns.collection;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;

import java.util.Map;

import static com.google.common.collect.Iterables.tryFind;
import static com.google.common.collect.Maps.newHashMap;
import static java.util.Map.Entry;

public class ShoppingCart {
    private Map<Product, Integer> productsMap;

    public ShoppingCart() {
        productsMap = newHashMap();
    }

    public int getAmount() {
        int amount = 0;
        for (Entry<Product, Integer> entry : productsMap.entrySet()) {
            amount += entry.getValue();
        }
        return amount;
    }

    public void addProduct(Product product) {
        Integer currentAmountForThis = productsMap.get(product);
        if (currentAmountForThis == null) {
            productsMap.put(product, 1);
        } else {
            productsMap.put(product, currentAmountForThis + 1);
        }
    }

    public Product getProductByName(final String name) {
        return tryFindExistProductEntry(name)
                .transform(toProduct())
                .orNull();
    }

    private Function<Entry<Product, Integer>, Product> toProduct() {
        return new Function<Entry<Product, Integer>, Product>() {
            @Override
            public Product apply(Entry<Product, Integer> entry) {
                return entry.getKey();
            }
        };
    }

    private Optional<Entry<Product, Integer>> tryFindExistProductEntry(String name) {
        return tryFind(productsMap.entrySet(), byNameAndAmount(name));
    }

    private Predicate<Entry<Product, Integer>> byNameAndAmount(final String name) {
        return new Predicate<Entry<Product, Integer>>() {
            public boolean apply(Entry<Product, Integer> entry) {
                return entry.getValue() > 0
                        && entry.getKey().getName().equals(name);
            }
        };
    }

    public Product removeProductByName(String name) {
        return tryFindExistProductEntry(name)
                .transform(decreaseAmount())
                .transform(toProduct())
                .orNull();
    }

    private Function<Entry<Product, Integer>, Entry<Product, Integer>> decreaseAmount() {
        return new Function<Entry<Product, Integer>, Entry<Product, Integer>>() {
            @Override
            public Entry<Product, Integer> apply(Entry<Product, Integer> entry) {
                entry.setValue(entry.getValue() - 1);
                return entry;
            }
        };
    }

    public int getAmountOfProduct(String name) {
        Optional<Integer> transform = tryFindExistProductEntry(name)
                .transform(toAmount());
        return transform.isPresent() ? transform.get() : 0;
    }

    private Function<Entry<Product, Integer>, Integer> toAmount() {
        return new Function<Entry<Product, Integer>, Integer>() {
            @Override
            public Integer apply(Entry<Product, Integer> entry) {
                return entry.getValue();
            }
        };
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Entry<Product, Integer> entry : productsMap.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }
}
