package DataTest10.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceInterfaceDecorator implements VisitServiceInterface {

    @Autowired
    private VisitServiceInterface visitService;

    @Override
    public void registerVisit(int visitorId, String badge) throws Exception {

        executeLog();

        visitService.registerVisit(visitorId, badge);


        executeLog1();


    }

    private void executeLog1() {
        System.out.println("exiting log");
    }

    private void executeLog() {

        System.out.println("entering log");
    }
}
