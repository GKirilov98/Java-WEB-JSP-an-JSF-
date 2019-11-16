package employee.web.bean;

import employee.domain.models.binding.EmployeeViewBindingModel;
import employee.service.EmployeeService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeListBean {
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    private List<EmployeeViewBindingModel> employeeViewBindingModels;
    private BigDecimal totalMoneyNeeded;
    private BigDecimal averageSalary;

    public EmployeeListBean() {
    }

    @Inject
    public EmployeeListBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.fillEmployeesList();
        this.calculateSalary();
    }

    private void fillEmployeesList() {
        this.employeeViewBindingModels = this.employeeService.findAll().stream()
                .map(e -> this.modelMapper.map(e, EmployeeViewBindingModel.class))
                .collect(Collectors.toList());

    }

    private void calculateSalary(){
        List<BigDecimal> allSalaries = this.employeeViewBindingModels.stream()
                .map(EmployeeViewBindingModel::getSalary)
                .collect(Collectors.toList());
        BigDecimal sumOfSalary = allSalaries.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
      this.totalMoneyNeeded = sumOfSalary;
      this.averageSalary = sumOfSalary.divide(
              BigDecimal.valueOf(allSalaries.size()),
              2, RoundingMode.HALF_DOWN);
    }

    public List<EmployeeViewBindingModel> getEmployeeViewBindingModels() {
        return employeeViewBindingModels;
    }

    public void setEmployeeViewBindingModels(List<EmployeeViewBindingModel> employeeViewBindingModels) {
        this.employeeViewBindingModels = employeeViewBindingModels;
    }

    public BigDecimal getTotalMoneyNeeded() {
        return totalMoneyNeeded;
    }

    public void setTotalMoneyNeeded(BigDecimal totalMoneyNeeded) {
        this.totalMoneyNeeded = totalMoneyNeeded;
    }

    public BigDecimal getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(BigDecimal averageSalary) {
        this.averageSalary = averageSalary;
    }
}
