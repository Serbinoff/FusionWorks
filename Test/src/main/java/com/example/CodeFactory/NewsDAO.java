package com.example.CodeFactory;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
 
@Repository
@Transactional
public class NewsDAO {
    
    @Autowired
	JdbcTemplate jdbcTemplate;
    
    @Transactional
    public void editNews(long id, String name, String text, String preview) {
    	jdbcTemplate.update("UPDATE news.news SET name ='"+ name +"', text='"+ text +"', preview ='"+ preview +"' WHERE id = "+ id); 
    }
}