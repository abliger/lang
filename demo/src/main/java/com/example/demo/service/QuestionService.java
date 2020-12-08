package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Question;
import com.example.demo.entity.QuestionVo;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author abliger
 * @date 2020/12/7
 * @description
 */

public interface QuestionService {
    public Page<Question> show(Integer page, Integer limit);

    boolean update(Question question);

    Map<String, List<String>> getQuestionVo() throws UnsupportedEncodingException, FileNotFoundException;

}
