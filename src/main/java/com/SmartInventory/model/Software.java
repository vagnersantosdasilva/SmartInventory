package com.SmartInventory.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Software implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false )
    private Integer machineId;
    private String name;
    private String version;
    private String arquiteture;
    private Boolean collection;
    private String installDate;
    private String uninstallLocation;
    private String installLocation;
    private boolean deleted;
    private String deleteDate;
    private String categories;



    public Software(Integer id, Integer machineId, String name, String version, String arquiteture,
                    Boolean collection, String installDate, String uninstallLocation,
                    String installLocation, boolean deleted, String deleteDate,String categories) {
        this.id = id;
        this.machineId = machineId;
        this.name = name;
        this.version = version;
        this.arquiteture = arquiteture;
        this.collection = collection;
        this.installDate = installDate;
        this.uninstallLocation = uninstallLocation;
        this.installLocation = installLocation;
        this.deleted=deleted;
        this.deleteDate=deleteDate;
        this.categories=categories;
    }

    public Software(){}

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getArquiteture() {
        return arquiteture;
    }

    public void setArquiteture(String arquiteture) {
        this.arquiteture = arquiteture;
    }

    public Boolean getCollection() {
        return collection;
    }

    public void setCollection(Boolean collection) {
        this.collection = collection;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public String getUninstallLocation() {
        return uninstallLocation;
    }

    public void setUninstallLocation(String uninstallLocation) {
        this.uninstallLocation = uninstallLocation;
    }

    public String getInstallLocation() {
        return installLocation;
    }

    public void setInstallLocation(String installLocation) {
        this.installLocation = installLocation;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }
}
