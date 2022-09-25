package com.rg.survey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rg.survey.entity.Student;
import com.rg.survey.service.StudentService;
import com.rg.survey.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




