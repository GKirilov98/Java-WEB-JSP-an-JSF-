package employee.repository;

import employee.domain.enities.Employee;

public interface EmployeeRepository extends GenericRepository<Employee> {
    void removeEmployee(Employee employee);
}
