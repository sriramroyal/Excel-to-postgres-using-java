package com.example.demo.Controller;

import com.example.demo.EntityPackage.EmpData;
import com.example.demo.Helper.BookHelper;
import com.example.demo.Helper.ExcelHelper;
import com.example.demo.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class EmpController {

    @Autowired
    private EmpService empService;

    @PostMapping("/fileUpload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file) throws IOException
    {
        if(ExcelHelper.gotIt(file)){
            String FileName=file.getOriginalFilename();
            System.out.println(FileName);
        }

        if (ExcelHelper.CheckFile(file)){

            this.empService.save(file);

            return ResponseEntity.ok(Map.of("Message","The given file is Excel"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload Excel File only!!");
    }

    @PostMapping("/fileUploadBook")
    public  ResponseEntity<?> uploadBook(@RequestParam("file")MultipartFile file)throws IOException{
        if(ExcelHelper.gotIt(file)){
            String FileName=file.getOriginalFilename();
            System.out.println(FileName);
        }

        if (BookHelper.CheckFile(file)){

            this.empService.saveBook(file);

            return ResponseEntity.ok(Map.of("Message","The given file is Excel"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload Excel File only!!");
    }

    @GetMapping("/")
    public List<EmpData> getAllData(){
        return this.empService.getAllData();
    }
}
