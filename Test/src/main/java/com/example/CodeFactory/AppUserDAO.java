package com.example.CodeFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
 
@Repository
@Transactional
public class AppUserDAO {
 
    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    JdbcTemplate jdbcTemplate;
 
    public AppUser findUserAccount(String userName) {
        try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userName = :userName ";
 
            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);
 
            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Transactional
    public void updateUser(Long userId, Long roleId, String userName, String encrytedPassword) {
    	jdbcTemplate.update("UPDATE `news`.`app_user` SET `ROLE_ID`='"+ roleId +"', `USER_NAME`='"+ userName +"', `ENCRYTED_PASSWORD` = '"+ encrytedPassword +"' WHERE `USER_ID`='"+ userId +"'");
    }
}