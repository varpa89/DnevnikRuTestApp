package com.varpa89.dnevnik.util;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * User: varpa89
 * Date: 21.04.14
 * Time: 10:47
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldErrorResource {
    private String resource;
    private String name;
    private String code;
    private String message;

    public String getResource() { return resource; }

    public void setResource(String resource) { this.resource = resource; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}