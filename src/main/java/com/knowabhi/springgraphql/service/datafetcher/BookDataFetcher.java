package com.knowabhi.springgraphql.service.datafetcher;

import com.knowabhi.springgraphql.model.Book;
import com.knowabhi.springgraphql.repository.BookReposiroty;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

    @Autowired
    BookReposiroty bookReposiroty;

    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {

        String isn = dataFetchingEnvironment.getArgument("id");

        return bookReposiroty.getOne(isn);
    }
}
