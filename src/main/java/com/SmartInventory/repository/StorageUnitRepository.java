package com.SmartInventory.repository;

import com.SmartInventory.model.StorageUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StorageUnitRepository extends JpaRepository<StorageUnit,Integer >{

    @Query(nativeQuery = true,value = "select * from machine_inventory.storage_unit where machine_id =? ")
    public List<StorageUnit> findStorageUnitByMachineId(Integer id);
}
