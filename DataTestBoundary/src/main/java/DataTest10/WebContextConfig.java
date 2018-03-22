package DataTest10;

import DataTest10.repositories.DepartmentRepository;
import DataTest10.repositories.VisitRepository;
import DataTest10.repositories.VisitorRepository;
import DataTest10.services.DepartmentValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan({"DataTest10.boundaries","DataTest10.services"})
public class WebContextConfig {



    @Bean
    public Bootstrapper getBoostrapper(){

        return new Bootstrapper();
    }

    @Bean
    public DepartmentRepository getDepartmentRepository(){

        return new DepartmentRepository(new DepartmentValidator());
    }


    @Bean
    public VisitorRepository getVisitorRepository(){

        return new VisitorRepository();
    }

    @Bean
    public VisitRepository getVisitRepository(){

        return new VisitRepository();
    }




}
