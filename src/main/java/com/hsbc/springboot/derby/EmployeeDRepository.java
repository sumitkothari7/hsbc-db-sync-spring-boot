package com.hsbc.springboot.derby;

import com.hsbc.springboot.Constants;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Sumit on 07-03-2018.
 */
@Repository
public class EmployeeDRepository {

    @Autowired
    @PersistenceContext(unitName = Constants.DB_DERBY)
    private EntityManager entityManager;

    public List<EmployeeD> findAll() {
        String sql = "Select e from " + EmployeeD.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, EmployeeD.class);
        return query.getResultList();
    }

    public void save(EmployeeD employeeD) {
        Session session = null;
        EntityTransaction tx = null;
        try {

            session = entityManager.unwrap(Session.class);
            session.beginTransaction();
            session.save(employeeD);
            session.getTransaction().commit();

        }catch (Exception e) {

        }
    }
}


