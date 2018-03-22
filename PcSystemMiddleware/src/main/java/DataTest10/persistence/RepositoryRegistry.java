package DataTest10.persistence;

import java.util.HashMap;

class RepositoryRegistry {

    private HashMap<String,AbstractRepository> repositories;
    private static RepositoryRegistry  instance;
    private RepositoryRegistry(){

    }

    public static RepositoryRegistry getInstance(){
        if (instance == null)
            instance = new RepositoryRegistry();

        return instance;
    }


    public <T extends Entity> AbstractRepository<T> getRepository(Class<T> type){


        for (String s : repositories.keySet()) {
            if (type.getName().equals(s))
                return repositories.get(s);
        }

        throw  new RuntimeException("Repository not configured");

    }


    <T> void register(AbstractRepository repository, Class<T> type){
        repository.setType(type);
        repositories.put(type.getName(), repository );
    }




}
