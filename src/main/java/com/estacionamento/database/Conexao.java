package com.estacionamento.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class Conexao
{
    private static final String PROPERTIES_FILE = "/database.properties";

    private static String url;
    private static String user;
    private static String password;

    static
    {
        try(InputStream in = Conexao.class.getResourceAsStream(PROPERTIES_FILE))
        {
            Properties prop = new Properties();
            if(in == null)
            {
               throw new RuntimeException("Não foi possível encontrar o arquivo!");
            }

            prop.load(in);
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");

        }
        catch (Exception ex)
        {
            throw new RuntimeException("Erro ao carregar arquivo!",ex);
        }


    }
    public static Connection getConnection() throws SQLException
    {
        try
        {
            return DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Erro ao conectar ao banco de dados",e);
        }
    }

}
