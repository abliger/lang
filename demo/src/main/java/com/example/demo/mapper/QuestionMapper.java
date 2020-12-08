package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Question;
import com.example.demo.entity.QuestionVo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author abliger
 * @date 2020/12/7
 * @description
 */
@Component
public interface QuestionMapper extends BaseMapper<Question> {
    @Select("select id,question,type from question")
    public List<Question> show();

    List<Question> getQuestionVo();
}
