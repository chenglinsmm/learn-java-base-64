package com.es.edu.learn2;

import com.es.edu.util.JSONChange;
import org.apache.http.HttpHost;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

public class CreateIndex {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 创建索引 - 请求对象
        CreateIndexRequest request = new CreateIndexRequest("teacher");
        // 发送请求，获取响应
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(JSONChange.objToJson(response));
        boolean acknowledged = response.isAcknowledged();
        // 响应状态
        System.out.println("操作状态 = " + acknowledged);
        // 关闭客户端连接
        client.close();



    }

}
