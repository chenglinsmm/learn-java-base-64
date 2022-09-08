package com.es.edu.learn3;

import com.es.edu.common.ConnectElasticsearch;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * 浅分页的原理很简单，就是查询前20条数据，然后截断前10条，只返回10-20的数据。这样其实白白浪费了前10条的查询
 * es默认采用的是from+size形式，在深度分页的情况下，这种效率是非常低的，但是可以随机跳转页面
 * es为了性能，会限制我们分页的深度，es目前支持最大的max_result_window = 10000，也就是from+size的大小不能超过10000
 *
 * from+size查询在10000-50000条数据（1000到5000页）以内的时候还是可以的，但是如果数据过多的话，就会出现深分页问题。为了这个问题，es提出了scroll滚动查询方式
 * scroll滚动搜索，会在第一次搜索的时候，保存一个当下的快照。之后只会基于该快照提供数据搜索。在这个期间数据如果发生变动，是不会让用户看到的。推荐非实时处理大量数据的情况可以使用
 * 不适用于有跳页的情景
 *
 */
public class QueryDocByPage {

    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            // 创建搜索请求对象
            SearchRequest request = new SearchRequest().indices("teacher");
            // 构建查询的请求体
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(QueryBuilders.matchAllQuery());
            // 分页查询
            // 当前页其实索引(第一条数据的顺序号) from
            sourceBuilder.from(0);
            // 每页显示多少条 size
            sourceBuilder.size(5);
            request.source(sourceBuilder);
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            // 查询匹配
            SearchHits hits = response.getHits();
            System.out.println("took:" + response.getTook());
            System.out.println("timeout:" + response.isTimedOut());
            System.out.println("total:" + hits.getTotalHits());
            System.out.println("MaxScore:" + hits.getMaxScore());
            System.out.println("hits========>>");
            for (SearchHit hit : hits) {
                //输出每条查询的结果信息
                System.out.println(hit.getSourceAsString());
            }
            System.out.println("<<========");
        });
    }
}
