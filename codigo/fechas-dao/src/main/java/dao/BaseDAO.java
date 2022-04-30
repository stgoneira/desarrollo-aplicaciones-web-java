package dao;

import java.util.List;

public interface BaseDAO<E, K> {
	public E getById(K id) throws PersistenciaException;
	public List<E> getAll() throws PersistenciaException;
	public void remove(K id) throws PersistenciaException;
	public void update(E entity) throws PersistenciaException;
	public void insert(E entity) throws PersistenciaException;
}
