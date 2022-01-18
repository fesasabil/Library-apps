package com.sinaukoding.perpustakaan.service;

import com.sinaukoding.perpustakaan.dao.BaseDao;
import com.sinaukoding.perpustakaan.entity.BaseEntity;

public abstract class BaseService<T extends BaseEntity<T>> {

    protected abstract BaseDao<T> getDao();

    
}
