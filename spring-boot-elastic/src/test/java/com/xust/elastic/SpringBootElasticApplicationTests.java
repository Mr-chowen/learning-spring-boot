package com.xust.elastic;

import com.xust.elastic.bean.Article;
import com.xust.elastic.bean.Book;
import com.xust.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SpringBootElasticApplicationTests {
    @Autowired
    JestClient jestClient;
    @Autowired
    BookRepository bookRepository;

    @Test
    void test(){
//        Book book=new Book();
//        book.setId(1);
//        book.setBookName("水浒传");
//        book.setAuthor("施耐庵");
//        bookRepository.index(book);
        for (Book book:bookRepository.findBookByBookNameLike("传")) {
            System.out.println(book);
        }
    }

    @Test
    void contextLoads() {
        Article article = new Article();
        article.setId(1);
        article.setAuthor("春生");
        article.setTitle("冬雪");
        article.setContent("春生夏长，秋收冬藏！");
        //构建一个索引功能
        Index index = new Index.Builder(article).index("xust").type("news").build();

        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //测试搜索功能
    @Test
    void search(){
        //查询表达式
        String json="{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"春生\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("xust").addType("news").build();
        try {
            //执行
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
