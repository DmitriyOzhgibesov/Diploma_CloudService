package com.example.diploma_cloudservice.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileResponse {

    @JsonProperty("filename")
    private String fileName;

    @JsonProperty("size")
    private Long size;

}
