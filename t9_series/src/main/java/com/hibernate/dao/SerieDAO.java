package com.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Serie;
import com.hibernate.util.SerieUtils;

public class SerieDAO {

	
	//añadir
	public void insertSerie(Serie s) {
		Transaction transaction=null;
		try(Session session=SerieUtils.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			session.persist(s);
			transaction.commit();
		}
	}
	
	//actualizar
	
	
	//borrar
}
