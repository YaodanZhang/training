package com.thoughtworks.mvc.example;

import com.thoughtworks.mvc.annotations.Mapping;

import java.util.Map;

public class TestAction {
    private String stringParameter;
    private int intParameter;
    private boolean booleanParameter;
    private byte byteParameter;
    private char charParameter;
    private short shortParameter;
    private long longParameter;
    private float floatParameter;
    private double doubleParameter;
    private Boolean packagedBooleanParameter;
    private Integer packedIntPara;
    private Byte packedBytePara;
    private Character packedCharPara;
    private Short packedShortPara;
    private Long packedLongPara;
    private Float packedFloatPara;
    private Double packedDoublePara;
    private Map<String, Integer> map;
    private Nested nested;

    private boolean handleNestedInvoked = false;

    @Mapping(url = "/handleNested/$1/$2", forward = "/handleNestedResult.jsp")
    public void handleNested(Nested nested) {
        handleNestedInvoked = true;
        System.out.print("dd");
    }

    public String getStringParameter() {
        return stringParameter;
    }

    public void setStringParameter(String stringParameter) {
        this.stringParameter = stringParameter;
    }

    public int getIntParameter() {
        return intParameter;
    }

    public void setIntParameter(int intParameter) {
        this.intParameter = intParameter;
    }

    public boolean getBooleanParameter() {
        return booleanParameter;
    }

    public void setBooleanParameter(boolean booleanParameter) {
        this.booleanParameter = booleanParameter;
    }

    public byte getByteParameter() {
        return byteParameter;
    }

    public void setByteParameter(byte byteParameter) {
        this.byteParameter = byteParameter;
    }

    public char getCharParameter() {
        return charParameter;
    }

    public void setCharParameter(char charParameter) {
        this.charParameter = charParameter;
    }

    public short getShortParameter() {
        return shortParameter;
    }

    public void setShortParameter(short shortParameter) {
        this.shortParameter = shortParameter;
    }

    public long getLongParameter() {
        return longParameter;
    }

    public void setLongParameter(long longParameter) {
        this.longParameter = longParameter;
    }

    public float getFloatParameter() {
        return floatParameter;
    }

    public void setFloatParameter(float floatParameter) {
        this.floatParameter = floatParameter;
    }

    public double getDoubleParameter() {
        return doubleParameter;
    }

    public void setDoubleParameter(double doubleParameter) {
        this.doubleParameter = doubleParameter;
    }

    public Boolean getPackagedBooleanParameter() {
        return packagedBooleanParameter;
    }

    public void setPackagedBooleanParameter(Boolean packagedBooleanParameter) {
        this.packagedBooleanParameter = packagedBooleanParameter;
    }

    public Integer getPackedIntPara() {
        return packedIntPara;
    }

    public Byte getPackedBytePara() {
        return packedBytePara;
    }

    public void setPackedIntPara(Integer packedIntPara) {
        this.packedIntPara = packedIntPara;
    }

    public void setPackedBytePara(Byte packedBytePara) {
        this.packedBytePara = packedBytePara;
    }

    public Character getPackedCharPara() {
        return packedCharPara;
    }

    public Short getPackedShortPara() {
        return packedShortPara;
    }

    public Long getPackedLongPara() {
        return packedLongPara;
    }

    public Float getPackedFloatPara() {
        return packedFloatPara;
    }

    public Double getPackedDoublePara() {
        return packedDoublePara;
    }

    public void setPackedCharPara(Character packedCharPara) {
        this.packedCharPara = packedCharPara;
    }

    public void setPackedShortPara(Short packedShortPara) {
        this.packedShortPara = packedShortPara;
    }

    public void setPackedLongPara(Long packedLongPara) {
        this.packedLongPara = packedLongPara;
    }

    public void setPackedFloatPara(Float packedFloatPara) {
        this.packedFloatPara = packedFloatPara;
    }

    public void setPackedDoublePara(Double packedDoublePara) {
        this.packedDoublePara = packedDoublePara;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public Nested getNested() {
        return nested;
    }

    public void setNested(Nested nested) {
        this.nested = nested;
    }

    public boolean isHandleNestedInvoked() {
        return handleNestedInvoked;
    }
}
