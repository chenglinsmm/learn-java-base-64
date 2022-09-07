package com.es.edu.learn1;

import com.es.edu.util.JSONChange;
import org.apache.http.HttpHost;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class ElasticsearchConnectNew {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();



        //		...
        System.out.println(JSONChange.objToJson(restClient));
        // 关闭客户端连接
        restClient.close();
    }
}
