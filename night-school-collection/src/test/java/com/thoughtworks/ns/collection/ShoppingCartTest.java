package com.thoughtworks.ns.collection;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingCartTest {

    private static final String X_BOX_NAME = "x-box";
    private static final String PS_3_NAME = "PS3";
    private ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void should_show_amount() throws Exception {
        assertThat(shoppingCart.getAmount(), is(0));
    }

    @Test
    public void should_add_product() throws Exception {
        shoppingCart.addProduct(new Product(X_BOX_NAME, 0));
        assertThat(shoppingCart.getAmount(), is(1));
    }

    @Test
    public void should_not_find_product_if_not_exist() throws Exception {
        assertThat(shoppingCart.getProductByName(X_BOX_NAME), is(nullValue()));
    }

    @Test
    public void should_find_product_by_name() throws Exception {
        Product xBox = new Product(X_BOX_NAME, 0);
        shoppingCart.addProduct(xBox);
        assertThat(shoppingCart.getProductByName(X_BOX_NAME), is(xBox));
    }

    @Test
    public void should_not_remove_product_when_not_exist() throws Exception {
        shoppingCart.addProduct(new Product(X_BOX_NAME, 0));
        Product deletedProduct = shoppingCart.removeProductByName(PS_3_NAME);

        assertThat(deletedProduct, is(nullValue()));
        assertThat(shoppingCart.getAmount(), is(1));
    }

    @Test
    public void should_remove_product() throws Exception {
        Product xBox = new Product(X_BOX_NAME, 0);
        shoppingCart.addProduct(xBox);

        Product deletedProduct = shoppingCart.removeProductByName(X_BOX_NAME);

        assertThat(deletedProduct, sameInstance(xBox));
        assertThat(shoppingCart.getAmount(), is(0));
    }

    @Test
    public void should_return_0_for_amount_of_not_existed_product() throws Exception {
        assertThat(shoppingCart.getAmountOfProduct(X_BOX_NAME), is(0));
    }

    @Test
    public void should_get_amount_of_one_product() throws Exception {
        shoppingCart.addProduct(new Product(X_BOX_NAME, 0));
        shoppingCart.addProduct(new Product(X_BOX_NAME, 0));

        assertThat(shoppingCart.getAmountOfProduct(X_BOX_NAME), is(2));
    }

    @Test
    public void should_get_total_price() throws Exception {
        shoppingCart.addProduct(new Product(X_BOX_NAME, 10));
        shoppingCart.addProduct(new Product(X_BOX_NAME, 10));
        shoppingCart.addProduct(new Product(PS_3_NAME, 100));

        assertThat(shoppingCart.getTotalPrice(), is(120));
    }
}
