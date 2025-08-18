package db;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

public class DB {



    public static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();

            props.load(fs);

            return props;
        } catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }
}
