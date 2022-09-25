package com.rg.survey.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @TableName teacher
 */
@TableName(value ="teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Serializable {
    /**
     * 老师id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 老师姓名
     */
    private String teacherName;

    /**
     * 老师性别,0女,1男
     */
    private Integer gender;

    /**
     * 老师手机号码(登录账号)
     */
    private String phonenumber;

    /**
     * 出生日期
     */
    private Date bornday;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime = new Date();

    /**
     * 状态,1正常,2休假,3离职,4退休
     */
    private Integer status = 1;

    /**
     * 逻辑删除,0正常,1删除
     */
    private Integer isDelete = 0;

    /**
     * 乐观锁
     */
    private Integer version = 1;

    /**
     * 权限等级,0为管理员,1为老师
     */
    private Integer level = 0;

    /**
     * 登录令牌
     */
    private String token = "";

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}