package com.es.edu.learn2;

import com.es.edu.common.ConnectElasticsearch;
import com.es.edu.util.JSONChange;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.xcontent.XContentType;

public class BatchInsertDoc {

    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            //创建批量新增请求对象
            BulkRequest request = new BulkRequest();
            request.add(new IndexRequest().index("teacher").id("1001").source(XContentType.JSON, "name", "zhangsan", "age", "10", "sex","女"));
            request.add(new IndexRequest().index("teacher").id("1002").source(XContentType.JSON, "name", "lisi", "age", "30", "sex","女"));
            request.add(new IndexRequest().index("teacher").id("1003").source(XContentType.JSON, "name", "wangwu1", "age", "40", "sex","男"));
            request.add(new IndexRequest().index("teacher").id("1004").source(XContentType.JSON, "name", "wangwu2", "age", "20", "sex","女"));
            request.add(new IndexRequest().index("teacher").id("1005").source(XContentType.JSON, "name", "wangwu3", "age", "50", "sex","男"));
            request.add(new IndexRequest().index("teacher").id("1006").source(XContentType.JSON, "name", "wangwu4", "age", "20", "sex","男"));
            //客户端发送请求，获取响应对象
            BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
            //打印结果信息
            System.out.println("took:" + responses.getTook());
            //System.out.println("items:" + responses.getItems());
            System.out.println(JSONChange.objToJson(responses));
        });
    }
}