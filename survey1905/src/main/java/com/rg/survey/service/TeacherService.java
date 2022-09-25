package com.rg.survey.service;

import com.rg.survey.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rg.survey.vo.P;

import java.util.Map;

/**
 *
 */
public interface TeacherService extends IService<Teacher> {

    P selectPage(Map<String, Object> params);
}
