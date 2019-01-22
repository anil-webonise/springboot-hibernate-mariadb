package com.krushidj.modules.admin.dao;

import com.krushidj.utils.MethodUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class AdminDao<T> {
    @Autowired
    private MethodUtil<T> methodUtil;

    public void saveUser(T instance) throws Throwable {
        methodUtil.save(instance);
    }


}
