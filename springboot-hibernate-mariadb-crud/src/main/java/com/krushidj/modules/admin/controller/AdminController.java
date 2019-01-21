package com.krushidj.modules.admin.controller;

import com.krushidj.modules.admin.service.AdminService;
import com.krushidj.modules.admin.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = {"/user"}, method = RequestMethod.POST)
    public ResponseEntity<String> saveUser(@RequestBody User user) throws Throwable {
        adminService.saveUser(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<String>("", responseHeaders, HttpStatus.OK);
    }

}
