package com.es.edu.learn3;

import com.es.edu.common.ConnectElasticsearch;
import com.es.edu.util.JSONChange;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class QueryDocGroup {

    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            //1.创建搜索请求对象
            SearchRequest request = new SearchRequest().indices("teacher");
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.aggregation(AggregationBuilders.terms("age_groupby").field("age"));
            //2.构建查询的请求体
            request.source(sourceBuilder);
            //3.客户端发送请求，获取响应对象
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            //4.打印响应结果
            SearchHits hits = response.getHits();
            System.out.println(response);

        });
    }
}
