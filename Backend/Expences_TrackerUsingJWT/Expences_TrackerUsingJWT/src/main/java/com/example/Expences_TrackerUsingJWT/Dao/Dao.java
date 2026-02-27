package com.example.Expences_TrackerUsingJWT.Dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Expences_TrackerUsingJWT.Model.users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class Dao {
	
	@PersistenceContext
	EntityManager entityManager;
	 
	
	public List<users> getUsers()
	{
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<users> cq=cb.createQuery(users.class);
		Root<users> root=cq.from(users.class);
		
		cq.select(root);
		
		return entityManager.createQuery(cq).getResultList();
	}
	
	@Transactional
	public String ragister( users usr)
	{
		entityManager.persist(usr);
		
		return "Registration successfull";
	}

	 public users login(String email) {  // 👈 Remove password parameter
	        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<users> cq = cb.createQuery(users.class);
	        Root<users> root = cq.from(users.class);
	        
	        cq.select(root).where(cb.equal(root.get("email"), email));
	        
	        List<users> result = entityManager.createQuery(cq).getResultList();
	        
	        return result.isEmpty() ? null : result.get(0);
	    }
	 
	 
	public users getUserByEmail(String email) {

	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<users> cq = cb.createQuery(users.class);
	    Root<users> root = cq.from(users.class);

	    cq.select(root)
	      .where(cb.equal(root.get("email"), email));

	    List<users> list = entityManager.createQuery(cq).getResultList();

	    return list.isEmpty() ? null : list.get(0);
	}

	@Transactional
	public String deleteUserById(int id) {
	    // 1️⃣ Find user by ID
	    users usr = entityManager.find(users.class, id);

	    if (usr != null) {
	        // 2️⃣ Remove from DB
	        entityManager.remove(usr);
	        return "User deleted successfully";
	    } else {
	        return "User not found";
	    }
	}
	
	
	

}
