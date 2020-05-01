package com.SmartInventory.service;

import com.SmartInventory.model.*;
import com.SmartInventory.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachineService {

    @Autowired
    MemoryRepository memoryRepository;

    @Autowired
    ProcessorRepository processorRepository;

    @Autowired
    MotherBoardRepository motherBoardRepository;

    @Autowired
    SoftwareRepository softwareRepository;

    @Autowired
    OperationalSystemRepository operationalSystemRepository;


    public MachineDTO findMachineDTOById(Integer machineId){

        List<Memory> listMemory = memoryRepository.findByMachineId(machineId);
        List<Software> listSoftware = softwareRepository.findByMachineId(machineId);
        Processor processor = processorRepository.findOne(machineId);
        MotherBoard motherBoard = motherBoardRepository.findOne(machineId);
        OperationalSystem op = operationalSystemRepository.findOne(machineId);
        MachineDTO machineDTO = new MachineDTO(processor.getMachineId(),listMemory,processor,listSoftware,motherBoard,op);
        return machineDTO;
    }

    public List<MachineDTO> findAll(){

        List<MachineDTO> listMachine = new ArrayList<>();
        List<OperationalSystem> listOS = operationalSystemRepository.findAll();

        for (OperationalSystem os :listOS){
            if (os.getMachineId()!=null) {
                List<Software> softwareList = softwareRepository.findByMachineId(os.getMachineId());
                List<Memory> memoryList = memoryRepository.findByMachineId(os.getMachineId());
                Processor processor = processorRepository.findByMachineId(os.getMachineId());
                MotherBoard motherBoard = motherBoardRepository.findByMachineId(os.getMachineId());
                MachineDTO machineDTO = new MachineDTO(os.getMachineId(), memoryList, processor, softwareList, motherBoard, os);
                listMachine.add(machineDTO);
            }
        }
        return listMachine;
    }

    public boolean processorUpdate(Processor processor) throws Exception{
        processorRepository.save(processor);
        return true;
    }

    public boolean memorysUpdate(List<Memory> listMemory) throws Exception{
        memoryRepository.save(listMemory);
        return true;
    }

    public boolean osUpdate(OperationalSystem os) throws Exception{
        operationalSystemRepository.save(os);
        return true;
    }

    public boolean softwareUpdate(List<Software> listSoftware) throws Exception{
        softwareRepository.save(listSoftware);
        return true;
    }

    public boolean motherBoardUpdate(MotherBoard motherBoard) throws Exception{
        motherBoardRepository.save(motherBoard);
        return true;
    }

}
