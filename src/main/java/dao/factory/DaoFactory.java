package dao.factory;


import dao.BicycleDaoImpl;
import dao.DAO;
import dao.RentServiceDaoImpl;
import dao.UserDaoImpl;
import dao.issue.DatabaseManager;
import dao.postgree.BicycleDaoPostgre;
import dao.postgree.RentServiceDaoPostgre;
import dao.postgree.UserDaoPostgre;

public class DaoFactory {

    public static DAO getUserDAO(String dataBaseType) {
        if (dataBaseType.equalsIgnoreCase(DatabaseManager.getProperty(DatabaseManager.DATABASE_TYPE))) {
            return new UserDaoImpl();
        } else {
            return new UserDaoPostgre();
        }
    }

    public static DAO getBicycleDAO(String dataBaseType) {
        if (dataBaseType.equalsIgnoreCase(DatabaseManager.getProperty(DatabaseManager.DATABASE_TYPE))) {
            return new BicycleDaoImpl();
        } else {
            return new BicycleDaoPostgre();
        }
    }

    public static DAO getRentServiceDAO(String dataBaseType) {
        if (dataBaseType.equalsIgnoreCase(DatabaseManager.getProperty(DatabaseManager.DATABASE_TYPE))) {
            return new RentServiceDaoImpl();
        } else {
            return new RentServiceDaoPostgre();
        }
    }
}

