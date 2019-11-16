package employee.web.bean;

import employee.domain.models.binding.EmployeeViewBindingModel;
import employee.domain.models.services.EmployeeServiceModel;
import employee.service.EmployeeService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class EmployeeRemoveBean {
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeRemoveBean() {
    }

    @Inject
    public EmployeeRemoveBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    public void remove(EmployeeViewBindingModel employeeViewBindingModel) throws IOException {
        EmployeeServiceModel employeeServiceModel = this.modelMapper
                .map(employeeViewBindingModel, EmployeeServiceModel.class);
        this.employeeService.removeEmployee(employeeServiceModel);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }
}
