package dao;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public interface BaseDAO<E, K> {

	public List<E> findAll() throws SQLException, NamingException;
	public E findById(K id) throws SQLException, NamingException;
	public void create(E entidad) throws SQLException, NamingException;
	public void edit(E entidad) throws SQLException, NamingException;
	public void remove(K id) throws SQLException, NamingException;
	
}
