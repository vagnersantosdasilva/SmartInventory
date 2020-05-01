package com.SmartInventory.repository;

import com.SmartInventory.model.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemoryRepository extends JpaRepository< Memory,Integer> {

    @Query(nativeQuery = true,value = "select * from machine_inventory.memory where machine_id = ?")
    public List<Memory> findByMachineId(Integer id);

}
