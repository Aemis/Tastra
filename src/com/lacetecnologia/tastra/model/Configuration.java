package com.lacetecnologia.tastra.model;

public class Configuration {

    private ConfigKey key;
    private String value;

    public Configuration(ConfigKey key, String value) {
        this.key = key;
    }

    public ConfigKey getKey() {
        return key;
    }

    public void setKey(ConfigKey key) { this.key = key; }

    public String getValue() {
        return value;
    }

    public void setValue(String value) { this.value = value; }
}


