package ru.smart.service.filter;

import lombok.Getter;

public class FilterDB {

    @Getter
    private String name;

    @Getter
    private String paramName;

    @Getter
    private Object paramValue;

    public FilterDB(String name) {
        this.name = name;
    }

    public void setParam(String paramName, Object paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    public boolean withParameter() {
        return paramName != null;
    }
}
