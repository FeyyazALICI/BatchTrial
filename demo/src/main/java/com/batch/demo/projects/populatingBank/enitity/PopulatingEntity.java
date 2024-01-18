package com.batch.demo.projects.populatingBank.enitity;

// auxilary imports
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="customer")
@Data
public class PopulatingEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String origin;
    private BigDecimal deposit;

    public PopulatingEntity(){
        // Default Constructor
    }

    public PopulatingEntity(long id, String origin, BigDecimal deposit){
        this.id=id;
        this.origin=origin;
        this.deposit=deposit;
    }
}
