package com.krushidj.modules.admin.dao;

import com.krushidj.utils.MethodUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AdminDao<T> {
    @Autowired
    private MethodUtil<T> util;

    public void saveUser(T instance) throws Throwable {
        util.save(instance);
    }


}
