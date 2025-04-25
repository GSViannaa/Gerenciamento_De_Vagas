package com.estacionamento.database;

import java.sql.*;
import java.sql.PreparedStatement;

public class VagasInitializer
{
    public static void popularVagas()
    {
        String sqlContar = "SELECT COUNT(*) FROM vagas";
        String sqlInserir = "INSERT INTO vagas (numero, status) VALUES (?,'DISPONIVEL')";

        try(
             Connection connection = Conexao.getConnection();
             PreparedStatement check = connection.prepareStatement(sqlContar);
             ResultSet rs = check.executeQuery()
                )
        {

               if(rs.next())
               {
                    int total = rs.getInt(1);

                    if(total == 0)
                    {
                       try(PreparedStatement insert = connection.prepareStatement(sqlInserir))
                       {

                           for(int i = 1; i <= 50; i++)
                           {
                               insert.setInt(1, i);
                               insert.executeUpdate();
                           }
                           System.out.println("✅ 50 Vagas inserida com sucesso!");
                       }

                    }
                    else
                    {
                        System.out.println("⚠️ Tabela 'vagas' já possui registros. Nenhuma vaga inserida.");
                    }


               }
        }
        catch (SQLException ex)
        {
            System.err.println("Erro ao popular vagas: " + ex.getMessage());
        }

    }
}
