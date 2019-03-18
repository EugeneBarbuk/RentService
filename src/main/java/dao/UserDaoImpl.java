package dao;


import entity.User;
import pool.ConnectionPool;
import pool.PoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements DAO<User> {
    private Connection connection;
    private static final String ADD_USER="insert into user(name,login,balance) values (?, ?, ? )";
    private static final String DELETE_USER="delete from user where idUser=?";
    private static final String UPDATE_USER="update user set name=?, login=?, balance=? where idUser=?";
    private static final String FIND_USER_BY_ID="select * from user where idUser=?";
    private static final String FIND_ALL_USERS="select * from users";

    public UserDaoImpl(){
        connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public void addEntity(User user) throws DaoException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
  //          preparedStatement.setInt(3, user.getBalance());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
           throw new DaoException(e);
        }
    }


    @Override
    public void deleteEntity(int id)  throws DaoException {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_USER);
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateEntity(User user) throws DaoException {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_USER);
            // Parameters start with 1
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
       //     preparedStatement.setInt(3, user.getBalance());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public List<User> findAllEntity() throws DaoException {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_ALL_USERS);
            while (rs.next()) {
                User entity = new User();
                entity.setId(rs.getInt("idUser"));
                entity.setName(rs.getString("name"));
                entity.setLogin(rs.getString("login"));
       //         entity.setBalance(rs.getInt("balance"));
                users.add(entity);
            }
        } catch (SQLException e) {
            throw new  DaoException(e);
        }

        return users;
    }

    @Override
    public User findEntityById(int id) throws DaoException {
        User entity = new User();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement(FIND_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                entity.setId(rs.getInt("user_id"));
                entity.setName(rs.getString("name"));
                entity.setLogin(rs.getString("login"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return entity;
    }
}
