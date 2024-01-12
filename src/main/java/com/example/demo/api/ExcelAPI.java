package com.example.demo.api;


import com.example.demo.dto.MyShopDTO;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("excel/api/v1")
public interface ExcelAPI {
    @GetMapping(value = "/health")
    String healthCheck();

    @PostMapping(value = "/addProduct")
    ResponseEntity<String> addProduct(@RequestBody MyShopDTO myShopDTO);

    @PostMapping(value = "/addDataFromSheet")
    ResponseEntity<String> addDataFromSheet(@RequestParam(value = "file") MultipartFile file);
}
