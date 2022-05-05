package com.example.demo.Helper;

import com.example.demo.EntityPackage.EmpData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExcelHelper {
    private static String myName;
    public static String regexonString(String str,boolean flag){
        if (flag==false){
            str=str.replaceAll("[^a-zA-Z0-9]&&[#+./,]","");
        }
        else {
            str=str.replaceAll("[^a-zA-Z0-9]","");
        }
        return str;
    }
    public static boolean CheckFile(MultipartFile file){

        String contentType = file.getContentType();
        String FileName=file.getOriginalFilename();
        System.out.println(FileName);
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean gotIt(MultipartFile file) {
        myName=file.getOriginalFilename();

        return false;
    }
    public static List<EmpData> ExcelToList(InputStream is) {
        List<EmpData> list = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("sampleData");
//            XSSFName fileName=workbook.getName();
            System.out.println(sheet);

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                EmpData empData = new EmpData();

                while (cells.hasNext()) {
                    Cell cell = cells.next();


                    switch (cid) {
                        case 0:
                            empData.setSno((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            empData.setId(cell.getStringCellValue());
                            break;
                        case 2:
                            empData.setName(cell.getStringCellValue());
                            break;
                        case 3:
                            empData.setPrimaryskill(cell.getStringCellValue());
                            break;
                        case 4:
                            empData.setSecondaryskill(cell.getStringCellValue());
                            break;
                        case 5:
                            empData.setDomain(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                //Removing Special Characters
                empData.setId(regexonString(empData.getId(),true));
                empData.setName(regexonString(empData.getName(),true));
                empData.setPrimaryskill(regexonString(empData.getPrimaryskill(),false));
                empData.setSecondaryskill(regexonString(empData.getSecondaryskill(),false));
                empData.setDomain(regexonString(empData.getDomain(),true));
                list.add(empData);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
}
