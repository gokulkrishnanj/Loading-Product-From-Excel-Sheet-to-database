package com.example.demo.controller;

import com.example.demo.api.ExcelAPI;
import com.example.demo.dto.MyShopDTO;
import com.example.demo.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
//@Controller
public class ExcelController implements ExcelAPI {
    @Autowired
    ExcelService excelService;
    @Override
    public String healthCheck(){
        return "health";
    }

    @Override
    public ResponseEntity<String> addProduct(MyShopDTO myShopDTO){
        return new ResponseEntity<>(excelService.addProduct(myShopDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> addDataFromSheet(MultipartFile file){
        return new ResponseEntity<>(excelService.addDataFromSheet(file),HttpStatus.OK);
    }
}

