package com.rg.survey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.rg.survey.entity.*;
import com.rg.survey.service.*;
import com.rg.survey.mapper.ClazzMapper;
import com.rg.survey.vo.ClazzVo;
import com.rg.survey.vo.P;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz>
    implements ClazzService{
    @Autowired
    StudentService studentService;
    @Autowired
    GradeService gradeService;
    @Autowired
    ClassTeacherService classTeacherService;
    @Autowired
    TeacherService teacherService;
    @Override
    public P selectPage(Map<String, Object> map) {
        //构建多条件
        LambdaQueryWrapper<Clazz> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(map.containsKey("gradeId") && !StringUtils.isEmpty(map.get("gradeId").toString()),Clazz::getGradeId,map.get("gradeId"));
        wrapper.like(map.containsKey("className")&& !StringUtils.isEmpty(map.get("className").toString()),Clazz::getClassName,map.get("className"));
        wrapper.eq(map.containsKey("status")&& !StringUtils.isEmpty(map.get("status").toString()),Clazz::getStatus,map.get("status"));

        //日期降序排序
        wrapper.orderByDesc(Clazz::getCreateTime);

        //判断分页
        if(map.containsKey("page")) {
            long current = Long.parseLong(map.get("page").toString());
//            System.out.println(current);
            long size = Long.parseLong(map.get("size").toString());
            IPage<Clazz> page = new Page<>(current, size);//创建分页对象
            page = this.page(page, wrapper);
            List<Clazz> classList = page.getRecords();
            //把clazz类型集合转为clazzVo类型

            return new P(page.getTotal(), getClazzVo(classList));
        }else{
            List<Clazz> classList = this.list(wrapper);

            return new P(0L,getClazzVo(classList));
        }
    }

    private List<ClazzVo> getClazzVo(List<Clazz> clazzList) {
        List<ClazzVo> voList = clazzList.stream().map(clazz -> {
            ClazzVo vo = new ClazzVo();
            BeanUtils.copyProperties(clazz,vo);
            //查询年级名称
            Grade grade = gradeService.getById(clazz.getGradeId());

            vo.setGradeName(grade.getGradeName());

            //查询班级人数
            LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Student::getClassId,clazz.getId());
            int count = studentService.count(wrapper);//查询班级人数
            vo.setCount(count);
            //查询老师集合
            LambdaQueryWrapper<ClassTeacher> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(ClassTeacher::getClassId,clazz.getId());
            List<ClassTeacher> classTeacherList = classTeacherService.list(wrapper1);
            List<Teacher> teacherList = new ArrayList<>();
            //判断是否有带班老师
            if(classTeacherList.size() > 0){
                //取出代办级的老师id stream（）获取流 map()中间操作
                List<Integer> tidlist = classTeacherList.stream().map(ClassTeacher::getTeacherId).collect(Collectors.toList());
                //通过老师id查询对应老师
                teacherList = teacherService.listByIds(tidlist);
            }
            vo.setTeacherList(teacherList);
            return vo;
        }).collect(Collectors.toList());
        return voList;
    }
}




