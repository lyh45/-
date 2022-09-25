package com.rg.survey.service;

import com.rg.survey.entity.SurveyItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rg.survey.vo.P;

import java.util.Map;

/**
 *
 */
public interface SurveyItemService extends IService<SurveyItem> {

    P selectPage(Map<String, Object> map);
}
