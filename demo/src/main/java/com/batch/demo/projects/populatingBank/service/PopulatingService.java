package com.batch.demo.projects.populatingBank.service;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch.demo.projects.populatingBank.enitity.PopulatingEntity;
import com.batch.demo.projects.populatingBank.repository.PopulatingRepository;
import com.batch.demo.projects.populatingBank.service.DTO.BatchDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

// auxilary imports
import java.lang.Math;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PopulatingService {
    
    @Autowired
    private PopulatingRepository populatingRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void populate(){
        try{
            IntStream.range(0,10_000).forEach(i ->{
                PopulatingEntity customer = new PopulatingEntity();
                double rand = Math.random();
                if(rand < 0.1){
                    customer.setOrigin("foreign");
                }else{
                    customer.setOrigin("local");
                }
    
                customer.setDeposit(BigDecimal.ZERO);
                
                populatingRepository.saveAndFlush(customer);
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /*
     * For 10_000 rows:
        Processing time: 56285
        seconds:56 seconds
     */


    @Transactional
    public void increaseAllDeposits(){
        List<PopulatingEntity> customerList = populatingRepository.findAll();
        for(PopulatingEntity customer: customerList){
            BigDecimal currentDeposit = customer.getDeposit();
            customer.setDeposit(currentDeposit.add(BigDecimal.ONE));
        }
        populatingRepository.saveAllAndFlush(customerList);
    }
    /*
        Processing time: 5288
        seconds:5 seconds
     */

     
     @Transactional
     public void increaseInBatches(BatchDTO data) {
        int batchSize = (int)data.getBatchSize();
         List<PopulatingEntity> customerList = populatingRepository.findAll();
     
         try {
             for (int i = 0; i < customerList.size(); i += batchSize) {
                 int endIndex = Math.min(i + batchSize, customerList.size());
                 List<PopulatingEntity> batch = customerList.subList(i, endIndex);
     
                 batch.forEach(customer -> {
                     BigDecimal currentDeposit = customer.getDeposit();
                     customer.setDeposit(currentDeposit.add(BigDecimal.ONE));
                 });
     
                 populatingRepository.saveAllAndFlush(batch);
             }
         } catch (Exception e) {
             e.printStackTrace(); // Log the exception or handle it appropriately
         }
     }


     
    /*
    Batch Size: 100
    time: [6443, 6738, 6676]
    avg_time: 6619.0
    ---------------------------

    Batch Size: 200
    time: [5334, 6178, 6095]
    avg_time: 5869.0
    ---------------------------

    Batch Size: 300
    time: [5434, 5026, 6000]
    avg_time: 5486.666666666667
    ---------------------------

    Batch Size: 400
    time: [6537, 5731, 5695]
    avg_time: 5987.666666666667
    ---------------------------

    Batch Size: 500
    time: [5975, 5765, 5839]
    avg_time: 5859.666666666667
    ---------------------------

    Batch Size: 1000
    time: [5254, 5701, 6500]
    avg_time: 5818.333333333333
    ---------------------------
     */

}
