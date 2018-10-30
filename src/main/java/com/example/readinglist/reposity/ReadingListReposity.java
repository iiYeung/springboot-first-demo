package com.example.readinglist.reposity;

import com.example.readinglist.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingListReposity extends JpaRepository<Book, Long> {

    List<Book> findByReader(String reader);
}
