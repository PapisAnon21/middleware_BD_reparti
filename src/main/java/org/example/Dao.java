package org.example;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class Dao {

    public ConnectionSource createConnexion(String source) throws SQLException {
        return new JdbcConnectionSource(source);
    }

    public void setConfiguration(ConnectionSource connexion_object, String user_name, String password)
    {
        ((JdbcConnectionSource)connexion_object).setUsername(user_name);
        ((JdbcConnectionSource)connexion_object).setPassword(password);
    }

    //public Dao<Student, String> initDao(ConnectionSource connexion_object, Object Class)
    //{
        //Dao<Student, String> StudentDao  = DaoManager.createDao(connexion_object, Class.class);

    //}
}
