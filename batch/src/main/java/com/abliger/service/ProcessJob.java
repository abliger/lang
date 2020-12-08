package com.abliger.service;

import com.abliger.entity.Question;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author abliger
 * @date 2020/12/7
 * @description
 */
@Component
public class ProcessJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    // 注入数据源
    @Autowired
    private DataSource dataSource;

    @Bean
    public Job dataSourceItemReaderJob() throws Exception {
        return jobBuilderFactory.get("dataSourceItemReaderJob")
                .start(step())
                .build();
    }

    private Step step() throws Exception {
        final TaskletStep step = stepBuilderFactory.get("step")
                .<Question, Question>chunk(2)
                .writer(dataSourceItemWriter())
                .reader(dataSourceItemReader())
                .build();
        return step;
    }

    private ItemReader<Question> dataSourceItemReader() throws Exception {
        JdbcPagingItemReader<Question> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource); // 设置数据源
        reader.setFetchSize(5); // 每次取多少条记录
        reader.setPageSize(5); // 设置每页数据量

        // 指定sql查询语句 select id,field1,field2,field3 from TEST
        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
        provider.setSelectClause("id,question,type"); //设置查询字段
        provider.setFromClause("from question"); // 设置从哪张表查询
        // 将读取到的数据转换为TestData对象
        provider.setWhereClause("where question.type!=0 and question.question is not null");
        reader.setRowMapper((resultSet, rowNum) -> {
                Question data = new Question();
                data.setId(resultSet.getString(1));
                data.setQuestion(resultSet.getString(2)); // 读取第一个字段，类型为String
                data.setType(resultSet.getString(3));
                return data;
        });
        reader.setSaveState(false);

        Map<String, Order> sort = new HashMap<>(1);
        sort.put("id", Order.ASCENDING);
        provider.setSortKeys(sort); // 设置排序,通过id 升序

        reader.setQueryProvider(provider);
        // 设置namedParameterJdbcTemplate等属性
        reader.afterPropertiesSet();
        return reader;
    }

    private ItemWriter<Question> dataSourceItemWriter() {
        // ItemWriter的实现类之一，mysql数据库数据写入使用JdbcBatchItemWriter，
        // 其他实现：MongoItemWriter,Neo4jItemWriter等
        JdbcBatchItemWriter<Question> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource); // 设置数据源
        String sql = "insert into question_copy3(id,question,type) values (:id,:question,:type)";
        writer.setSql(sql); // 设置插入sql脚本

        // 映射TestData对象属性到占位符中的属性
        BeanPropertyItemSqlParameterSourceProvider<Question> provider = new BeanPropertyItemSqlParameterSourceProvider<>();
        writer.setItemSqlParameterSourceProvider(provider);

        writer.afterPropertiesSet(); // 设置一些额外属性
        return writer;
    }
}
