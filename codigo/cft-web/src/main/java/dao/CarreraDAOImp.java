package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import modelo.Carrera;

public class CarreraDAOImp implements CarreraDAO {

	@Override
	public List<Carrera> findAllCarreras() throws SQLException, NamingException {
		try (
			Connection conexion = DbUtils.getConexion();
			Statement declaracion = conexion.createStatement();
		) {			
			ResultSet rs = declaracion.executeQuery("SELECT * FROM carreras");
			List<Carrera> carreras = new ArrayList<>();
			while(rs.next()) {
				// recuperar a variables datos de la tabla 
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");				
				Carrera carrera = new Carrera(id, nombre);
				// agregar a la lista
				carreras.add( carrera );
			}
			return carreras;
		}	
	}

	@Override
	public Carrera findCarreraById(int carreraId) throws SQLException, NamingException {
		String sql = "SELECT * FROM carreras WHERE id = ?";
		try (
			Connection conexion = DbUtils.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
		) {
			declaracion.setInt(1, carreraId);
			ResultSet rs = declaracion.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				return new Carrera(id, nombre);
			} else {
				return null;
			}
		}
	}

	@Override
	public void crearCarrera(Carrera carrera) throws SQLException, NamingException {
		String sql = "INSERT INTO carreras(nombre) VALUES(?)";
		try (
			Connection conexion = DbUtils.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
		) {
			declaracion.setString(1, carrera.getNombre());			
			int filasInsertadas = declaracion.executeUpdate();
		}	
	}

	@Override
	public void editarCarrera(Carrera carrera) throws SQLException, NamingException {
		String sql = "UPDATE carreras"
				+" SET nombre = ?"
				+" WHERE id = ?";
		try (
			Connection conexion = DbUtils.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
		) {
			declaracion.setString(1, carrera.getNombre());			
			declaracion.setInt(2, carrera.getId());
			declaracion.executeUpdate();
		}	
	}

	@Override
	public void borrarCarrera(int carreraId) throws SQLException, NamingException {
		try (
			Connection conexion = DbUtils.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement("DELETE FROM carreras WHERE id = ?");
		) {
			declaracion.setInt(1, carreraId);
			int filasEliminadas = declaracion.executeUpdate();
		}
	}

}
