package hu.jegyertekesito.dao;

import java.sql.SQLException;
import java.util.List;

import hu.jegyertekesito.model.Felhasznalo;

public interface FelhasznaloDAO {
	
	public List<Felhasznalo> listFelhasznalok();	
	

}