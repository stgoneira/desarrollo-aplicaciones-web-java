package dao.pg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import dao.AlumnoDAO;
import dao.PersistenciaException;
import modelo.Alumno;
import util.DBUtil;

public class AlumnoPg implements AlumnoDAO {

	private Alumno makeAlumno(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String nombre = rs.getString("nombre");
		String carrera = rs.getString("carrera");
		LocalDate nacimiento = rs.getObject("nacimiento", LocalDate.class);
		return new Alumno(id, nombre, carrera, nacimiento);
	}
	
	@Override
	public Alumno getById(Integer id) throws PersistenciaException {
		try (
			Connection conexion = DBUtil.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM alumnos WHERE id = ?");
		) {
			declaracion.setInt(1, id);
			ResultSet rs = declaracion.executeQuery();
			if(rs.next()) {
				return makeAlumno(rs);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error SQLException", e);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error JNDI", e);
		} 
	}

	@Override
	public List<Alumno> getAll() throws PersistenciaException {
		try (
			Connection conexion = DBUtil.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM alumnos");
		) {
			List<Alumno> alumnos = new ArrayList<>();
			ResultSet rs = declaracion.executeQuery();
			while(rs.next()) {
				alumnos.add( makeAlumno(rs) );
			}
			return alumnos;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error SQLException", e);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error JNDI", e);
		} 
	}

	@Override
	public void remove(Integer id) throws PersistenciaException {
		try (
			Connection conexion = DBUtil.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement("DELETE FROM alumnos WHERE id = ?");
		) {
			declaracion.setInt(1, id);
			declaracion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error SQLException", e);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error JNDI", e);
		} 
	}

	@Override
	public void update(Alumno entity) throws PersistenciaException {
		try (
			Connection conexion = DBUtil.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement("UPDATE alumnos SET nombre = ?, carrera = ?, nacimiento = ? WHERE id = ?");
		) {
			declaracion.setString(1, entity.getNombre());
			declaracion.setString(2, entity.getCarrera());
			declaracion.setObject(3, entity.getFechaNacimiento());
			declaracion.setInt(4, entity.getId());
			declaracion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error SQLException", e);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error JNDI", e);
		} 
		
	}

	@Override
	public void insert(Alumno entity) throws PersistenciaException {
		try (
			Connection conexion = DBUtil.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement("INSERT INTO alumnos(nombre, carrera, nacimiento) VALUES(?,?,?)");
		) {
			declaracion.setString(1, entity.getNombre());
			declaracion.setString(2, entity.getCarrera());
			declaracion.setObject(3, entity.getFechaNacimiento());
			declaracion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error SQLException", e);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error JNDI", e);
		} 
	}

}
