package com.SmartInventory.controller;

import com.SmartInventory.exceptions.DuplicateValueException;
import com.SmartInventory.exceptions.ResourceNotFoundException;
import com.SmartInventory.model.*;
import com.SmartInventory.repository.MachineDTO;
import com.SmartInventory.service.MachineService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/inventory")
public class SmartInventoryController {

    @Autowired
    MachineService machineService;


    @GetMapping("/machine/{id}")
    public ResponseEntity<?> getMachineById(@PathVariable("id") Integer id) throws ResourceNotFoundException {

        return new ResponseEntity<>(machineService.findMachineDTOById(id), HttpStatus.OK);
    }

    @PostMapping("/machine/create")
    public ResponseEntity<?> createMachine(@RequestBody MachineDTO machine) throws DuplicateValueException {

        return new ResponseEntity<>(
                machineService.createMachine(machine),
                HttpStatus.CREATED
        );
    }

    @PutMapping("machine/update/{id}")
    public ResponseEntity<?> updateMachine(@PathVariable("id")Integer id,@RequestBody MachineDTO machine) throws ResourceNotFoundException,DuplicateValueException{
        machineService.machineUpdate(id,machine);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

}
