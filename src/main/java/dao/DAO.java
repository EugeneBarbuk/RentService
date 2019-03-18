package dao;



import java.util.List;

public interface DAO<T> {

    void addEntity(T t) throws DaoException;

    void deleteEntity(int id) throws DaoException;

    void updateEntity(T t) throws DaoException;

    List<T> findAllEntity() throws DaoException;

    public T findEntityById(int id) throws DaoException;
}
