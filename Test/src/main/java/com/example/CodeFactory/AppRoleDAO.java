package com.example.CodeFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class AppRoleDAO {
 
    @Autowired
    private EntityManager entityManager;
    
    private static final List<AppRole> ROLES = new ArrayList<AppRole>();
    
    static {
        initData();
    }
    
    private static void initData() {
    	AppRole adm = new AppRole(1, "Admin");
    	AppRole pub = new AppRole(2, "Publisher");
    	AppRole user = new AppRole(3, "User");
    	
    	ROLES.add(adm);
    	ROLES.add(pub);
    	ROLES.add(user);
    }
    
    public static List<AppRole> getRoles() {
        return ROLES;
    }
 
    @SuppressWarnings("unchecked")
	public List<String> getRoleNames(Long userId) {
        String sql = "Select ar.roleName from AppRole ar, AppUser au where ar.roleId = au.roleId and au.userId = :userId";
 
        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
