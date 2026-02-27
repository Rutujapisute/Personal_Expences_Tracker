package com.example.Expences_TrackerUsingJWT.Dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Expences_TrackerUsingJWT.Model.expences;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class ExpencesDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<expences> getExpences(int uid) {
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<expences> cq = cb.createQuery(expences.class);
	    Root<expences> root = cq.from(expences.class);
	    
	    cq.select(root)  // All fields select
	      .where(cb.equal(root.get("uid"), uid));  // UID filter
	    
	    return entityManager.createQuery(cq).getResultList();
	}
	public List<expences> getDailyExpences(int uid) {
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<expences> cq = cb.createQuery(expences.class);
	    Root<expences> root = cq.from(expences.class);
	    
	    String today = LocalDate.now().toString();  // Current date filter
	    
	    cq.select(cb.construct(expences.class, root.get("edate"), root.get("title"), root.get("amount")))
	      .where(cb.and(
	              cb.equal(root.get("uid"), uid),
	              cb.equal(root.get("edate"), today)));
	    
	    return entityManager.createQuery(cq).getResultList();
	}
@Transactional
public String addExpences(expences exp) {
	entityManager.persist(exp);
	return "expences added";
}

public List<expences> getMonthlyExpences(int uid) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<expences> cq = cb.createQuery(expences.class);  // ExpencesDTO वापरा
    Root<expences> root = cq.from(expences.class);
    
    LocalDate now = LocalDate.now();
    String startOfMonth = now.withDayOfMonth(1).toString();  // e.g., "2024-10-01"
    String endOfMonth = now.withDayOfMonth(now.lengthOfMonth()).toString();  // e.g., "2024-10-31"
    
    cq.select(cb.construct(expences.class, root.get("edate"), root.get("title"), root.get("amount")))
    .where(cb.and(
            cb.equal(root.get("uid"), uid),
            cb.between(root.get("edate"), startOfMonth, endOfMonth)));  // Monthly filter
    
    return entityManager.createQuery(cq).getResultList();
}

public List<expences> getYearlyExpences(int uid) {  // UID parameter add
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<expences> cq = cb.createQuery(expences.class);
    Root<expences> root = cq.from(expences.class);
    
    LocalDate now = LocalDate.now();
    String startOfYear = now.withDayOfYear(1).toString();  // e.g., "2024-01-01"
    String endOfYear = now.withDayOfYear(now.lengthOfYear()).toString();  // e.g., "2024-12-31"
    
    cq.select(cb.construct(expences.class, root.get("edate"), root.get("title"), root.get("amount")))
      .where(cb.and(
          cb.equal(root.get("uid"), uid),  // UID filter add
          cb.between(root.get("edate"), startOfYear, endOfYear)
      ));
    
    return entityManager.createQuery(cq).getResultList();
}

public List<expences> getMonthlyExpensesForSecondMenu(int uid, int year, int month) {

    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<expences> cq = cb.createQuery(expences.class);
    Root<expences> root = cq.from(expences.class);

    // Month 2 digit format (02, 11 etc.)
    String monthStr = String.format("%02d", month);
    String pattern = year + "-" + monthStr + "-%";

    cq.select(root)
      .where(
          cb.and(
              cb.equal(root.get("uid"), uid),
              cb.like(root.<String>get("edate"), pattern)
          )
      );

    return entityManager.createQuery(cq).getResultList();
}
public List<expences> getYearlyExpensesList(int uid, int year) {

    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<expences> cq = cb.createQuery(expences.class);
    Root<expences> root = cq.from(expences.class);

    // Pattern for full year (yyyy-%)
    String pattern = year + "-%";

    cq.select(root)
      .where(
          cb.and(
              cb.equal(root.get("uid"), uid),
              cb.like(root.<String>get("edate"), pattern)
          )
      );

    return entityManager.createQuery(cq).getResultList();
}
}
