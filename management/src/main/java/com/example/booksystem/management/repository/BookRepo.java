package com.example.booksystem.management.repository;

import com.example.booksystem.management.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<BookModel, Long> {
    List<BookModel> findBookByAuthor(String author);
    @Query(nativeQuery = true, value = "select * from book_model where author like :author || '%'")
    List<BookModel> findBookByAuthorStartsWithT(@Param("author") String author);
}


