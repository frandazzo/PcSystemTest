package DataTest10.persistence;

import java.util.List;

public class PersistenceService {

    private static PersistenceService instance;
    private PersistenceService(){}

    public static PersistenceService getInstance() {
        if (instance == null)
            instance = new PersistenceService();

        return instance;
    }



    public <T extends Entity> void registerRepository(AbstractRepository<T> repository, Class<T> type){

        RepositoryRegistry.getInstance().register(repository, type);

    }


    public void registerDatasources(List<DataSourceConfiguration> configs){

        DataSourceHelper.registerConfiguration(configs);

    }


    public  <T extends Entity> AbstractRepository<T> getRepositoryOf(Class<T> type){
        return RepositoryRegistry.getInstance().getRepository(type);
    }


    public <T extends Entity> List<T> getAll(Class<T> type) {
        AbstractRepository<T> rep = getRepositoryOf(type);
        return rep.getAll();
    }

    public<T extends Entity> void delete(int id, Class<T> type) {
        AbstractRepository<T> rep = getRepositoryOf(type);
        rep.delete(id);
    }

    public <T extends Entity> void saveOrUpdate(T data, Class<T> type){
        AbstractRepository<T> rep = getRepositoryOf(type);
        rep.saveOrUpdate(data);
    }


    public <T extends Entity> List<T> find(String query, Class<T> type) {
        AbstractRepository<T> rep = getRepositoryOf(type);
        return rep.find(query);


    }

    public<T extends Entity> List<T> find(String query, Object[] params, Class<T> type) {
        AbstractRepository<T> rep = getRepositoryOf(type);
        return rep.find(query, params);
    }


    public<T extends Entity> T getById(int id, Class<T> type) {

        AbstractRepository<T> rep = getRepositoryOf(type);
        return rep.getById(id);
    }









}
