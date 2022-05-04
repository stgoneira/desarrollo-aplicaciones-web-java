package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import modelo.Alumno;

public class AlumnoDAOImp extends GenericDAO<Alumno, Integer> implements AlumnoDAO {

	public AlumnoDAOImp() {
		// nombre tabla 
		super("alumnos");
	}
	
	@Override
	public List findByCarrera(int carreraId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno makeObjectEntity(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String nombre = resultSet.getString("nombre");
		return new Alumno(id, nombre);
	}

}
