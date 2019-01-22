package com.krushidj.modules.admin.service;

import com.krushidj.constants.TableConstants;
import com.krushidj.modules.admin.dao.AdminDao;
import com.krushidj.modules.admin.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    public void saveUser(User user) throws Throwable {
        adminDao.saveUser(user);
    }

    public void deleteUser(Long id) throws Throwable {
        adminDao.deleteUser(TableConstants.userTable, id);
    }
}
