package com.rg.survey.service;

import com.rg.survey.entity.ClassTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rg.survey.vo.P;

/**
 *
 */
public interface ClassTeacherService extends IService<ClassTeacher> {

    boolean removeTeacher(Integer cid, Integer tid);

}
