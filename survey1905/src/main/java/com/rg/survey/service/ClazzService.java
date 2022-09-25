package com.rg.survey.service;

import com.rg.survey.entity.Clazz;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rg.survey.vo.P;

import java.util.Map;

/**
 *
 */
public interface ClazzService extends IService<Clazz> {

    P selectPage(Map<String, Object> params);

}
