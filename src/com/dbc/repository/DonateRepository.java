package com.dbc.repository;

import com.dbc.entities.Donate;

import java.sql.*;
import java.util.List;

public class DonateRepository implements Repository<Integer, Donate> {

    @Override
    public Integer getNextId(Connection connection) throws SQLException {
        String sql = "SELECT donate_seq.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Donate add(Donate donate) throws SQLException {
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();

            Integer proximoId = this.getNextId(con);
            donate.setId_donate(proximoId);

            String sql = "INSERT INTO DONATE\n" +
                    "(ID_PESSOA, NOME, EMAIL, VALUE)\n" +
                    "VALUES(?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, donate.getId_donate());
            stmt.setString(2, donate.getDonator_name());
            stmt.setString(3, donate.getDonator_email());
            stmt.setDouble(4, donate.getDonate_value());
            stmt.setString(5, donate.getDescription());
            stmt.setObject(6, donate.getRequest());
            int res = stmt.executeUpdate();
            System.out.println("adicionarDonate.res=" + res);
            return donate;
        } catch (SQLException e) {
            throw new SQLException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean remove(Integer id) throws SQLException {
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();

            String sql = "DELETE FROM PESSOA WHERE id_pessoa = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerPessoaPorId.res=" + res);

            return res > 0;
        } catch (SQLException e) {
            throw new SQLException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean update(Integer id, Donate donate) throws SQLException {
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PESSOA SET ");
            sql.append(" cpf = ?,");
            sql.append(" nome = ?,");
            sql.append(" data_nascimento = ? ");
            sql.append(" WHERE id_pessoa = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, donate.getId_donate());
            stmt.setString(2, donate.getDonator_name());
            stmt.setString(3, donate.getDonator_email());
            stmt.setDouble(4, donate.getDonate_value());
            stmt.setString(5, donate.getDescription());
            stmt.setObject(6, donate.getRequest());

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarPessoa.res=" + res);

            return res > 0;
        } catch (SQLException e) {
            throw new SQLException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Donate> list() throws SQLException {
        return null;
    }
}
