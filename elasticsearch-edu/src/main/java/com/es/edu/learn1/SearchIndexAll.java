package com.es.edu.learn1;

import com.es.edu.util.JSONChange;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

public class SearchIndexAll {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 查询索引 - 请求对象
        GetIndexRequest request = new GetIndexRequest("*");
        // 发送请求，获取响应
        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);

        System.out.println("indices:" + JSONChange.objToJson(response.getIndices()));
        System.out.println("aliases:" + response.getAliases());
        System.out.println("mappings:" + JSONChange.objToJson(response.getMappings()));
        System.out.println("settings:" + response.getSettings());

        client.close();
    }
}
