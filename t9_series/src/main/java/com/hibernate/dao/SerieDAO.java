package com.hibernate.dao;

import java.util.List;

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
	public void updateSerie(Serie s) {
		Transaction transaction=null;
		try(Session session=SerieUtils.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			//actualiza los datos de ese objeto de clase en la base de datos
			session.merge(s);
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();			
			}
		}
	}
	
	
	//borrar
	public void deleteSerie(int id) {
		Transaction transaction=null;
		Serie s=null;
		try(Session session=SerieUtils.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			s=session.get(Serie.class, id);
			session.remove(s);
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();			
			}
		}
	}
	
	
	//lista
	public List<Serie> selectAllSerie(){
		Transaction transaction=null;
		List<Serie> series=null;
		try(Session session=SerieUtils.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			series=session.createQuery("FROM Serie", Serie.class).getResultList();
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();			
			}
		}
		return series;
	}
	
	
	//select
	public Serie selectSerieById(int id) {
		Transaction transaction=null;
		Serie s=null;
		try(Session session=SerieUtils.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			s=session.get(Serie.class, id);
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();			
			}
		}
		return s;
	}
	
	
	
}//DAO
