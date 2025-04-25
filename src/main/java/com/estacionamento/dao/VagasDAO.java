package com.estacionamento.dao;

import com.estacionamento.database.Conexao;
import com.estacionamento.model.Status;
import com.estacionamento.model.Vagas;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class VagasDAO
{
    public List<Vagas> listarTodas() {
        List<Vagas> vagas = new ArrayList<>();
        String sql = "SELECT * FROM Vagas";

        try(
                Connection connection = Conexao.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
           )
        {
            while(rs.next())
            {
                Vagas vaga = new Vagas();

                vaga.setId(rs.getInt("id"));
                vaga.setNumero(rs.getInt("numero"));
                vaga.setStatus(Status.valueOf(rs.getString("status")));
                vaga.setPlaca(rs.getString("placa"));


                Timestamp ts = rs.getTimestamp("entrada");
                vaga.setEntrada(ts != null ? LocalTime.from(ts.toLocalDateTime()) : null);

                vagas.add(vaga);
            }


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return vagas;
    }

    public boolean ocuparVaga(int numero,  String placa, LocalDateTime entrada)
    {
        String sql = "UPDATE vagas SET status = ?, placa = ?, entrada = ? WHERE numero = ?";

        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)
        ){

            stmt.setString(1, Status.OCUPADA.name());
            stmt.setString(2, placa);
            stmt.setTimestamp(3, Timestamp.valueOf(entrada));
            stmt.setInt(4, numero);

            return stmt.executeUpdate() > 0;
        }
        catch (SQLException e)
            {
            e.printStackTrace();
            return false;
            }

    }

    public boolean liberarVaga(int numero)
    {
        String sql = "UPDATE vagas SET status = ?, placa = NULL, entrada = NULL WHERE numero = ?";


        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Status.DISPONIVEL.name());
            stmt.setInt(2, numero);

            return stmt.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public Vagas buscarPorNumero(int numero) {
        String sql = "SELECT * FROM vagas WHERE numero = ?";
        Vagas vaga = null;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numero);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vaga = new Vagas();
                    vaga.setId(rs.getInt("id"));
                    vaga.setNumero(rs.getInt("numero"));
                    vaga.setStatus(Status.valueOf(rs.getString("status")));
                    vaga.setPlaca(rs.getString("placa"));

                    Timestamp ts = rs.getTimestamp("entrada");
                    vaga.setEntrada(ts != null ? LocalTime.from(ts.toLocalDateTime()) : null);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vaga;
    }
}
