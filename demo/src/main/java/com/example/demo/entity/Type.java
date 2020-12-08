package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author abliger
 * @date 2020/12/7
 * @description
 */
@Data
@TableName("Type")
public class Type {
    @TableId(type = IdType.AUTO,value = "id")
    private String id;
    @TableField("type")
    private String type;
}
