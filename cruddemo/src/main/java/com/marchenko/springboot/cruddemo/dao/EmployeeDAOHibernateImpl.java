package com.marchenko.springboot.cruddemo.dao;

import com.marchenko.springboot.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    private final EntityManager entityManager;

    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Employee");
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {
        return session().get(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        session().saveOrUpdate(employee);
    }

    @Override
    public void deleteById(int theId) {
        Query query = session().createQuery("delete from Employee where id =: employeeId");
        query.setParameter("employeeId", theId);
        query.executeUpdate();
    }

    private Session session() {
        return entityManager.unwrap(Session.class);
    }
}
