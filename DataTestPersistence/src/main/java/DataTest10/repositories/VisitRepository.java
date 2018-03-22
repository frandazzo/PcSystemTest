package DataTest10.repositories;

import DataTest10.domain.Visit;

import DataTest10.domain.Visitor;
import DataTest10.persistence.AbstractRepository;
import DataTest10.persistence.PersistenceService;
import DataTest10.persistence.RepositoryRegistry;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitRepository extends AbstractRepository<Visit> {
    @Override
    protected RowMapper<Visit> buildRowMapper() {
        return new VisitRowMapper();
    }

    @Override
    protected String buildGetAllQuery() {
        return "select * from visit";
    }

    @Override
    protected String builddeleteQuery() {
        return "delete from Visit where id = ?";
    }

    @Override
    protected String buildGetByIdQuery() {
        return "select * from Visit where id = ?";
    }

    @Override
    protected String buildSaveQuery() {
        return "insert into visit (badge, visitorId, activeVisit,visitDate ) values (?,?, ?,?)";
    }
    @Override
    protected String buildUpdateQuery() {
        return "update visit set badge = ?, visitorId = ?, activeVisit = ? ,visitDate = ? where id = ?";
    }







    @Override
    protected Object[] buildObjectParams(Visit data) {
        return new Object[]{data.getBadge(), data.getVisitor().getId(), data.isActiveVisit(), data.getVisitDate()};
    }

    public static class VisitRowMapper implements RowMapper<Visit> {

        @Override
        public Visit mapRow(ResultSet rs, int i) throws SQLException {
            Visit actor = new Visit();


            actor.setVisitDate(rs.getDate("visitDate"));
            actor.setBadge(rs.getString("badge"));
            actor.setActiveVisit(rs.getBoolean("activeVisit"));
            int visitorId = rs.getInt("visitorId");

            AbstractRepository<Visitor> rep = PersistenceService.getInstance().getRepositoryOf(Visitor.class);
            actor.setVisitor(rep.getById(visitorId));
            //
            actor.setId(rs.getInt("id"));
            return actor;
        }
    }
}

