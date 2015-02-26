package com.thoughtworks.mvc.container;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.thoughtworks.mvc.annotations.Mapping;
import com.thoughtworks.mvc.example.MyAction;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GuiceContainerTest {

    private static final String MODULE_PATH = "com.thoughtworks.mvc.example.ExampleModule";
    private IocContainer container;

    @Before
    public void setUp() {
        container = new GuiceContainer();
        container.init(MODULE_PATH);
    }

    @Test
    public void should_get_expected_bindings() throws Exception {
        // when
        List<Object> allBeans = container.findAllBeans();
        Optional<Object> finding = FluentIterable.from(allBeans).firstMatch(new Predicate<Object>() {
            @Override
            public boolean apply(Object input) {
                return input instanceof MyAction;
            }
        });

        // then
        assertThat(allBeans, notNullValue());
        assertTrue(finding.isPresent());
    }

    @Test
    public void should_get_mapping_method() throws Exception {
        // when
        Method method = container.getMethodByMappingUrl("/in");

        // then
        assertThat(method.getAnnotation(Mapping.class).forward(), is("/out"));
    }
}
