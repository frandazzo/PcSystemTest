package DataTest10.persistence;

import DataTest10.persistence.exceptions.PersistenceValidationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractRepository<T extends Entity> {


    protected HashMap<Integer, T> cache = new HashMap<>();


    protected abstract RowMapper<T> buildRowMapper();
    protected abstract String buildGetAllQuery();
    protected abstract String builddeleteQuery();
    protected abstract String buildGetByIdQuery();
    protected abstract String buildSaveQuery();
    protected abstract Object[] buildObjectParams(T data);
    protected abstract String buildUpdateQuery();

    protected AbstractValidator<T> validator;

    public AbstractRepository(){};
    public AbstractRepository(AbstractValidator validator){
        this.validator = validator;
    }




    protected  Class<T> type;

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public List<T> getAll() {

        String getAllQuery = buildGetAllQuery();
        RowMapper<T> rowMapper = buildRowMapper();

        return getQueryResults(getAllQuery, rowMapper );
    }

    public void delete(int id) {
        String deleteQuery = builddeleteQuery();
        executeDelete(id,deleteQuery);
    }

    public void saveOrUpdate(T data) throws PersistenceValidationException {


        if (validator != null)
        {
            HashMap<String,String> errors = validator.validate(data);
            if (errors.isEmpty())
                errors = data.validate();

            if (!errors.isEmpty())
                throw new PersistenceValidationException(Entity.extractString(errors));

        }


        if (data.getId() == 0){
            insert(data);
            return;
        }

        update(data);
    }


    protected void update(T data){
        String updateQuery = buildUpdateQuery();

        Object[] paramsWithId = createCompleteParamList(data);

        executeCommand(updateQuery, paramsWithId);
    }

    protected Object[] createCompleteParamList(T data) {
        Object[] params = buildObjectParams(data);
        Object[] paramsWithId = new Object[params.length + 1];

        for (int i = 0; i < params.length; i++) {
            paramsWithId[i] = params[i];

        }
        paramsWithId[params.length] = data.getId();
        return paramsWithId;
    }


    public List<T> find(String query) {


        RowMapper<T> rowMapper = buildRowMapper();
        return getQueryResults(query, rowMapper );
    }

    public List<T> find(String query, Object[] params) {
        RowMapper<T> rowMapper = buildRowMapper();
        return getQueryResults(query, rowMapper );
    }


    public T getById(int id) {

//        if (cache.containsKey(id))
//            return cache.get(id);
        String getByIdQuery = buildGetByIdQuery();
        RowMapper<T> rowMapper = buildRowMapper();
        T result = executeQueryById(id,getByIdQuery,rowMapper);
       // cache.put(id,result);

        return result;
    }



    protected void insert(T data) {
        String saveQuery = buildSaveQuery();
        executeCommand(saveQuery, buildObjectParams(data));
    }


    protected void executeCommand(String query, Object[] params) {
        getJdbcTemplate().update(query,
                        params);
    }

    protected void executeDelete(int id, String query) {
        getJdbcTemplate().update(query,
                Long.valueOf(id));
    }

    protected JdbcTemplate getJdbcTemplate() {
        JdbcTemplate t;
        try {
            Entity a = type.newInstance();
            t = DataSourceHelper.getInstance().createJdbcTemplate(a.dataSource);

        } catch (Exception e) {
            e.printStackTrace();
            t = DataSourceHelper.getInstance().createJdbcTemplate();
        }
        return t;
    }

    protected List<T> getQueryResults(String query, RowMapper<T> r) {
        try{
            return getJdbcTemplate().query(query, r);
        }catch(EmptyResultDataAccessException e){
            return new ArrayList<T>();
        }
    }

    protected List<T> getQueryResults(String query, RowMapper<T> r, Object[] params) {
        try{
            return getJdbcTemplate().query(query, r, params);
        }catch(EmptyResultDataAccessException e){
            return new ArrayList<T>();
        }

    }


    protected T executeQueryById(int id, String query, RowMapper<T> m) {
        try{
            return  getJdbcTemplate().queryForObject(query,
                    new Object[]{id},
                    m);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }



}
