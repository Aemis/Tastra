package com.lacetecnologia.tastra.model;

import java.util.Date;
import java.util.List;

public class Configurations {

    private List<Configuration> configurations;
    private Date lastUpdate;

    public Configurations() {
    }
    public Configurations(List<Configuration> configurations, Date lastUpdate) {
        this.configurations = configurations;
        this.lastUpdate = lastUpdate;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

}
