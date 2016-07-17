package hu.jegyertekesito.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import hu.jegyertekesito.dao.FelhasznaloDAO;
import hu.jegyertekesito.model.Felhasznalo;


public class FelhasznaloDAOImpl implements FelhasznaloDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Felhasznalo> listFelhasznalok() {
	      String SQL = "select * from Felhasznalo";
	      List<Felhasznalo> felhasznalok = jdbcTemplate.query(SQL, new RowMapper<Felhasznalo>(){
	      
	      @Override
	      public Felhasznalo mapRow(ResultSet rs, int rowNum) throws SQLException{
	  		Felhasznalo felhasznalo = new Felhasznalo();
	  		felhasznalo.setFelhasznaloID(rs.getInt("id"));
	  		felhasznalo.setFelhasznalonev(rs.getString("felhasznalonev"));
	  		felhasznalo.setJelszo(rs.getString("jelszo"));
	  		felhasznalo.setSzulido(rs.getDate("szulido"));
	  		felhasznalo.setKeresztnev(rs.getString("keresztnev"));
	  		felhasznalo.setVezeteknev(rs.getString("vezeteknev"));
	  		felhasznalo.setEmail(rs.getString("email"));
	  		felhasznalo.setLakhely(rs.getString("lakhely"));
	  		felhasznalo.setUtca(rs.getString("utca"));
	  		felhasznalo.setTelefonszam(rs.getInt("telefonszam"));
	  		felhasznalo.setNeme(rs.getBoolean("neme"));
	  		
	  		return felhasznalo;
	      	}
	      });
	return felhasznalok;
	}
}
