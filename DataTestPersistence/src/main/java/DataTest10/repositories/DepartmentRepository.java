package DataTest10.repositories;

import DataTest10.domain.Department;

import DataTest10.domain.Visit;
import DataTest10.persistence.AbstractRepository;
import DataTest10.persistence.AbstractValidator;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentRepository extends AbstractRepository<Department> {


    public DepartmentRepository(AbstractValidator validator){
        super(validator);
    }

    public boolean existDepartmentByDescription(final String description) {
        Object[] param = new Object[]{description};
        List<Department> deps1 = find("select * from department where description = ?", param );
        return !deps1.isEmpty();
    }

    @Override
    protected RowMapper<Department> buildRowMapper() {
        return new RowMapper<Department>() {
            @Override
            public Department mapRow(ResultSet rs, int i) throws SQLException {
                Department actor = new Department();
                actor.setDescription(rs.getString("description"));
                actor.setAccessPolicy(rs.getInt("accessPolicy"));
                actor.setId(rs.getInt("id"));
                return actor;
            }
        };
    }

    @Override
    protected String buildGetAllQuery() {
        return "select * from department";
    }

    @Override
    protected String builddeleteQuery() {
        return "delete from department where id = ?";
    }

    @Override
    protected String buildGetByIdQuery() {
        return "select * from department where id = ?";
    }

    @Override
    protected String buildSaveQuery() {
        return "insert into department (description, accessPolicy) values (?,?)";
    }

    @Override
    protected Object[] buildObjectParams(Department data) {
        return new Object[]{data.getDescription(), data.getAccessPolicy()};
    }





    @Override
    protected String buildUpdateQuery() {
        return "update department set description = ?, accessPolicy = ? where id = ?";
    }

//    public static class DepartMentRowMapper implements RowMapper<Department>{
//
//        @Override
//        public Department mapRow(ResultSet rs, int i) throws SQLException {
//            Department actor = new Department();
//            actor.setDescription(rs.getString("description"));
//            actor.setAccessPolicy(rs.getInt("accessPolicy"));
//            actor.setId(rs.getInt("id"));
//            return actor;
//        }
//    }
}
