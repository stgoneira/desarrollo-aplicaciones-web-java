package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Alumno;
import modelo.Carrera;

public class AlumnoDAOImp implements AlumnoDAO {

	private CarreraDAO carreraDAO;
	
	public AlumnoDAOImp(CarreraDAO carreraDAO) {
		this.carreraDAO = carreraDAO;
	}
	
	@Override
	public List<Alumno> findAllAlumnos() throws SQLException, NamingException {
		try (
			Connection conexion = DbUtils.getConexion();
			Statement declaracion = conexion.createStatement();
		) {			
			ResultSet rs = declaracion.executeQuery("SELECT * FROM alumnos");
			List<Alumno> alumnos = new ArrayList<>();
			while(rs.next()) {
				// recuperar a variables datos de la tabla 
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				int carreraId = Integer.parseInt( rs.getString("carrera_id") );
				Carrera carrera = carreraDAO.findCarreraById(carreraId);
				LocalDate fechaNacimiento = rs.getObject("fecha_nacimiento", LocalDate.class);
				// instanciar objeto alumno 
				Alumno alumno = new Alumno(id, nombre, carrera, fechaNacimiento);
				// agregar a la lista
				alumnos.add(alumno);
			}
			return alumnos;
		}	
	}

	@Override
	public Alumno findAlumnoById(int alumnoId) throws SQLException, NamingException {
		try (
			Connection conexion = DbUtils.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM alumnos WHERE id = ?");
		) {
			declaracion.setInt(1, alumnoId);
			ResultSet rs = declaracion.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String carrera = rs.getString("carrera");
				LocalDate fechaNacimiento = rs.getObject("fecha_nacimiento", LocalDate.class);
				return new Alumno(id, nombre, carrera, fechaNacimiento);
			} else {
				return null;
			}
		}
	}

	@Override
	public void crearAlumno(Alumno alumno) throws SQLException, NamingException {
		String sql = "INSERT INTO alumnos(nombre, carrera, fecha_nacimiento) VALUES(?, ?, ?)";
		try (
			Connection conexion = DbUtils.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
		) {
			declaracion.setString(1, alumno.getNombre());
			declaracion.setString(2, alumno.getCarrera());
			declaracion.setObject(3, alumno.getFechaNacimiento());
			int filasInsertadas = declaracion.executeUpdate();
		}	
	}

	@Override
	public void editarAlumno(Alumno alumno) throws SQLException, NamingException {
		String sql = "UPDATE alumnos"
				+" SET nombre = ?, carrera = ?, fecha_nacimiento = ?"
				+" WHERE id = ?";
		try (
			Connection conexion = DbUtils.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
		) {
			declaracion.setString(1, alumno.getNombre());
			declaracion.setString(2, alumno.getCarrera());
			declaracion.setObject(3, alumno.getFechaNacimiento());
			declaracion.setInt(4, alumno.getId());
			declaracion.executeUpdate();
		}			
	}

	@Override
	public void borrarAlumno(int alumnoId) throws SQLException, NamingException {
		try (
			Connection conexion = DbUtils.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement("DELETE FROM alumnos WHERE id = ?");
		) {
			declaracion.setInt(1, alumnoId);
			int filasEliminadas = declaracion.executeUpdate();
		}			
	}

}
