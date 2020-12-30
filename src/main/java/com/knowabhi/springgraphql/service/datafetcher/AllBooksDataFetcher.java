package com.knowabhi.springgraphql.service.datafetcher;

import com.knowabhi.springgraphql.model.Book;
import com.knowabhi.springgraphql.repository.BookReposiroty;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>> {

    @Autowired
    BookReposiroty bookReposiroty;

    @Override
    public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return bookReposiroty.findAll();
    }
}
