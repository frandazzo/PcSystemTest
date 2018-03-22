package DataTest10.persistence;

import com.sun.javafx.binding.StringFormatter;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

class DataSourceHelper {

    private static  DataSourceHelper instance;
    private HashMap<String,BasicDataSource> dataSources = new HashMap<>();

    private static List<DataSourceConfiguration> configurations;

    private DataSourceHelper(){


        for (DataSourceConfiguration configuration : configurations) {
            BasicDataSource dataSource = new BasicDataSource();

            if(configuration==null)
                throw new RuntimeException("Data source non congigurato");

            dataSource.setDriverClassName(configuration.getConnectionDriver());
            dataSource.setUrl(configuration.getConnectionString());
            dataSource.setUsername(configuration.getUsername());
            dataSource.setPassword(configuration.getPassword());

            dataSource.setMaxActive(-1);
            dataSource.setMaxWait(-1);
            dataSource.setMaxIdle(-1);

            dataSources.put(configuration.getDataSourceName(),dataSource);
        }

//
    }

    public static void registerConfiguration(List<DataSourceConfiguration> configs){

       configurations=configs;

    }

    public synchronized static DataSourceHelper getInstance(){
        if (instance == null)
            instance = new DataSourceHelper();


        return instance;
    }

    public DataSource getDataSource(){
        return  dataSources.get(Entity.DEFAULT_DATA_SOURCE);
    }

    public DataSource getDataSource(String dataSource){

        BasicDataSource ds =  dataSources.get(dataSource);
        if (ds == null)
            throw new RuntimeException("Data source non congigurato");

        return ds;
    }


    public JdbcTemplate createJdbcTemplate(){
        return  new JdbcTemplate(getDataSource());
    }

    public JdbcTemplate createJdbcTemplate(String dataSource){
        return  new JdbcTemplate(getDataSource(dataSource));
    }



    public String toString(){

        StringBuilder builder = new StringBuilder();

        for (String s : dataSources.keySet()) {
            BasicDataSource configuration = dataSources.get(s);
            builder.append(String.format("%s - %s \n", "Datasource", s));
//            builder.append(String.format("%s-%s", "driverName" , configuration.getConnectionDriver()));
//            builder.append(String.format("%s-%s", "connection url" , configuration.getConnectionString()));

        }


       return builder.toString();
    }


}
