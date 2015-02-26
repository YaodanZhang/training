package com.thoughtworks.ns.collection;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ProductTest {

    private static final String X_BOX_NAME = "x-box";

    @Test
    public void should_return_true_when_compare_equal_products() throws Exception {
        assertThat(new Product(X_BOX_NAME, 0), equalTo(new Product(X_BOX_NAME, 0)));
    }

    @Test
    public void should_return_false_when_compare_not_equal_products() throws Exception {
        assertThat(new Product(X_BOX_NAME, 0), not(equalTo(new Product("ps-3", 0))));
    }

    @Test
    public void should_return_same_hash_code_for_equal_products() throws Exception {
        assertThat(new Product(X_BOX_NAME, 0).hashCode(), is(new Product(X_BOX_NAME, 0).hashCode()));
    }
}
