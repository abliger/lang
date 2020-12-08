package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Question;
import com.example.demo.entity.QuestionVo;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author abliger
 * @date 2020/12/7
 * @description
 */
@CrossOrigin
@RestController
public class Show {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/show/{page}/{limit}")
    public Page<Question> show(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        return questionService.show(page,limit);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody Question question) {
        return questionService.update(question);
    }
    @GetMapping("/getQuestionVo")
    public Map<String, List<String>> getQuestionVo() throws UnsupportedEncodingException, FileNotFoundException {
        return questionService.getQuestionVo();
    }
}
