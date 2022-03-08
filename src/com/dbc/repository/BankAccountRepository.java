package com.dbc.repository;

import com.dbc.entities.BankAccount;
import com.dbc.entities.Donate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountRepository implements Repository<Integer, BankAccount> {
    @Override
    public Integer getNextId(Connection connection) throws SQLException {
        String sql = "SELECT DONATOR_PROJECT.bank_account_seq.nextval mysequence FROM DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public BankAccount add(BankAccount bank_account) throws SQLException {
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();

            Integer nextId = this.getNextId(con);
            bank_account.setId_bank_account(nextId);

            String sql = "INSERT INTO BANK_ACCOUNT\n" +
                    "(ID_BANK_ACCOUNT, ACCOUNT_NUMBER, AGENCY)\n" +
                    "VALUES(?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, bank_account.getId_bank_account());
            stmt.setString(2, bank_account.getAccount_number());
            stmt.setString(3, bank_account.getAgency());


            int res = stmt.executeUpdate();
            System.out.println("adicionarDonate.res=" + res);
            return bank_account;
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

            String sql = "DELETE FROM PESSOA WHERE id_bank_account = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerBank_AccountPorId.res=" + res);

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
    public boolean update(Integer id, BankAccount bank_account) throws SQLException {
        Connection con = null;
        try {
            con = ConnectionDB.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE DONATE SET ");
            sql.append(" ID_BANK_ACCOUNT = ?,");
            sql.append(" ACCOUNT_NUMBER = ?,");
            sql.append(" AGENCY = ? ,");
            sql.append(" WHERE id_bank_account = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, bank_account.getId_bank_account());
            stmt.setString(2, bank_account.getAccount_number());
            stmt.setString(3, bank_account.getAgency());

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarBank_account.res=" + res);

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
    public List<BankAccount> list() throws SQLException {
        List<BankAccount> bank_accounts = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            Statement stmt = conn.createStatement();

            String sql = "SELECT * BANK_ACCOUNT";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                BankAccount bank_account = new BankAccount();
                bank_account.setId_bank_account(res.getInt("id_bank_account"));
                bank_account.setAccount_number(res.getString("account"));
                bank_account.setAgency(res.getString("agency"));

                bank_accounts.add(bank_account);
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
        return bank_accounts;
    }
}
