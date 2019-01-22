package com.krushidj.modules.admin.controller;

import com.krushidj.constants.MappingConstants;
import com.krushidj.modules.admin.service.AdminService;
import com.krushidj.modules.admin.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MappingConstants.adminClassLevelMapping)
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = {MappingConstants.adminUriMapping}, method = RequestMethod.POST)
    public ResponseEntity<String> saveUser(@RequestBody User user) throws Throwable {
        adminService.saveUser(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<String>("ok", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = {MappingConstants.adminUriMapping}, method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestParam("id") Long id) throws Throwable {
        adminService.deleteUser(id);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<String>("ok", responseHeaders, HttpStatus.OK);
    }

}
