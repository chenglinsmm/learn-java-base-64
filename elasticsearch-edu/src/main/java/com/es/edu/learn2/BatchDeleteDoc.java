package com.es.edu.learn2;

import com.es.edu.common.ConnectElasticsearch;
import com.es.edu.util.JSONChange;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;

public class BatchDeleteDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            //创建批量删除请求对象
            BulkRequest request = new BulkRequest();
            request.add(new DeleteRequest().index("teacher").id("1001"));
            request.add(new DeleteRequest().index("teacher").id("1002"));
            request.add(new DeleteRequest().index("teacher").id("1003"));
            //客户端发送请求，获取响应对象
            BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
            //打印结果信息
            System.out.println("took:" + responses.getTook());
            //System.out.println("items:" + responses.getItems());
            System.out.println(JSONChange.objToJson(responses));
        });
    }
}
