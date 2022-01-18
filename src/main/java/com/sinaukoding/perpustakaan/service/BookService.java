package com.sinaukoding.perpustakaan.service;

import com.sinaukoding.perpustakaan.dao.BaseDao;
import com.sinaukoding.perpustakaan.dao.BookDao;
import com.sinaukoding.perpustakaan.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService extends BaseService<Book> {

    @Autowired
    private BookDao dao;

    @Override
    protected BaseDao<Book> getDao() {
        return dao;
    }
}
