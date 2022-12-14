package com.es.edu.learn1;


import com.es.edu.util.JSONChange;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ElasticsearchConnect {

    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        //		...
        System.out.println(JSONChange.objToJson(client));
        // 关闭客户端连接
        client.close();
    }

}
