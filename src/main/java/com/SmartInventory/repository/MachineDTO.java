package com.SmartInventory.repository;

import com.SmartInventory.model.*;

import java.util.List;

public class MachineDTO {

    private Integer id;
    private List<Memory> memorys;
    private Processor processor;
    private List<Software> softwares;
    private MotherBoard motherBoard;
    private OperationalSystem operationalSystem;


    public MachineDTO(Integer id, List<Memory> memorys, Processor processor, List<Software> softwares, MotherBoard motherBoard, OperationalSystem operationalSystem) {
        this.id = id;
        this.memorys = memorys;
        this.processor = processor;
        this.softwares = softwares;
        this.motherBoard = motherBoard;
        this.operationalSystem = operationalSystem;
    }

    public MachineDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Memory> getMemorys() {
        return memorys;
    }

    public void setMemory(List<Memory> memorys) {
        this.memorys = memorys;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public List<Software> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(List<Software> softwares) {
        this.softwares = softwares;
    }

    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    public void setMotherBoard(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
    }

    public OperationalSystem getOperationalSystem() {
        return operationalSystem;
    }

    public void setOperacionalSystem(OperationalSystem operationalSystem) {
        this.operationalSystem = operationalSystem;
    }
}
