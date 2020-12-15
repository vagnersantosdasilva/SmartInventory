package com.SmartInventory.repository;

import com.SmartInventory.model.Memory;
import com.SmartInventory.model.Software;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoftwareRepository extends JpaRepository<Software,Integer> {

    @Query(nativeQuery = true,value="select * from machine_inventory.software where machine_id = ?1")
    public List<Software> findByMachineId(Integer id);

    @Query(nativeQuery = true,value="select * from machine_inventory.software where machine_id=?1 and deleted=false")
    public List<Software> findInstalledByMachineId(Integer id);


}
