package com.sinaukoding.perpustakaan.service;

import com.sinaukoding.perpustakaan.dao.BaseDao;
import com.sinaukoding.perpustakaan.dao.LoanDao;
import com.sinaukoding.perpustakaan.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
public class LoanService extends BaseService<Loan>{

    @Autowired
    private LoanDao dao;

    @Override
    protected BaseDao<Loan> getDao() {
        return dao;
    }

    @Transactional
    public Loan save(Loan entity) {
        entity.setLoanDate(new Date());

        return dao.save(entity);
    }

    @Transactional
    public Loan update(Loan entity) {
        if (entity.getId() != null) {
            Loan reference = getDao().findReference(entity.getId());

            reference.setReturnDate(entity.getReturnDate() != null
                    ? entity.getReturnDate()
                    : new Date());

            reference.setStatus(reference.getStatus().equals(Loan.StatusLoan.BORROWED)
                    ? Loan.StatusLoan.RETURNED
                    : reference.getStatus());

            entity.setLoanDate(reference.getLoanDate());
            entity.setReturnDate(reference.getReturnDate());
            entity.setStatus(reference.getStatus());

            return entity;
        }

        return null;
    }

    @Transactional
    public Collection<Loan> findByDate(Loan entity, Date startDate, Date endDate) {
        Collection<Loan> result = dao.findByDate(entity, startDate, endDate);
        return result.size() > 0 ? result : new ArrayList<>();
    }
}
