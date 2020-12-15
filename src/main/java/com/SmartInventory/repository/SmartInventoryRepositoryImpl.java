package com.SmartInventory.repository;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class SmartInventoryRepositoryImpl implements  SmartInventoryRepositoryQuery {

    @Autowired
    private EntityManager entityManager;


    public List<?> findProcessorsAMD(String memoryType) {

        String sql = "select " +
                "Model as model," +
                "Family as family, " +
                "Line as line , " +
                "Platform as platform ," +
                "Base_Clock as baseClock ," +
                "of_CPU_Cores as ofCPUCores , " +
                "System_Memory_Type as systemMemoryType " +
                "from amd " +
                "where " +
                //"model = :mod and " ;
                "System_Memory_Type = :mType ";

        Query query = entityManager.createNativeQuery(sql);
        query =setParameters(query,memoryType);
        //List<AmdDTO> lista =resultList(query,AmdDTO.class);

        return null;
    }

    private <T> List<T> resultList(Query query, Class<T> tClass ) {

        List<T> result = query
                .unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(tClass))
                .list();
        return result;
    }

    private Query setParameters(Query query, String mType) {
        query.setParameter("mType", mType);
        return query;

    }

    @Override
    public List<MachineDTO> findAllMachine() {
        return null;
    }

    @Override
    public MachineDTO findMachineById() {
        return null;
    }
}
