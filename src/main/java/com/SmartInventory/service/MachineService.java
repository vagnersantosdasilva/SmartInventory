package com.SmartInventory.service;

import com.SmartInventory.exceptions.DuplicateValueException;
import com.SmartInventory.exceptions.ResourceNotFoundException;
import com.SmartInventory.model.*;
import com.SmartInventory.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    public MachineDTO createMachine(MachineDTO machine) throws DuplicateValueException {
        //Verificar existência via serial da placa mae

            if (isRegistred(machine)) { throw new DuplicateValueException("A máquina já foi criada!");}
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

    public MachineDTO findMachineDTOById(Integer machineId) throws ResourceNotFoundException {
        if (motherBoardRepository.findByMachineId(machineId)==null) throw new ResourceNotFoundException("Recurso não encontrado");
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

    public void machineUpdate(MachineDTO machine) throws ResourceNotFoundException  {
        if (!isRegistred(machine)) { throw new ResourceNotFoundException("O recurso ainda não foi criado!");}
        Integer machineId = createMachineId(machine);
        MachineDTO machineDTO = setMachineId(machineId, machine);
        if (machineDTO.getMemories()!=null)memoryRepository.save(machineDTO.getMemories());
        //storageUnitRepository.save(machineDTO.getStorageUnits());
        if (machineDTO.getProcessor()!=null) processorRepository.save(machineDTO.getProcessor());
        if (machineDTO.getMotherBoard()!=null)motherBoardRepository.save(machineDTO.getMotherBoard());
        if (machineDTO.getSoftwares()!=null)softwareRepository.save(machineDTO.getSoftwares());
        if (machineDTO.getOperationalSystem()!=null)operationalSystemRepository.save(machineDTO.getOperationalSystem());


    }

    private boolean isRegistred(MachineDTO machine ){
        String serialNumber = machine.getMotherBoard().getSerialNumber();
        if (motherBoardRepository.findBySerialNumber(serialNumber)!=null) return true;
        return false;
    }

    private Integer createMachineId(MachineDTO machineDTO){
        Random generator = new Random();
        Integer lastId = motherBoardRepository.lastMachineId();
        if (lastId==null) return 1;
        return lastId+1;
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
            if (machine.getMotherBoard()!=null) machine.getMotherBoard().setMachineId(id);
            if (machine.getProcessor()!=null)machine.getProcessor().setMachineId(id);
            if (machine.getOperationalSystem()!=null)machine.getOperationalSystem().setMachineId(id);
        }

        return machine;
    }

}
