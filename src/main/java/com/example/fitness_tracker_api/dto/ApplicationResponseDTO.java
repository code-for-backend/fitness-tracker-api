package com.example.fitness_tracker_api.dto;

import lombok.NoArgsConstructor;
/*
POST:/api/application/reigister
{
"name":some_app,
"api_key":122563738
}
 */
@NoArgsConstructor
public class ApplicationResponseDTO {
    private String name;
    private String apiKey;

    public ApplicationResponseDTO(String name,String apiKey) {
        this.name = name;
        this.apiKey = apiKey;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
