package com.SmartInventory.repository;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartInventoryRepositoryQuery {

    public List<MachineDTO> findAllMachine();
    public MachineDTO findMachineById();

}
