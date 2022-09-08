package com.es.edu.learn1;

import com.es.edu.common.ConnectElasticsearch;
import com.es.edu.util.JSONChange;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;

public class SearchCount {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            //1.创建请求对象
            CountRequest request = new CountRequest("teacher");
            //2.客户端发送请求，获取响应对象
            CountResponse response = client.count(request, RequestOptions.DEFAULT);
            //3.打印结果信息
            System.out.println("_count:" + response.getCount());
            System.out.println(JSONChange.objToJson(response));
        });
    }
}
