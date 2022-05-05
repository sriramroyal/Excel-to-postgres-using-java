package com.example.demo.EmpRepo;

import com.example.demo.EntityPackage.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoy extends JpaRepository<Book, Integer> {

}
