package com.example.demo.Helper;

import com.example.demo.EntityPackage.Book;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookHelper {
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
    public static List<Book> ExcelConvert(InputStream is) {
        List<Book> list = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
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

                Book book=new Book();

                while (cells.hasNext()) {
                    Cell cell = cells.next();


                    switch (cid) {
                        case 0:
                            book.setProductId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            book.setProductName(cell.getStringCellValue());
                            break;
                        case 2:
                            book.setProductDesc(cell.getStringCellValue());
                            break;
                        case 3:
                            book.setProductPrice(cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                //Removing Special Characters
//                empData.setId(regexonString(book.getProductId(),true));
                book.setProductName(regexonString(book.getProductName(),true));
                book.setProductDesc(regexonString(book.getProductDesc(),false));
//                book.setProductPrice(regexonString(book.getProductPrice(),false));
//                empData.setDomain(regexonString(empData.getDomain(),true));
                list.add(book);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
}
