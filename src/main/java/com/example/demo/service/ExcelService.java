package com.example.demo.service;

import com.example.demo.dto.MyShopDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ExcelService {
    String addProduct(MyShopDTO myShopDTO);

    String addDataFromSheet(MultipartFile file);
}
