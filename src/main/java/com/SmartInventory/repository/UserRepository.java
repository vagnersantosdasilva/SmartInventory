package com.SmartInventory.repository;

import com.SmartInventory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query (nativeQuery = true,value="select * from machine_inventory.user where username=?1")
    User findByUsername(String username);

}
