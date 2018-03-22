package DataTest10.persistence;

import java.util.HashMap;

public abstract class Entity {


    protected int id;
    protected String dataSource;

    static final String DEFAULT_DATA_SOURCE = "default";

    public Entity(){
        dataSource = DEFAULT_DATA_SOURCE;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String, String> validate(){
        return  new HashMap<>();
    }

    public static String extractString(HashMap<String, String> errors) {
        StringBuilder b = new StringBuilder();

        for (String key : errors.keySet()) {
            b.append(String.format("%s - %s   \n", key, errors.get(key)));
        }

        return b.toString();
    }

}
