package com.rg.survey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rg.survey.entity.ClassTeacher;
import com.rg.survey.entity.Teacher;
import com.rg.survey.service.ClassTeacherService;
import com.rg.survey.service.TeacherService;
import com.rg.survey.mapper.TeacherMapper;
import com.rg.survey.vo.P;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{
    @Autowired
    ClassTeacherService classTeacherService;
    @Override
    public P selectPage(Map<String, Object> map) {
        //构建多条件
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(map.containsKey("id") && !StringUtils.isEmpty(map.get("id").toString()),Teacher::getId,map.get("id"));
        wrapper.like(map.containsKey("teacherName")&& !StringUtils.isEmpty(map.get("teacherName").toString()),Teacher::getTeacherName,map.get("teacherName"));
        wrapper.eq(map.containsKey("status")&& !StringUtils.isEmpty(map.get("status").toString()),Teacher::getStatus,map.get("status"));

        //判断是否包含cid，是否不带班老师
        if(map.containsKey("cid") && !StringUtils.isEmpty(map.get("cid").toString())){
            //根据cid查询带班老师集合
            LambdaQueryWrapper<ClassTeacher> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(ClassTeacher::getClassId,map.get("cid"));
            List<ClassTeacher> classTeacherList = classTeacherService.list(wrapper1);
            //从带班老师集合中取出老师id
            List<Integer> tidList = classTeacherList.stream().map(ClassTeacher::getTeacherId).collect(Collectors.toList());
            //判断该班是否有带班老师
            if(tidList.size() > 0){
                //构建不带这个班的老师, 从元老的老师条件中排除这个班的老师id
                wrapper.notIn(Teacher::getId,tidList);
            }
        }
        //日期降序排序
        wrapper.orderByDesc(Teacher::getCreateTime);

        //判断分页
        if(map.containsKey("page")) {
            long current = Long.parseLong(map.get("page").toString());
//            System.out.println(current);
            long size = Long.parseLong(map.get("size").toString());
            Page<Teacher> page = new Page<>(current, size);//创建分页对象
            page = this.page(page, wrapper);
//            System.out.println(page.getRecords());
            return new P(page.getTotal(), page.getRecords());
        }else{
            return new P(0L,this.list(wrapper));
        }



    }
}




