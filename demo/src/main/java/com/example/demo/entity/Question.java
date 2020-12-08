package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.lang.reflect.Type;

/**
 * @author abliger
 * @date 2020/12/7
 * @description
 */
@Data
@TableName("question")
public class Question {
    @TableId(type = IdType.AUTO,value = "id")
    private String id;
    @TableField("question")
    private String question;
    @TableField("type")
    private String type;


}
