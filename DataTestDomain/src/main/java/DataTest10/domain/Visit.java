package DataTest10.domain;

import DataTest10.persistence.Entity;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;

public class Visit extends Entity {


    private String badge;
    private Visitor visitor;
    private  boolean activeVisit;
    private Date visitDate;

    public boolean isActiveVisit() {
        return activeVisit;
    }

    public void setActiveVisit(boolean activeVisit) {
        this.activeVisit = activeVisit;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }



    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }



    @Override
    public HashMap<String, String> validate(){
        boolean validBadge = !StringUtils.isEmpty(badge);
        boolean validVisitor = this.visitor == null ? false : true;


        HashMap<String, String> result = new HashMap<>();

        if (!validBadge)
            result.put("Badge", "Badge non corretto");

        if (!validVisitor)
            result.put("Visitor", "Vistor non impostato");

        return result;
    }


    public String getValidationError() {

        StringBuilder b = new StringBuilder() ;
        if (StringUtils.isEmpty(badge))
            b.append(String.format("%s - %s", "Badge: ", "Il valore è nullo"));

        if (visitor == null)
            b.append(String.format("%s - %s", "Visitor: ", "Il valore è nullo"));

        return b.toString();
    }
}
