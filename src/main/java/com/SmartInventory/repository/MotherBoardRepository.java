package com.SmartInventory.repository;

import com.SmartInventory.model.Memory;
import com.SmartInventory.model.MotherBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotherBoardRepository extends JpaRepository< MotherBoard,Integer> {

    @Query(nativeQuery = true,value = "select * from machine_inventory.mother_board where machine_id = ?1")
    public MotherBoard findByMachineId(Integer id);

    @Query (nativeQuery = true,value="select * from machine_inventory.mother_board where serial_number=?1")
    public MotherBoard findBySerialNumber(String serial);
}
