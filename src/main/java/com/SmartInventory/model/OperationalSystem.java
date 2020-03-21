package com.SmartInventory.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class OperationalSystem  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Integer machineId;
    private String name;
    private String sysUpdate;
    private String hostname;
    private String status;
    private String version;
    private Date installDate;
    private Date lastBoot;

    public OperationalSystem(Integer id, Integer machineId, String name, String sysUpdate, String hostname, String status,
                             String version, Date installDate, Date lastBoot) {
        this.id = id;
        this.machineId = machineId;
        this.name = name;
        this.sysUpdate = sysUpdate;
        this.hostname = hostname;
        this.status = status;
        this.version = version;
        this.installDate = installDate;
        this.lastBoot = lastBoot;
    }

    public OperationalSystem(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdate() {
        return sysUpdate;
    }

    public void setUpdate(String update) {
        this.sysUpdate = update;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public Date getLastBoot() {
        return lastBoot;
    }

    public void setLastBoot(Date lastBoot) {
        this.lastBoot = lastBoot;
    }
}
