package com.example.demo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@Service
public class AnalysisService {

    public Map<String, Integer> analyzeUploadedFile(
            MultipartFile file) {

        Map<String, Integer> riskCount = new HashMap<>();
        riskCount.put("Green", 0);
        riskCount.put("Yellow", 0);
        riskCount.put("Red", 0);

        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {

                if (row.getRowNum() == 0)
                    continue;

                Cell speedCell = row.getCell(1);

                if (speedCell == null)
                    continue;

                int speed =
                        (int) speedCell.getNumericCellValue();

                if (speed <= 40)
                    riskCount.put("Green",
                            riskCount.get("Green") + 1);

                else if (speed <= 60)
                    riskCount.put("Yellow",
                            riskCount.get("Yellow") + 1);

                else
                    riskCount.put("Red",
                            riskCount.get("Red") + 1);
            }

            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return riskCount;
    }
}
