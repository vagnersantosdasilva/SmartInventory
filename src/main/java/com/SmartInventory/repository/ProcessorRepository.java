package com.SmartInventory.repository;

import com.SmartInventory.model.Memory;
import com.SmartInventory.model.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcessorRepository extends JpaRepository<Processor,Integer> {

    @Query(nativeQuery = true,value = "select * from machine_inventory.processor where machine_id = ?1")
    public Processor findByMachineId(Integer id);
}
