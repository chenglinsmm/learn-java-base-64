package com.es.edu.learn1;

import com.es.edu.common.ConnectElasticsearch;
import com.es.edu.util.JSONChange;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;

public class SearchDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            //1.创建请求对象
            //GetRequest request = new GetRequest().index("teacher").id("1001");
            GetRequest request = new GetRequest("teacher","1001");
            //2.客户端发送请求，获取响应对象
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            //3.打印结果信息
            System.out.println("_index:" + response.getIndex());
            System.out.println("_type:" + response.getType());
            System.out.println("_id:" + response.getId());
            System.out.println("_source:" + response.getSourceAsString());
            System.out.println(JSONChange.objToJson(response));
        });
    }
}
