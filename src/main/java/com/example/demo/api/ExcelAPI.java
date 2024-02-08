package com.example.demo.api;


import com.example.demo.dto.MyShopDTO;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RequestMapping(value = "${excel}")
public interface ExcelAPI {
    @GetMapping(value = "${health}")
    String healthCheck();

    @PostMapping(value = "${addProduct}")
    ResponseEntity<String> addProduct(@Valid @RequestBody MyShopDTO myShopDTO);

    @PostMapping(value = "${addDataFromSheet}")
    ResponseEntity<String> addDataFromSheet(@RequestParam(value = "file") MultipartFile file);
}
