package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Carro;
import utils.Conexao;

public class DaoCarro {
	public static List<Carro> carros = new ArrayList<>();

	public static boolean salvar(Carro carro) {
		Connection con = Conexao.getConexao();
		String sql = "insert into tb_carros(placa, modelo)values(?,?)";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, carro.getPlaca());
			stm.setString(2, carro.getModelo());
			stm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;			
		}
		return true;
	}
}
