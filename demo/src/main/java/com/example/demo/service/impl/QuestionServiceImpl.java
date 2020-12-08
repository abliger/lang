package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Question;
import com.example.demo.entity.QuestionVo;
import com.example.demo.entity.Type;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.TypeMapper;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author abliger
 * @date 2020/12/7
 * @description
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Page<Question> show( Integer page, Integer limit) {
        return questionMapper.selectPage(new Page<>(page,limit), null);
    }

    @Override
    public boolean update(Question question) {
        final int i = questionMapper.updateById(question);
        if(i!=1){
            return false;
        }
        return true;
    }

    @Override
    public Map<String, List<String>> getQuestionVo() throws  FileNotFoundException {
        final List<Question> questionVoList = questionMapper.getQuestionVo();
        final Map<String, List<String>> collect = questionVoList
                .stream()
                .collect(Collectors.groupingBy(Question::getType, Collectors.mapping(Question::getQuestion, Collectors.toList())));
        System.out.println(collect);
        PrintStream out=new PrintStream(new File(System.getProperty("user.dir")+"/面试题.md"));
        System.setOut(out);
        for (Map.Entry<String, List<String>> stringListEntry : collect.entrySet()) {
            String key = stringListEntry.getKey();
            List<String> value = stringListEntry.getValue();
            key="<h1>"+key+"</h1>";
            System.out.println(key);
            value.forEach(System.out::println);
        }
        return collect;
    }
}
