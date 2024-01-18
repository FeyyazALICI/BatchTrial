package com.batch.demo.projects.populatingBank.service.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchDTO {

    @JsonProperty("batchSize")
    private double batchSize;

    public BatchDTO(){
        // Default Empty Constructor
    }

    public BatchDTO(double batchSize){
        this.batchSize = batchSize;
    }
}
