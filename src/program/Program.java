package program;

import db.DB;

import java.sql.Connection;

public class Program {
    public static void main(String[] args){
        Connection conn = DB.getConnection();

        System.out.println("Conexão realizada com sucesso!!!");

        DB.closeConnection();
    }

}
