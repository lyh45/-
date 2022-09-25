package com.rg.survey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rg.survey.entity.ClassTeacher;
import com.rg.survey.service.ClassTeacherService;
import com.rg.survey.mapper.ClassTeacherMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ClassTeacherServiceImpl extends ServiceImpl<ClassTeacherMapper, ClassTeacher>
    implements ClassTeacherService{

    @Override
    public boolean removeTeacher(Integer cid, Integer tid) {
        LambdaQueryWrapper<ClassTeacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClassTeacher::getClassId,cid);
        wrapper.eq(ClassTeacher::getTeacherId,tid);
        return this.remove(wrapper);
    }
}




