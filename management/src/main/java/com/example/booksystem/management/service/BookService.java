package com.example.booksystem.management.service;

import com.example.booksystem.management.model.BookModel;
import com.example.booksystem.management.repository.BookRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;
    public BookService (BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }
    public List<BookModel> getAllBooks() {
        return bookRepo.findAll();
    }

    public BookModel updateBooks(long id, BookModel bookModel) {
        BookModel existingBook = bookRepo.findById(id).orElseThrow();
        existingBook.setTitle(bookModel.getTitle());
        return bookRepo.save(existingBook);
    }

    public ResponseEntity<HttpStatus> deleteBooks(long id) {
        bookRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public BookModel createBooks(BookModel bookModel) {
        try {
            BookModel newBook =  bookRepo.save(new BookModel(bookModel.getId(), bookModel.getAuthor(), bookModel.getTitle()));
            return bookRepo.save(newBook);
        }catch (Exception e){
            try {
                throw new Exception("Not Created");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public List<BookModel> getBookByAuthor(String author) {
        return bookRepo.findBookByAuthor(author);
    }

    public List<BookModel> getbyAuthorStartsWithT(String author) {
        return bookRepo.findBookByAuthorStartsWithT(author);
    }
}
