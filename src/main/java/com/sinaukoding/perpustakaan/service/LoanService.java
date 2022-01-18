package com.sinaukoding.perpustakaan.service;

import com.sinaukoding.perpustakaan.dao.BaseDao;
import com.sinaukoding.perpustakaan.dao.LoanDao;
import com.sinaukoding.perpustakaan.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService extends BaseService<Loan>{

    @Autowired
    private LoanDao dao;

    @Override
    protected BaseDao<Loan> getDao() {
        return dao;
    }
}
