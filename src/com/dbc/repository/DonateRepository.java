package com.dbc.repository;

import com.dbc.entities.Donate;
import com.dbc.entities.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonateRepository implements Repository<Integer, Donate> {

    @Override
    public Integer getNextId(Connection connection) throws SQLException {
        String sql = "SELECT DONATOR_PROJECT.donate_seq.nextval mysequence FROM DUAL";

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

            Integer nextId = this.getNextId(con);
            donate.setId_donate(nextId);

            String sql = "INSERT INTO DONATE\n" +
                    "(ID_DONATE, ID_REQUEST, DONATOR_NOME, DONATOR_EMAIL, DONATOR_VALUE, DONATOR_DESCRIPTION)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, donate.getId_donate());
            stmt.setInt(2, donate.getRequest().getIdRequest());
            stmt.setString(3, donate.getDonator_name());
            stmt.setString(4, donate.getDonator_email());
            stmt.setDouble(5, donate.getDonate_value());
            stmt.setString(6, donate.getDescription());

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

            String sql = "DELETE FROM PESSOA WHERE id_donate = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerDonatePorId.res=" + res);

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
            sql.append("UPDATE DONATE SET ");
            sql.append(" ID_DONATE = ?,");
            sql.append(" ID_REQUEST = ?,");
            sql.append(" DONATOR_NOME = ? ,");
            sql.append(" DONATOR_EMAIL = ? ,");
            sql.append(" DONATOR_VALUE = ? ,");
            sql.append(" DONATOR_DESCRIPTION = ? ,");
            sql.append(" WHERE ID_DONATE = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, donate.getId_donate());
            stmt.setInt(2, donate.getRequest().getIdRequest());
            stmt.setString(3, donate.getDonator_name());
            stmt.setString(4, donate.getDonator_email());
            stmt.setDouble(5, donate.getDonate_value());
            stmt.setString(6, donate.getDescription());

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarDonate.res=" + res);

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
        List<Donate> donates = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            Statement stmt = conn.createStatement();

            String sql = "SELECT * from DONATE";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Donate donate = new Donate();
                donate.setId_donate(res.getInt("id_donate"));
                donate.setRequest(res.getObject("request"));
                donate.setDonator_name(res.getString("donator_name"));
                donate.setDonator_email(res.getString("donator_email"));
                donate.setDonate_value(res.getDouble("donate_value"));
                donate.setDescription(res.getString("description"));

                donates.add(donate);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getCause());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return donates;
    }
}
