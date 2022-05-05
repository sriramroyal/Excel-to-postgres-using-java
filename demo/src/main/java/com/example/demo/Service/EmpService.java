package com.example.demo.Service;

import com.example.demo.EmpRepo.BookRepositoy;
import com.example.demo.EmpRepo.EmpRepository;
import com.example.demo.EntityPackage.Book;
import com.example.demo.EntityPackage.EmpData;
import com.example.demo.Helper.BookHelper;
import com.example.demo.Helper.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EmpService {

    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private BookRepositoy bookRepositoy;

    public void save( MultipartFile file) throws IOException {

        List<EmpData> empData = ExcelHelper.ExcelToList(file.getInputStream());

        this.empRepository.saveAll(empData);

    }
    public void saveBook(MultipartFile file)throws IOException{
        List<Book> book= BookHelper.ExcelConvert(file.getInputStream());
        this.bookRepositoy.saveAll(book);
    }


    public List<EmpData> getAllData(){

        return this.empRepository.findAll();
    }
}
