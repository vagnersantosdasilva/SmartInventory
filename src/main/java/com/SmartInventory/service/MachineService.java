package com.SmartInventory.service;

import com.SmartInventory.model.*;
import com.SmartInventory.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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


    public MachineDTO createMachine(MachineDTO machine){
        //Verificar existência via serial da placa mae
        try{
            if (!isRegistred(machine)) {
                Integer machineId = createMachineId(machine);
                MachineDTO machineDTO = setMachineId(machineId, machine);
                if (machineDTO.getMemories()!=null)memoryRepository.save(machineDTO.getMemories());
                //storageUnitRepository.save(machineDTO.getStorageUnits());
                if (machineDTO.getProcessor()!=null) processorRepository.save(machineDTO.getProcessor());
                if (machineDTO.getMotherBoard()!=null)motherBoardRepository.save(machineDTO.getMotherBoard());
                if (machineDTO.getSoftwares()!=null)softwareRepository.save(machineDTO.getSoftwares());
                if (machineDTO.getOperationalSystem()!=null)operationalSystemRepository.save(machineDTO.getOperationalSystem());

                return machineDTO;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public MachineDTO findMachineDTOById(Integer machineId){

        List<Memory> listMemory = memoryRepository.findByMachineId(machineId);
        List<Software> listSoftware = softwareRepository.findByMachineId(machineId);
        Processor processor = processorRepository.findByMachineId(machineId);
        MotherBoard motherBoard = motherBoardRepository.findByMachineId(machineId);
        OperationalSystem op = operationalSystemRepository.findByMachineId(machineId);
        //List<StorageUnit> storageUnits = storageUnitRepository.findByMachineId(machineId);
        MachineDTO machineDTO = new MachineDTO(processor.getMachineId(),listMemory,
                processor,listSoftware,motherBoard,op,null);

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
                //List<StorageUnit> storageUnits = storageUnitRepository.findByMachineId(os.getMachineId());
                MachineDTO machineDTO = new MachineDTO(os.getMachineId(), memoryList, processor,
                        softwareList, motherBoard, os,null);
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

    private boolean isRegistred(MachineDTO machine ){
        String serialNumber = machine.getMotherBoard().getSerialNumber();
        if (motherBoardRepository.findBySerialNumber(serialNumber)!=null) return true;
        return false;
    }

    private Integer createMachineId(MachineDTO machineDTO){
        Random generator = new Random();
        return generator.nextInt(1000);
    }

    private MachineDTO setMachineId(Integer id, MachineDTO machine){
        if (id!=null) {
            machine.setId(id);

            if (machine.getMemories()!=null){
                machine.setMemory(
                        machine.getMemories().stream()
                                .map(e -> {
                                    e.setMachineId(id);
                                    return e;
                                })
                                .collect(Collectors.toList())
                );
            }

            if(machine.getSoftwares()!=null){
                machine.setSoftwares(
                        machine.getSoftwares().stream()
                                .map(e -> {
                                    e.setMachineId(id);
                                    return e;
                                })
                                .collect(Collectors.toList())
                );
            }
    /*
            machine.setStorageUnits(
                    machine.getStorageUnits().stream()
                    .map(e -> {e.setMachineId(id);return e;})
                    .collect(Collectors.toList())
            );
    */
            if (machine.getMotherBoard()!=null) machine.getMotherBoard().setMachineId(id);
            if (machine.getProcessor()!=null)machine.getProcessor().setMachineId(id);
            if (machine.getOperationalSystem()!=null)machine.getOperationalSystem().setMachineId(id);
        }

        return machine;
    }

}
