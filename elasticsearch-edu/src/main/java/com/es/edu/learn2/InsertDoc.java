package com.es.edu.learn2;

import com.es.edu.common.ConnectElasticsearch;
import com.es.edu.common.Teacher;
import com.es.edu.common.User;
import com.es.edu.util.JSONChange;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;

import org.elasticsearch.xcontent.XContentType;

public class InsertDoc {

    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            // 新增文档 - 请求对象
            IndexRequest request = new IndexRequest();
            // 设置索引及唯一性标识
            request.index("teacher").id("1001");

            // 创建数据对象
            Teacher teacher = new Teacher();
            teacher.setName("zhangsan");
            teacher.setAge(30);
            teacher.setSex("男");

            // 添加文档数据，数据格式为 JSON 格式
            request.source(JSONChange.objToJson(teacher), XContentType.JSON);
            // 客户端发送请求，获取响应对象
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            // 打印结果信息
            System.out.println("_index:" + response.getIndex());
            System.out.println("_id:" + response.getId());
            System.out.println("_result:" + response.getResult());
            System.out.println(JSONChange.objToJson(response));
        });
    }
}