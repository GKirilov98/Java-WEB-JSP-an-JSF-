package employee.service;

import employee.domain.models.services.EmployeeServiceModel;

import java.util.List;

public interface EmployeeService {
    boolean saveEmployee(EmployeeServiceModel employeeServiceModel);

    List<EmployeeServiceModel> findAll();

    void removeEmployee(EmployeeServiceModel employeeServiceModel);
}
