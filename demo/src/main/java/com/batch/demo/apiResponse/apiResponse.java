package com.batch.demo.apiResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class apiResponse {
    
    private String message;

    public apiResponse(String message){
        this.message = message;
    }
}
