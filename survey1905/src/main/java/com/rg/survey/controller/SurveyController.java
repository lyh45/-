package com.rg.survey.controller;

import com.rg.survey.entity.SurveyItem;
import com.rg.survey.entity.Teacher;
import com.rg.survey.service.SurveyItemService;
import com.rg.survey.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://192.168.1.242:8080","http://127.0.0.1:8080","http://192.168.1.105:8081"},allowCredentials = "true")

public class SurveyController {
    @Autowired
    SurveyItemService surveyItemService;
    @GetMapping("/survey/list")
    public R surveyList(){
        return R.ok(surveyItemService.list());
    }



    @DeleteMapping("/survey/delete/{id}")
    public R surveyDelete(@PathVariable Integer id){
        return R.ok(surveyItemService.removeById(id));
    }

    @PostMapping("/survey/insert")
    public R surveyInsert(@RequestBody SurveyItem surveyItem){
        return R.ok(surveyItemService.save(surveyItem));
    }

    @PutMapping("/survey/update")
    public R surveyUpdate(@RequestBody SurveyItem surveyItem){
        return R.ok(surveyItemService.updateById(surveyItem));
    }

    @GetMapping("/survey/select")
    public R surveySelect(@RequestParam Map<String,Object> map){
        return R.ok(surveyItemService.selectPage(map));
    }

}
