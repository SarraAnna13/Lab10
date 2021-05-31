package it.polito.tdp.rivers.model;

import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
private RiversDAO dao;

public Model ()
{dao= new RiversDAO();
	}

public List <River> getRivers()
{return dao.getAllRivers();
	}

public DatiFiume getDati(int id)
{return dao.getDati(id);
	}
}
