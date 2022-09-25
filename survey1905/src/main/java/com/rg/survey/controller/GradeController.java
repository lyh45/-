package com.rg.survey.controller;

import com.rg.survey.service.GradeService;
import com.rg.survey.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://192.168.1.242:8080","http://127.0.0.1:8080","http://192.168.1.105:8081"},allowCredentials = "true")

public class GradeController {
    @Autowired
    GradeService gradeService;

    @GetMapping("/grade/list")
    public R gradeList(){
        return R.ok(gradeService.list());
    }
}
