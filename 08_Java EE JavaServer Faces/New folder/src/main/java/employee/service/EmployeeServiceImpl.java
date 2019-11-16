package employee.service;

import employee.config.MessagesCommands;
import employee.domain.enities.Employee;
import employee.domain.models.services.EmployeeServiceModel;
import employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private  final EmployeeRepository employeeRepository;
    private  final ModelMapper modelmapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelmapper) {
        this.employeeRepository = employeeRepository;
        this.modelmapper = modelmapper;
    }

    @Override
    public boolean saveEmployee(EmployeeServiceModel employeeServiceModel) {
        if (employeeServiceModel != null){
            this.employeeRepository.save(this.modelmapper.map(employeeServiceModel, Employee.class));
            return true;
        } else {
            throw new Error(MessagesCommands.SERVICE_ERROR);
        }
    }

    @Override
    public List<EmployeeServiceModel> findAll() {
      return  this.employeeRepository.findAll()
                .stream()
                .map( e -> this.modelmapper.map(e, EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void removeEmployee(EmployeeServiceModel employeeServiceModel) {
        Employee employee = this.modelmapper.map(employeeServiceModel, Employee.class);
        this.employeeRepository.removeEmployee(employee);
    }
}
