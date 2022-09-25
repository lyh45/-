package com.rg.survey.controller;

import com.rg.survey.entity.Manager;
import com.rg.survey.service.ManagerService;
import com.rg.survey.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = {"http://192.168.1.242:8080","http://127.0.0.1:8080","http://192.168.1.105:8081"},allowCredentials = "true")

//@CrossOrigin(origins = {"http://192.168.1.105:808"},allowCredentials = "true")

public class ManagerController {
    @Autowired
    ManagerService managerService;

    @GetMapping("/manager/login")
    public R login(@PathParam("username") String username, @PathParam("password") String password){
        return R.ok(managerService.login(username,password));
    }

    @PostMapping("/manager/register")
    public R register(@RequestBody Manager manager){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        return R.ok(managerService.save(manager));
    }

}
