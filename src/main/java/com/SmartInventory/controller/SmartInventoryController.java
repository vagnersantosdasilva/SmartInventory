package com.SmartInventory.controller;

import com.SmartInventory.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class SmartInventoryController {

    @Autowired
    MachineService machineService;


    @GetMapping("/machine/{id}")
    public ResponseEntity<?> getMachineById(@PathVariable("id") Integer id) {


        return new ResponseEntity<>(machineService.findMachineDTOById(id), HttpStatus.OK);
    }

    @GetMapping("/all_machine")
    public ResponseEntity<?> getAllMachine(){

        return new ResponseEntity<>(machineService.findAll(),HttpStatus.OK);
    }



}
