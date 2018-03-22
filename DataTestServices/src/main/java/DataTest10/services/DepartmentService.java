package DataTest10.services;

import DataTest10.domain.Department;

import DataTest10.persistence.PersistenceService;
import DataTest10.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DepartmentService{

    public List<Department> getAll() {
       return PersistenceService.getInstance().getRepositoryOf(Department.class).getAll();

    }


    public void insert(Department department) throws Exception {

        //salva il dipartimento
        PersistenceService.getInstance().getRepositoryOf(Department.class).saveOrUpdate(department);

    }



    public Department getById(int id) {
        return PersistenceService.getInstance().getRepositoryOf(Department.class).getById(id);
    }

    public void delete(int id) {
        PersistenceService.getInstance().getRepositoryOf(Department.class).delete(id);
    }
}
