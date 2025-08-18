package db;

import java.io.IOException; //tratamento de exceções
import java.sql.Connection; //conexão com o banco de dados
import java.sql.DriverManager; //usado pra fazr a conexão
import java.sql.SQLException;
import java.util.Properties; // carrega pares chave-valor de arquivos "[chave]=[valor]"
import java.io.FileInputStream; //ler

public class DB {

    private static Connection conn = null;//instancia uma conexão

    public static Connection getConnection(){ //abre e faz a conexão com o banco
        if(conn == null){
            try{
                Properties props = loadProperties();

                String url = props.getProperty("dburl");

                conn = DriverManager.getConnection(url, props);

            } catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection(){ //fecha a conexão
        if(conn!=null){
            try{
                conn.close();
            } catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    public static Properties loadProperties(){ //lê o db.properties
        try(FileInputStream fs = new FileInputStream("db.properties")){ //fs recebe a leitura do db.properties
            Properties props = new Properties();

            props.load(fs); //props recebe os pares chave-valor que estão no db.properties

            return props;
        } catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }
}
