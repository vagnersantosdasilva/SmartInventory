package com.SmartInventory.repository;

import com.SmartInventory.model.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorRepository extends JpaRepository<Processor,Integer> {

    @Query(nativeQuery = true,value = "select * from machine_inventory.processor where machine_id = ?")
    public Processor findByMachineId(Integer id);
}
