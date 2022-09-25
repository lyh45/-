package com.rg.survey.controller;


import com.rg.survey.entity.Teacher;
import com.rg.survey.service.TeacherService;
import com.rg.survey.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://192.168.1.242:8080","http://127.0.0.1:8080","http://192.168.1.105:8081"},allowCredentials = "true")
//saaa
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("/teacher/list")
    public R teacherList(){
        return R.ok(teacherService.list());
    }



    @DeleteMapping("/teacher/delete/{id}")
    public R teacherDelete(@PathVariable Integer id){
        return R.ok(teacherService.removeById(id));
    }

    @PostMapping("/teacher/insert")
    public R teacherInsert(@RequestBody Teacher teacher){
        return R.ok(teacherService.save(teacher));
    }

    @PutMapping("/teacher/update")
    public R teacherUpdate(@RequestBody Teacher teacher){
        return R.ok(teacherService.updateById(teacher));
    }

    @GetMapping("/teacher/select")
    public R teacherSelect(@RequestParam Map<String,Object> params){
        return R.ok(teacherService.selectPage(params));
    }



}
