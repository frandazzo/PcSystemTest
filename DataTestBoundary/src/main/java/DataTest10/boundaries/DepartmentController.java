package DataTest10.boundaries;

import DataTest10.domain.Department;
import DataTest10.services.DepartmentService;

import DataTest10.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/")
    public List<Department> getDepartments(){


        List<Department> a =  departmentService.getAll();
        return a;

    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable int id){

        return  departmentService.getById(id);


    }



    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable int id){

        departmentService.delete(id);

        return "ok";


    }




    @PostMapping("/")
    public String save(@RequestBody Department department)  {

        try {


            departmentService.insert(department);

             return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


}
