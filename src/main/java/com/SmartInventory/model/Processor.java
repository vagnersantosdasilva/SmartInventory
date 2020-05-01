package com.SmartInventory.model;

import javax.persistence.*;

@Entity
public class Processor  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false )
    private Integer machineId;
    private String name;
    private Integer architeture;
    private Integer maxClock;
    private Integer cores;
    private String manufacturer;
    private String status;

    public Processor(Integer id, Integer machineId, String name, Integer architeture, Integer maxClock, Integer cores, String manufacturer, String status) {
        this.id = id;
        this.machineId = machineId;
        this.name = name;
        this.architeture = architeture;
        this.maxClock = maxClock;
        this.cores = cores;
        this.manufacturer = manufacturer;
        this.status = status;
    }
    public Processor(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArchiteture() {
        return architeture;
    }

    public void setArchiteture(Integer architeture) {
        this.architeture = architeture;
    }

    public Integer getMaxClock() {
        return maxClock;
    }

    public void setMaxClock(Integer maxClock) {
        this.maxClock = maxClock;
    }

    public Integer getCores() {
        return cores;
    }

    public void setCores(Integer cores) {
        this.cores = cores;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
