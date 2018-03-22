package DataTest10;


import DataTest10.domain.Department;
import DataTest10.domain.Visit;
import DataTest10.domain.Visitor;
import DataTest10.persistence.DataSourceConfiguration;

import DataTest10.persistence.PersistenceService;
import DataTest10.repositories.DepartmentRepository;
import DataTest10.repositories.VisitRepository;
import DataTest10.repositories.VisitorRepository;
import DataTest10.services.DepartmentValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public class Bootstrapper {

    @Autowired
    private DepartmentRepository depRep;

    @Autowired
    private VisitRepository visitRepository;


    @Autowired
    private VisitorRepository visitorRepository;

    @PostConstruct
    public void init(){

        List<DataSourceConfiguration> configs = new ArrayList<>();
        DataSourceConfiguration configuration = new DataSourceConfiguration();
        configuration.setConnectionDriver("com.mysql.jdbc.Driver");
        configuration.setConnectionString("jdbc:mysql://localhost:3306/testpcsystem");
        configuration.setUsername("root");
        configuration.setPassword("ciccio");
        configs.add(configuration);


        PersistenceService.getInstance().registerDatasources(configs);

        PersistenceService.getInstance().registerRepository(depRep, Department.class);
        PersistenceService.getInstance().registerRepository(visitRepository, Visit.class);
        PersistenceService.getInstance().registerRepository(visitorRepository, Visitor.class);



        System.out.println("Bootstrap applicazione");





    }
}
