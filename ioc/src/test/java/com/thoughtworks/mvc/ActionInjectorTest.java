package com.thoughtworks.mvc;

import com.thoughtworks.mvc.example.TestAction;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ActionInjectorTest {

    private ActionInjector inject;
    private Map<String, String> parameterMap;
    private TestAction testAction;

    @Before
    public void setUp() throws Exception {
        testAction = new TestAction();
        parameterMap = new HashMap<String, String>();
        inject = new ActionInjector(testAction, parameterMap);
    }

    @Test
    public void should_inject_string_parameter() throws Exception {
        // given
        parameterMap.put("stringParameter", "string");

        // when
        testAction = inject.inject();

        // then
        assertThat(testAction.getStringParameter(), is("string"));
    }

    @Test
    public void should_inject_int_parameter() throws Exception {
        // given
        parameterMap.put("intParameter", "14");
        parameterMap.put("packedIntPara", "12");

        // when
        testAction = inject.inject();

        // then
        assertThat(testAction.getIntParameter(), is(14));
        assertThat(testAction.getPackedIntPara(), is(12));
    }

    @Test
    public void should_inject_boolean_parameter() throws Exception {
        // given
        parameterMap.put("booleanParameter", "true");
        parameterMap.put("packagedBooleanParameter", "false");

        // when
        testAction = inject.inject();

        // then
        assertThat(testAction.getBooleanParameter(), is(true));
        assertThat(testAction.getPackagedBooleanParameter(), is(false));
    }

    @Test
    public void should_inject_byte_parameter() throws Exception {
        // given
        parameterMap.put("byteParameter", "12");
        parameterMap.put("packedBytePara", "14");

        // when
        testAction = inject.inject();

        // then
        assertThat(testAction.getByteParameter(), is((byte) 12));
        assertThat(testAction.getPackedBytePara(), is(Byte.valueOf("14")));
    }

    @Test
    public void should_inject_char_parameter() throws Exception {
        // given
        parameterMap.put("charParameter", "c");
        parameterMap.put("packedCharPara", "b");

        // when
        testAction = inject.inject();

        // then
        assertThat(testAction.getCharParameter(), is('c'));
        assertThat(testAction.getPackedCharPara(), is('b'));
    }

    @Test
    public void should_inject_short_parameter() throws Exception {
        // given
        parameterMap.put("shortParameter", "14");
        parameterMap.put("packedShortPara", "12");

        // when
        testAction = inject.inject();

        // then
        assertThat(testAction.getShortParameter(), is((short) 14));
        assertThat(testAction.getPackedShortPara(), is((short) 12));
    }

    @Test
    public void should_inject_long_parameter() throws Exception {
        // given
        parameterMap.put("longParameter", "12");
        parameterMap.put("packedLongPara", "14");

        // when
        testAction = inject.inject();

        // then
        assertThat(testAction.getLongParameter(), is((long) 12));
        assertThat(testAction.getPackedLongPara(), is((long) 14));
    }

    @Test
    public void should_inject_float_parameter() throws Exception {
        // given
        parameterMap.put("floatParameter", "12.0");
        parameterMap.put("packedFloatPara", "14");

        // when
        testAction = inject.inject();

        // then
        assertThat(testAction.getFloatParameter(), is((float) 12));
        assertThat(testAction.getPackedFloatPara(), is((float) 14));
    }

    @Test
    public void should_inject_double_parameter() throws Exception {
        // given
        parameterMap.put("doubleParameter", "12");
        parameterMap.put("packedDoublePara", "14");

        // when
        testAction = inject.inject();

        // then
        assertThat(testAction.getDoubleParameter(), is(12.0));
        assertThat(testAction.getPackedDoublePara(), is(14.0));
    }

    @Test
    public void should_inject_nested_object() throws Exception {
        // given
        parameterMap.put("nested.id", "1");
        parameterMap.put("nested.name", "zhang");
        parameterMap.put("nested.next.id", "2");
        parameterMap.put("nested.next.name", "yaodan");

        // when
        testAction = inject.inject();

        // then
        assertThat(testAction.getNested().getId(), is(1));
        assertThat(testAction.getNested().getName(), is("zhang"));
        assertThat(testAction.getNested().getNext().getId(), is(2));
        assertThat(testAction.getNested().getNext().getName(), is("yaodan"));
        assertNull(testAction.getNested().getNext().getNext());
    }
}
