package com.SmartInventory.model;

import javax.persistence.*;


@Entity
public class MotherBoard   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //@OneToOne
    //@JoinColumn(name="machine_id")
    @Column(nullable = false,unique = true )
    private Integer machineId;
    private String manufacturer;
    private String product;

    private String serialNumber;
    private String status;


    public MotherBoard(Integer id, Integer machineId, String manufacturer, String product, String serialNumber, String status) {
        this.id = id;
        this.machineId = machineId;
        this.manufacturer = manufacturer;
        this.product = product;
        this.serialNumber = serialNumber;
        this.status = status;
    }

    public MotherBoard(){}


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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
