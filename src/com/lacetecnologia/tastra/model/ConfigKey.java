package com.lacetecnologia.tastra.model;

public enum ConfigKey {
    JIRA("Jira Token"),
    WBS_USER("WBS Usuário"),
    WBS_PASSWORD("WBS Senha");

    private String name;

    private ConfigKey(String name){
        this.name = name;
    }



}
