package DataTest10.services;


import DataTest10.domain.Department;
import DataTest10.persistence.AbstractValidator;
import DataTest10.persistence.PersistenceService;
import DataTest10.repositories.DepartmentRepository;

import com.mysql.jdbc.StringUtils;


import java.util.HashMap;

public class DepartmentValidator extends AbstractValidator<Department> {


    @Override
    public HashMap<String, String> validate(Department data) {


            //dalle busioness rules
            HashMap<String, String> errors = new HashMap<String, String>();
            //la descrizione non puo' essere nulla
            if (StringUtils.isNullOrEmpty(data.getDescription()))
                errors.put("description", "La descrizione non può essere nulla");

            //la descrizione deve esssere univoca
            DepartmentRepository rep = ((DepartmentRepository) PersistenceService.getInstance().getRepositoryOf(Department.class));

            if (rep.existDepartmentByDescription(data.getDescription()))
                errors.put("description", "Dipartimento già esistente");

            return errors;

    }



}
