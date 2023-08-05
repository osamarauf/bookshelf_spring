package com.example.booksystem.management.controller;

import com.example.booksystem.management.model.BookModel;
import com.example.booksystem.management.service.BookService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

//    @Autowired
    BookService bookService;
    BookController (BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("readBooks")
    public List<BookModel> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<BookModel> updateBooks(@PathVariable long id, @RequestBody BookModel bookModel){
        BookModel updatedbook = bookService.updateBooks(id, bookModel);
        return ResponseEntity.ok(updatedbook);
    }

    @DeleteMapping("delete")
    public ResponseEntity<HttpStatus> deleteBooks(@RequestParam("id") long id) {
        return bookService.deleteBooks(id);
    }

    @PostMapping("create")
    public ResponseEntity<HttpStatus> createBooks(@RequestBody BookModel bookModel){
        BookModel createdbook = bookService.createBooks(bookModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("byauthor/{author}")
    public List<BookModel> getbyAuthor(@PathVariable String author){
        return bookService.getBookByAuthor(author);
    }

    @GetMapping("byauthor")
    public List<BookModel> getbyAuthorStartsWithT(@PathParam("author") String author){
        return bookService.getbyAuthorStartsWithT(author);
    }
}
