package com.dbc.repository;

import com.dbc.entities.BankAccount;
import com.dbc.entities.Donate;
import com.dbc.service.BankAccountService;
import com.dbc.service.DonateService;

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

            String sql = "DELETE FROM BANK_ACCOUNT WHERE id_bank_account = ?";

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
            sql.append("UPDATE BANK_ACCOUNT SET ");
            sql.append(" ACCOUNT_NUMBER =?,");
            sql.append(" AGENCY =?");
            sql.append(" WHERE ID_BANK_ACCOUNT =?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, bank_account.getAccount_number());
            stmt.setString(2, bank_account.getAgency());
            stmt.setInt(3,id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarBank_Account.res=" + res);

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

            String sql = "SELECT * FROM BANK_ACCOUNT";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                BankAccount bank_account = new BankAccount();
                bank_account.setId_bank_account(res.getInt("id_bank_account"));
                bank_account.setAccount_number(res.getString("account_number"));
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

    public BankAccount getBankAccountById(Integer id) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM BANK_ACCOUNT WHERE id_bank_account = " + id;

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                BankAccount account = new BankAccount();
                account.setId_bank_account(res.getInt("id_bank_account"));
                account.setAccount_number(res.getString("account_number"));
                account.setAgency(res.getString("agency"));

                return account;
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
        return null;
    }
    public static void main(String[] args) throws SQLException {
        // DonateRepository donateRepository = new DonateRepository();
//    donateRepository.list().forEach(System.out::println);
    //donateRepository.remove(1);
    Donate donate=new Donate();
    donate.setDonator_name("ana");
    donate.setDonator_email("ana@gmail");
    donate.setDonate_value(1000.0);
    donate.setDescription(null);
    donate.setRequest(new RequestRepository().getRequestById(2));
    DonateService donateService = new DonateService();
    donateService.add(donate);

//        bankAccountRepository.remove(12);
//        BankAccount bankAccount = new BankAccount();
//        bankAccount.setId_bank_account(2);
//        bankAccount.setAccount_number("5900498");
//        bankAccount.setAgency("1789");
        //adicionando mesmo quando tem uma igual
//        BankAccountService bankAccountService= new BankAccountService();
//        bankAccountService.add(bankAccount);
//        bankAccountRepository.list().forEach(System.out::println);
//        //não da update
//      bankAccountRepository.update(2, bankAccount);
//        bankAccountRepository.list().forEach(System.out::println);
   }
}
