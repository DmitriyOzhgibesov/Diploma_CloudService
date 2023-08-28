package com.example.diploma_cloudservice.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.diploma_cloudservice.Dto.FileResponse;
import com.example.diploma_cloudservice.Service.FileService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/list")
public class ListController {

    private final FileService service;

    @GetMapping
    List<FileResponse> getAllFiles(@RequestHeader("auth-token") String authToken, @RequestParam("limit") Integer limit) {
        return service.getAllFiles(authToken, limit);
    }
}
