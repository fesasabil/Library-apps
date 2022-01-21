package com.sinaukoding.perpustakaan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinaukoding.perpustakaan.common.RestResult;
import com.sinaukoding.perpustakaan.common.StatusCode;
import com.sinaukoding.perpustakaan.entity.Book;
import com.sinaukoding.perpustakaan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("books")
public class BookControlller extends BaseController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public RestResult get(@RequestParam(value = "param", required = false) String param,
                           @RequestParam(value = "offset") int offset,
                           @RequestParam(value = "limit") int limit) throws JsonProcessingException {

        Book book = param != null ? new ObjectMapper().readValue(param, Book.class) : null;

        long rows = bookService.count(book);

        return new RestResult(rows > 0 ? bookService.find(book, offset, limit) : new ArrayList<>(), rows);
    }

    @PostMapping
    public RestResult save(@RequestBody Book param) {
        param = bookService.save(param);

        return new RestResult(param, param != null ? StatusCode.SAVE_SUCCESS : StatusCode.SAVE_FAILED);
    }

    @PutMapping
    public RestResult update(@RequestBody Book param) {
        param = bookService.update(param);

        return new RestResult(param, param != null ? StatusCode.UPDATE_SUCCESS : StatusCode.UPDATE_FAILED);
    }

    @DeleteMapping(value = "{id}")
    public RestResult delete(@PathVariable Long id) {
        return new RestResult(bookService.delete(id) ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }
}
