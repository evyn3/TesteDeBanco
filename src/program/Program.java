package program;

import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            //DB.closeConnection();

        }

        System.out.println("--------------------------------------");

        SimpleDateFormat niversario = new SimpleDateFormat(("dd/MM/yyyy"));//formata a data
        PreparedStatement ps = null;//para inserir dados no banco

        try{
            ps = conn.prepareStatement(
                    "INSERT INTO seller"
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS //gera primary key
            );

            ps.setString(1, "Duda");
            ps.setString(2, "duda@gmail.com");
            ps.setDate(3, new java.sql.Date(niversario.parse("29/09/2000").getTime()));
            ps.setDouble(4, 55000.0);
            ps.setInt(5, 3);


            int rowsAffected = ps.executeUpdate(); //para executar

            System.out.println("Finalizando linha alterada " + rowsAffected);

            if(rowsAffected > 0){
                rs = ps.getGeneratedKeys();

                while(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! id = " + id);
                }
            }
            else{
                System.out.println("Nenhuma linha foi alterada");
            }

        }catch (SQLException | ParseException e){
            e.printStackTrace();
        } finally {
            DB.closeStatment(ps);
            DB.closeConnection();
        }

    }

}
