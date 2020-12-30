package com.knowabhi.springgraphql.repository;

import com.knowabhi.springgraphql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReposiroty extends JpaRepository<Book, String> {
}
