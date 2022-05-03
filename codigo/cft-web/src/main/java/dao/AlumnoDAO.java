package dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import modelo.Alumno;

public interface AlumnoDAO {

	public List<Alumno> findAllAlumnos()  throws SQLException, NamingException;
	public Alumno findAlumnoById(int id)  throws SQLException, NamingException;
	public void crearAlumno(Alumno alumno) throws SQLException, NamingException;
	public void editarAlumno(Alumno alumno) throws SQLException, NamingException;
	public void borrarAlumno(int id) throws SQLException, NamingException;
	
}
