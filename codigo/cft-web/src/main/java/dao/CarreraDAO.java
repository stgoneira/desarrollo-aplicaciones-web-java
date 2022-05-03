package dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import modelo.Carrera;

public interface CarreraDAO {

	public List<Carrera> findAllCarreras()  throws SQLException, NamingException;
	public Carrera findCarreraById(int id)  throws SQLException, NamingException;
	public void crearCarrera(Carrera carrera) throws SQLException, NamingException;
	public void editarCarrera(Carrera carrera) throws SQLException, NamingException;
	public void borrarCarrera(int id) throws SQLException, NamingException;
	
}
