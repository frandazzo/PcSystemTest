package DataTest10.boundaries;

import DataTest10.services.VisitService;

import DataTest10.services.VisitServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitServiceInterface visitServiceInterfaceDecorator;

    @PostMapping("/")
    public String registerVisit(int visitorId, String badge){
        try
        {


            visitServiceInterfaceDecorator.registerVisit(visitorId, badge);


            return "";
        }catch (Exception ex){
            return ex.getMessage();
        }
    }


}
