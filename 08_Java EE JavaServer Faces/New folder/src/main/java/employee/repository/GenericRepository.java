package employee.repository;

import employee.domain.enities.Employee;

import java.util.List;

public interface GenericRepository<Entity> {
    boolean save(Employee employee);

    List<Entity> findAll();
}
