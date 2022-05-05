package com.example.demo.EmpRepo;

import com.example.demo.EntityPackage.Book;
import com.example.demo.EntityPackage.EmpData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<EmpData,String> {

}
