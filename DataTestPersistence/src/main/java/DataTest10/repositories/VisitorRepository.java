package DataTest10.repositories;

import DataTest10.domain.Visit;
import DataTest10.domain.Visitor;

import DataTest10.persistence.AbstractRepository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitorRepository extends AbstractRepository<Visitor> {
    @Override
    protected RowMapper<Visitor> buildRowMapper() {
        return new VisitorRowMapper();
    }

    @Override
    protected String buildGetAllQuery() {
        return "select * from visitor";
    }

    @Override
    protected String builddeleteQuery() {
        return "delete from visitor where id = ?";
    }

    @Override
    protected String buildGetByIdQuery() {
        return "select * from visitor where id = ?";
    }

    @Override
    protected String buildSaveQuery() {
        return "insert into visitor (name, surname) values (?,?)";
    }

    @Override
    protected String buildUpdateQuery() {
        return "update visitor set name = ?, surname = ? where id = ?";
    }





    @Override
    protected Object[] buildObjectParams(Visitor data) {
        return new Object[]{ data.getName(), data.getSurname()};
    }

    public static class VisitorRowMapper implements RowMapper<Visitor> {

        @Override
        public Visitor mapRow(ResultSet rs, int i) throws SQLException {
            Visitor actor = new Visitor();
            actor.setName(rs.getString("name"));
            actor.setSurname(rs.getString("surname"));
            actor.setId(rs.getInt("id"));
            return actor;
        }
    }
}
