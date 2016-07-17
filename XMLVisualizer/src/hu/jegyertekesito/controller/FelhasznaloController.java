package hu.jegyertekesito.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hu.jegyertekesito.dao.FelhasznaloDAO;
import hu.jegyertekesito.model.Felhasznalo;
@Controller
public class FelhasznaloController {
	@Autowired
	private FelhasznaloDAO FelhasznaloDAO;
	
	@RequestMapping(value="/")
	public ModelAndView felhasznalok(ModelAndView model) throws IOException, SQLException{
		List<Felhasznalo> felhasznalok = FelhasznaloDAO.listFelhasznalok();
	    model.addObject("felhasznalok", felhasznalok);
	    model.setViewName("index");
	 
	    return model;
	}
	
	
}