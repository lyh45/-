package com.rg.survey.vo;

import com.rg.survey.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class P {
    private Long total;
    private List<?> list;
}
