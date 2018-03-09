package com.hsbc.springboot.mysql;

import com.hsbc.springboot.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Sumit on 07-03-2018.
 */
@Repository
public class EmployeeRepository  {

    @Autowired
    @PersistenceContext(unitName = Constants.DB_MYSQL)
    private EntityManager entityManager;

    public List<Employee> findAll() {
        String sql = "Select e from " + Employee.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, Employee.class);
        return query.getResultList();
    }

}
