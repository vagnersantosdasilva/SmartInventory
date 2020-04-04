package com.SmartInventory.controller;

import com.SmartInventory.model.*;
import com.SmartInventory.repository.MachineDTO;
import com.SmartInventory.service.MachineService;
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
    public ResponseEntity<?> getMachineById(@PathVariable("id") Integer id) {


        return new ResponseEntity<>(machineService.findMachineDTOById(id), HttpStatus.OK);
    }

    @GetMapping("/all_machine")
    public ResponseEntity<?> getAllMachine(){

        return new ResponseEntity<>(machineService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/machine/create")
    public ResponseEntity<?> createMachine(@RequestBody MachineDTO machine){

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PutMapping("/processor/update")
    public ResponseEntity<?> updateProcessor(@RequestBody Processor processor){
        try{
            machineService.processorUpdate(processor);
        }catch(Exception e){
            Error error =  new Error(e.getMessage(),e.getCause());

            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/memory/update")
    public ResponseEntity<?> updateMemory(@PathVariable("id") Integer id,
                                          @RequestBody List<Memory> memorys){
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping("/mother_board/update")
    public ResponseEntity<?> updateMotherBoard(@PathVariable("id") Integer id,
                                               @RequestBody MotherBoard motherBoard){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/softwares/update")
    public ResponseEntity<?> updateSoftwares(@RequestBody List<Software> softwares){

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping("/operational_system/update")
    public ResponseEntity<?> updateOperationalSystem(@RequestBody OperationalSystem operationalSystem){
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
