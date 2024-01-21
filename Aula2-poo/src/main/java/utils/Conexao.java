package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	 public static String conectar() {
		 String retorno;
		 try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
			retorno = "Connected";
		} catch (ClassNotFoundException | SQLException e) {		
			retorno = e.getMessage();
		} 
		 return retorno;
	 }
}
