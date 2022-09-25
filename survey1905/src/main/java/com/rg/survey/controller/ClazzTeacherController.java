package com.rg.survey.controller;

import com.rg.survey.entity.ClassTeacher;
import com.rg.survey.service.ClassTeacherService;
import com.rg.survey.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://192.168.1.242:8080","http://127.0.0.1:8080","http://192.168.1.105:8081"},allowCredentials = "true")

public class ClazzTeacherController {
    @Autowired
    ClassTeacherService classTeacherService;

    @DeleteMapping("/clazzteacher/delete")
    public R clazzteacherDelete(Integer cid,Integer tid){
        return R.ok(classTeacherService.removeTeacher(cid,tid));
    }

    @PostMapping("/clazzteacher/insert")//添加老师
    @Transactional
    public R clazzteacherInsert(@RequestBody List<ClassTeacher> list){
        //saveBatch(传入集合)
        return R.ok(classTeacherService.saveBatch(list));
    }

}
