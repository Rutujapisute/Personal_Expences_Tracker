package com.example.Expences_TrackerUsingJWT.Dao;

import java.time.LocalDate;
import java.util.List;  // Import add करा

import org.springframework.stereotype.Repository;

import com.example.Expences_TrackerUsingJWT.Model.categories;
import com.example.Expences_TrackerUsingJWT.Model.expences;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

@Repository
public class TotalExpencesDao {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public String todaysExpences(int uid) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
        Root<expences> root = cq.from(expences.class);

        cq.select(
            cb.coalesce(
                cb.sum(cb.toDouble(root.get("amount"))),  // amount ला Double मध्ये convert करा
                0.0
            )
        ).where(
            cb.and(
                cb.equal(root.get("uid"), uid),
                cb.equal(root.get("edate"), LocalDate.now().toString())
            )
        );

        List<Double> results = entityManager.createQuery(cq).getResultList();
        Double total = results.isEmpty() ? 0.0 : results.get(0);  // जर रिकामे असेल तर 0.0
        return String.valueOf(total);
    }
    
    public String monthlyExpences(int uid) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<expences> root = cq.from(expences.class);

        LocalDate now = LocalDate.now();
        String startOfMonth = now.withDayOfMonth(1).toString();
        String endOfMonth = now.withDayOfMonth(now.lengthOfMonth()).toString();

        cq.select(
            cb.coalesce(
                cb.sumAsLong(root.get("amount")),  // Changed to sumAsLong for Long type
                0L
            )
        ).where(
            cb.and(
                cb.equal(root.get("uid"), uid),
                cb.between(root.get("edate"), startOfMonth, endOfMonth)
            )
        );

        Long total = entityManager.createQuery(cq).getSingleResult();
        return String.valueOf(total);
    }
    
    public String yearlyExpences(int uid) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<expences> root = cq.from(expences.class);

        LocalDate now = LocalDate.now();
        String startOfYear = now.withDayOfYear(1).toString();
        String endOfYear = now.withDayOfYear(now.lengthOfYear()).toString();

        cq.select(
            cb.coalesce(
                cb.sumAsLong(root.get("amount")),
                0L
            )
        ).where(
            cb.and(
                cb.equal(root.get("uid"), uid),
                cb.between(root.get("edate"), startOfYear, endOfYear)
            )
        );

        Long total = entityManager.createQuery(cq).getSingleResult();
        return String.valueOf(total);
    }
    
    public String dailyFoodExpences(int uid) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<expences> root = cq.from(expences.class);

        String today = LocalDate.now().toString();

        Subquery<Integer> subquery = cq.subquery(Integer.class);
        Root<categories> subRoot = subquery.from(categories.class);  // Import fix केल्यानंतर resolve होईल
        subquery.select(subRoot.get("id")).where(cb.equal(subRoot.get("cname"), "food"));

        cq.select(
            cb.coalesce(
                cb.sumAsLong(root.get("amount")),
                0L
            )
        ).where(
            cb.and(
                cb.equal(root.get("uid"), uid),
                cb.equal(root.get("edate"), today),
                cb.in(root.get("cid")).value(subquery)
            )
        );

        Long total = entityManager.createQuery(cq).getSingleResult();
        return String.valueOf(total);
    }
    
    public String dailyTravelExpences(int uid) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<expences> root = cq.from(expences.class);

        String today = LocalDate.now().toString();

        Subquery<Integer> subquery = cq.subquery(Integer.class);
        Root<categories> subRoot = subquery.from(categories.class);  // Import fix केल्यानंतर resolve होईल
        subquery.select(subRoot.get("id")).where(cb.equal(subRoot.get("cname"), "Travel"));

        cq.select(
            cb.coalesce(
                cb.sumAsLong(root.get("amount")),
                0L
            )
        ).where(
            cb.and(
                cb.equal(root.get("uid"), uid),
                cb.equal(root.get("edate"), today),
                cb.in(root.get("cid")).value(subquery)
            )
        );

        Long total = entityManager.createQuery(cq).getSingleResult();
        return String.valueOf(total);
    }
    
    public String dailyShoppingExpences(int uid) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<expences> root = cq.from(expences.class);

        String today = LocalDate.now().toString();

        Subquery<Integer> subquery = cq.subquery(Integer.class);
        Root<categories> subRoot = subquery.from(categories.class);  // Import fix केल्यानंतर resolve होईल
        subquery.select(subRoot.get("id")).where(cb.equal(subRoot.get("cname"), "Shopping"));

        cq.select(
            cb.coalesce(
                cb.sumAsLong(root.get("amount")),
                0L
            )
        ).where(
            cb.and(
                cb.equal(root.get("uid"), uid),
                cb.equal(root.get("edate"), today),
                cb.in(root.get("cid")).value(subquery)
            )
        );

        Long total = entityManager.createQuery(cq).getSingleResult();
        return String.valueOf(total);
    }
    
    public String dailyOthersExpences(int uid) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<expences> root = cq.from(expences.class);

        String today = LocalDate.now().toString();

        Subquery<Integer> subquery = cq.subquery(Integer.class);
        Root<categories> subRoot = subquery.from(categories.class);  // Import fix केल्यानंतर resolve होईल
        subquery.select(subRoot.get("id")).where(cb.equal(subRoot.get("cname"), "others"));

        cq.select(
            cb.coalesce(
                cb.sumAsLong(root.get("amount")),
                0L
            )
        ).where(
            cb.and(
                cb.equal(root.get("uid"), uid),
                cb.equal(root.get("edate"), today),
                cb.in(root.get("cid")).value(subquery)
            )
        );

        Long total = entityManager.createQuery(cq).getSingleResult();
        return String.valueOf(total);
    }
    
    public String monthlyCategoryExpenses(int uid, int year, int month, String categoryName) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<expences> root = cq.from(expences.class);

        // Month 2 digit format
        String monthStr = String.format("%02d", month);
        String pattern = year + "-" + monthStr + "-%";

        Subquery<Integer> subquery = cq.subquery(Integer.class);
        Root<categories> subRoot = subquery.from(categories.class);
        subquery.select(subRoot.get("id"))
                .where(cb.equal(subRoot.get("cname"), categoryName));

        cq.select(
                cb.coalesce(
                        cb.sumAsLong(root.get("amount")),
                        0L
                )
        ).where(
                cb.and(
                        cb.equal(root.get("uid"), uid),
                        cb.like(root.<String>get("edate"), pattern),
                        cb.in(root.get("cid")).value(subquery)
                )
        );

        Long total = entityManager.createQuery(cq).getSingleResult();
        return String.valueOf(total);
    }
    
    public String yearlyCategoryExpenses(int uid, int year, String categoryName) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<expences> root = cq.from(expences.class);

        String pattern = year + "-%";

        Subquery<Integer> subquery = cq.subquery(Integer.class);
        Root<categories> subRoot = subquery.from(categories.class);
        subquery.select(subRoot.get("id"))
                .where(cb.equal(subRoot.get("cname"), categoryName));

        cq.select(
                cb.coalesce(
                        cb.sumAsLong(root.get("amount")),
                        0L
                )
        ).where(
                cb.and(
                        cb.equal(root.get("uid"), uid),
                        cb.like(root.get("edate"), pattern),
                        cb.in(root.get("cid")).value(subquery)
                )
        );

        Long total = entityManager.createQuery(cq).getSingleResult();
        return String.valueOf(total);
    }
}