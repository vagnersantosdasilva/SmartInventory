package com.SmartInventory.repository;

import com.SmartInventory.model.Memory;
import com.SmartInventory.model.OperationalSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationalSystemRepository extends JpaRepository<OperationalSystem,Integer> {

    @Query(nativeQuery = true,value = "select * from machine_inventory.operational_system where machine_id = ?1")
    public OperationalSystem findByMachineId(Integer id);
}
