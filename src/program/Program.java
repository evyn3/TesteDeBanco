package program;

import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args){
        Connection conn = null;

        Statement st = null; //interface que representa uma instrução em sql para execultar em um banco de dados
        ResultSet rs = null; //interface que representa o conjuto dos resultados de uma conculta em um banco de dados

        try{
            conn = DB.getConnection();//tenta iniciar a conexão

            st = conn.createStatement();//cria a conexão com o banco

            rs = st.executeQuery("select * from department");//cria consulta com o banco

            while (rs.next()){ //percorre o banco
                System.out.println(rs.getInt("id") + " - " + rs.getString("Name"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
            DB.closeConnection();

        }

    }

}
