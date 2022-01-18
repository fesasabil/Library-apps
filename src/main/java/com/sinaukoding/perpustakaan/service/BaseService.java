package com.sinaukoding.perpustakaan.service;

import com.sinaukoding.perpustakaan.dao.BaseDao;
import com.sinaukoding.perpustakaan.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public abstract class BaseService<T extends BaseEntity<T>> {

    protected abstract BaseDao<T> getDao();

    @Transactional(readOnly = true)
    public T findOne(T param) {
        return getDao().findOne(param);
    }

    @Transactional(readOnly = true)
    public Collection<T> find(T param, int offset, int limit) {
        return getDao().find(param, offset, limit);
    }

    @Transactional(readOnly = true)
    public Long count(T param) {
        return getDao().count(param);
    }

    @Transactional
    public T save(T entity) {
        return getDao().save(entity);
    }

    @Transactional
    public T update(T entity) {
        if (entity.getId() != null) {
            return getDao().update(entity);
        }

        return null;
    }

    @Transactional
    public boolean delete(Long id) {
        return getDao().delete(getDao().findReference(id)) != null;
    }
}
