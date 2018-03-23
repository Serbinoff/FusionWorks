package com.example.CodeFactory;

import org.springframework.data.repository.CrudRepository;
import com.example.CodeFactory.News;

// This will be AUTO IMPLEMENTED by Spring into a Bean called newsRepository

public interface NewsRepository extends CrudRepository<News, Long> {

}
