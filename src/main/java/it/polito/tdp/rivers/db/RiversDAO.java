package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.DatiFiume;
import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public DatiFiume getDati(int id)
	{String sql="SELECT f.river AS id, MIN(f.day) AS dataMinima, MAX(f.day) AS dataMassima, COUNT(*) AS numeroMisure, SUM(f.flow)/COUNT(*) AS Media "
			+ "FROM flow AS f "
			+ "WHERE f.river=? "
			+ "GROUP BY f.river ";
	DatiFiume result;
	
	try {
		Connection conn = DBConnect.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, id);
		ResultSet res = st.executeQuery();
		result= new DatiFiume(res.getInt("id"), res.getDate("dataMinima").toLocalDate(), res.getDate("dataMassima").toLocalDate(), res.getInt("numeroMisure"), res.getDouble("Media"));
		
		conn.close();
		return result;
	} catch (SQLException e) {
		//e.printStackTrace();
		throw new RuntimeException("SQL Error");
	}
		
	}
}
