package DataTest10.services;

import DataTest10.domain.Department;
import DataTest10.domain.Visit;
import DataTest10.domain.Visitor;
import DataTest10.persistence.PersistenceService;
import DataTest10.repositories.QueryCollection;
import DataTest10.repositories.VisitRepository;
import DataTest10.repositories.VisitorRepository;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;


@Service
public class VisitService implements VisitServiceInterface {


    @Override
    public void registerVisit(int visitorId, String badge) throws Exception {

        //eseguo la validaziokne dei dati secondo i criteri specificcati nello usecase
        validate(visitorId, badge);


        //salvo lentità nel db avendo accortezza di impostare il flag
        //visitai in corso a true;
        Visit v = new Visit();
        v.setBadge(badge);
        v.setActiveVisit(true);
        v.setVisitor(retrieveVisitor(visitorId));
        v.setVisitDate(new Date());


        PersistenceService.getInstance().getRepositoryOf(Visit.class).saveOrUpdate(v);

    }

    private Visitor retrieveVisitor(int visitorId) {
        return PersistenceService.getInstance().getRepositoryOf(Visitor.class).getById(visitorId);
    }

    private void validate(int visitorId, String badge) throws Exception {


        checkBadgeValidity(badge);
        checkVisitorHasNotActiveVisit(visitorId);
        checkMaximunVisitsCapacity();


    }

    private void checkMaximunVisitsCapacity() throws Exception {

        List<Visit> visits = PersistenceService.getInstance().getRepositoryOf(Visit.class).find(String.format(QueryCollection.MAX_VISIT_NUMBER_QUERY, true));

        if (visits.size() > 10)
            throw new Exception("Massimo numero visitatori raggiunto");
    }

    private void checkVisitorHasNotActiveVisit(int visitorId) throws Exception {

        List<Visit> visits = PersistenceService.getInstance().getRepositoryOf(Visit.class).find(String.format("select * from visit where visitorId = %s and activeVisit = %s", visitorId, true));

        if (visits.size() > 0)
            throw new Exception("Esistono già visite attive per il visitatore");
    }

    private void checkBadgeValidity(String badge) throws Exception {


        List<Visit> visits = PersistenceService.getInstance().getRepositoryOf(Visit.class).find(String.format("select * from visit where badge = %s and activeVisit = %s", badge, true));

        if (visits.size() > 0)
            throw new Exception("Esistono già visitore con lo stesso badge");
    }

}
