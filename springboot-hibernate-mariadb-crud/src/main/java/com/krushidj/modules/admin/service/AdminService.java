package com.krushidj.modules.admin.service;

import com.krushidj.modules.admin.dao.AdminDao;
import com.krushidj.modules.admin.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;

    public void saveUser(User contact) throws Throwable {
        adminDao.saveUser(contact);

    }
}
