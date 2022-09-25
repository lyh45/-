package com.rg.survey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rg.survey.entity.SurveyItem;
import com.rg.survey.entity.Teacher;
import com.rg.survey.service.SurveyItemService;
import com.rg.survey.mapper.SurveyItemMapper;
import com.rg.survey.vo.P;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 *
 */
@Service
public class SurveyItemServiceImpl extends ServiceImpl<SurveyItemMapper, SurveyItem>
    implements SurveyItemService{

    @Override
    public P selectPage(Map<String, Object> map) {
        LambdaQueryWrapper<SurveyItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(map.containsKey("surveyText")&& !StringUtils.isEmpty(map.get("surveyText").toString()),SurveyItem::getSurveyText,map.get("surveyText"));

        wrapper.orderByDesc(SurveyItem::getChecked,SurveyItem::getCreateTime);

        //判断分页
        if(map.containsKey("page")) {
            long current = Long.parseLong(map.get("page").toString());
//            System.out.println(current);
            long size = Long.parseLong(map.get("size").toString());
            Page<SurveyItem> page = new Page<>(current, size);//创建分页对象
            page = this.page(page, wrapper);
//            System.out.println(page.getRecords());
            return new P(page.getTotal(), page.getRecords());
        }else{
            return new P(0L,this.list(wrapper));
        }

    }
}




