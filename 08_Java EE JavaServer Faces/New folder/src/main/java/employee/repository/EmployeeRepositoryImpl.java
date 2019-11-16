package employee.repository;

import employee.config.MessagesCommands;
import employee.domain.enities.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EntityManager entityManager;

    @Inject
    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(Employee employee) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(employee);
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw new Error(MessagesCommands.REPOSITORY_ERROR);
        }
    }

    @Override
    public List<Employee> findAll() {
        return this.entityManager
                .createQuery("SELECT e FROM employees e", Employee.class)
                .getResultList();
    }

    @Override
    public void removeEmployee(Employee employee) {
        this.entityManager.getTransaction().begin();
        Employee employeeToRemove = this.entityManager.createQuery("SELECT e FROM employees e" +
                " WHERE e.firstName = :fn " +
                "AND e.lastName = :ln " +
                "AND e.salary = :salary " +
                "AND e.position = :pos " +
                "AND e.age = :age", Employee.class)
                .setParameter("fn", employee.getFirstName())
                .setParameter("ln", employee.getLastName())
                .setParameter("salary", employee.getSalary())
                .setParameter("pos", employee.getPosition())
                .setParameter("age", employee.getAge())
                .getResultList().get(0);
        this.entityManager.remove(employeeToRemove);
        this.entityManager.getTransaction().commit();
    }
}
