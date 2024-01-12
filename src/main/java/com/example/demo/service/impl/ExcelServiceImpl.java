package com.example.demo.service.impl;

import com.example.demo.dto.MyShopDTO;
import com.example.demo.dto.ProductDetailDTO;
import com.example.demo.models.entity.Myshop;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {
    Log LOG = LogFactory.getLog(ExcelServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    public String addProduct(MyShopDTO myShopDTO){
        Myshop myshop = new Myshop();
        myshop.setPrice(myShopDTO.getPrice());
        myshop.setPartId(myShopDTO.getPartId());
        myshop.setQuantity(myShopDTO.getQuantity());
        productRepository.save(myshop);
        return "Product Added";
    }

    public String addDataFromSheet(MultipartFile file){
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            int rowIndex = 0;
            Map<String,ProductDetailDTO> productDetailDTOMap = new HashMap<>();
            while (rowIterator.hasNext()){
                Row cuurentRow = rowIterator.next();
                if(rowIndex==0){
                    rowIndex++;
                    continue;
                }
                Myshop myshop = new Myshop();
                Iterator<Cell> cellIterator = cuurentRow.iterator();
                int cellIndex = 0;
                while (cellIterator.hasNext()){
                    Cell currentCell = cellIterator.next();
                    ProductDetailDTO productDetailDTO = productDetailDTOMap.get(myshop.getPartId());
                    if(productDetailDTO==null){
                        productDetailDTO = new ProductDetailDTO();
                    }
                    switch (cellIndex){
                        case 0:
                            LOG.info("partId:"+ currentCell.getStringCellValue());
                            myshop.setPartId(currentCell.getStringCellValue());
                            break;
                        case 1:
                            LOG.info("quantity:"+currentCell.getNumericCellValue());
                            myshop.setQuantity((int)currentCell.getNumericCellValue());
                            productDetailDTO.setQuantity((int)currentCell.getNumericCellValue());
                            break;
                        case 2:
                            LOG.info("price:"+currentCell.getNumericCellValue());
                            myshop.setPrice(currentCell.getNumericCellValue());
                            productDetailDTO.setPrice(currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    productDetailDTOMap.put(myshop.getPartId(),productDetailDTO);
                    cellIndex++;
                }

            }
            LOG.info("dto--->"+productDetailDTOMap);
            productDetailDTOMap.forEach((partId, productDetailDTO) -> {
                Myshop myshop = new Myshop();
                myshop.setPartId(partId);
                LOG.info("quantity:"+productDetailDTO.getQuantity());
                myshop.setQuantity(productDetailDTO.getQuantity());
                myshop.setPrice(productDetailDTO.getPrice());
                productRepository.save(myshop);
            });
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        return "Product Added";
    }

}
