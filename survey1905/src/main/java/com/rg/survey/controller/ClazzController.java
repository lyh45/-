package com.rg.survey.controller;


import com.rg.survey.entity.Clazz;
import com.rg.survey.entity.Teacher;
import com.rg.survey.service.ClazzService;
import com.rg.survey.service.TeacherService;
import com.rg.survey.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://192.168.1.242:8080","http://127.0.0.1:8080","http://192.168.1.105:8081"},allowCredentials = "true")

public class ClazzController {
    @Autowired
    ClazzService clazzService;

    @GetMapping("/clazz/list")
    public R teacherList(){
        return R.ok(clazzService.list());
    }



    @DeleteMapping("/clazz/delete/{id}")
    public R teacherDelete(@PathVariable Integer id){
        return R.ok(clazzService.removeById(id));
    }

    @PostMapping("/clazz/insert")
    public R teacherInsert(@RequestBody Clazz clazz){
        return R.ok(clazzService.save(clazz));
    }

    @PutMapping("/clazz/update")
    public R teacherUpdate(@RequestBody Clazz clazz){
        return R.ok(clazzService.updateById(clazz));
    }

    @GetMapping("/clazz/select")
    public R classSelect(@RequestParam Map<String,Object> params){
        return R.ok(clazzService.selectPage(params));
    }



}
