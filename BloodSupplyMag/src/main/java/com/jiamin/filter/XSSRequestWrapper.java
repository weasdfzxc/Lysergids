/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jiamin.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return stripXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return value;
    }

    public String getQueryString() {
        String value = super.getQueryString();
        if (value != null) {
            value = stripXSS(value);
        }
        return value;
    }

    private String stripXSS(String value) {
        if (value != null) {
            value = value.replace(";", "_").replace("--!", "_").replace("!--", "_");
            value = value.replace("//", "_").replace("/*", "_").replace("*/", "_").replace("*", "_").replace("'", "_");
            value = value.replaceAll("(?!)insert", "insert_").replaceAll("(?!)select", "select_").replaceAll("(?!)delete", "delete_").replaceAll("(?!)update ", "update_");
            value = value.replaceAll("(?!)create", "create_").replaceAll("(?!)alter", "alter_").replaceAll("(?!)drop", "drop_");
        }
        return value;
    }
}
