package com.knowabhi.springgraphql.service;

import com.knowabhi.springgraphql.model.Book;
import com.knowabhi.springgraphql.repository.BookReposiroty;
import com.knowabhi.springgraphql.service.datafetcher.AllBooksDataFetcher;
import com.knowabhi.springgraphql.service.datafetcher.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class GraphQLService {

    @Autowired
    BookReposiroty bookReposiroty;

    @Value("classpath:books.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllBooksDataFetcher allBooksDataFetcher;

    @Autowired
    private BookDataFetcher bookDataFetcher;

    // load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {
        loadDataIntoHSQL();
        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry,wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoHSQL() {
        Stream.of(
                new Book("101",
                        "Book of Clouds",
                        "Kindle",
                        new String[] {"Chloe Aridlis"},
                        "Nov 2017"),
                new Book("102",
                        "Book of BigData",
                        "Kindle",
                        new String[] {"Chloe Smith", "Ram Sharma"},
                        "Oct 2018")
        ).forEach(book -> {
            bookReposiroty.save(book);
        });
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                    .dataFetcher("allBooks", allBooksDataFetcher)
                    .dataFetcher("book", bookDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
