package com.dbc.repository;

import com.dbc.entities.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestRepository implements Repository<Integer, Request> {

    @Override
    public Integer getNextId(Connection connection) throws SQLException {
        String sql = "SELECT DONATOR_PROJECT.request_seq.nextval mysequence FROM DUAL";

        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Request add(Request request) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            Integer nextId = this.getNextId(conn);
            request.setIdRequest(nextId);

            String sql = "INSERT INTO REQUEST\n" +
                    "(id_request, title, description, goal, reached_value, id_category, id_bank_account, id_user)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, request.getIdRequest());
            statement.setString(2, request.getTitle());
            statement.setString(3, request.getDescription());
            statement.setDouble(4, request.getGoal());
            statement.setDouble(5, request.getReachedValue());
            statement.setInt(6, request.getCategory().getIdCategory());
            statement.setInt(7, request.getAccount().getIdAccount());
            statement.setInt(8, request.getUser().getIdUser());

            int res = statement.executeUpdate();
            System.out.println("addRequest.res=" + res);
            return request;
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
    }

    @Override
    public boolean remove(Integer id) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            String sql = "DELETE FROM REQUEST WHERE id_request = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);

            int res = statement.executeUpdate();
            System.out.println("removeRequest.res=" + res);

            return res > 0;
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
    }

    @Override
    public boolean update(Integer id, Request request) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE REQUEST SET ");
            sql.append(" title = ?,");
            sql.append(" description = ?,");
            sql.append(" goal = ? ");
            sql.append("WHERE id_request = ?");

            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            stmt.setString(1, request.getTitle());
            stmt.setString(2, request.getDescription());
            stmt.setDouble(3, request.getGoal());
            stmt.setInt(4, id);

            int res = stmt.executeUpdate();
            System.out.println("updateRequest.res=" + res);

            return res > 0;
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
    }

    @Override
    public List<Request> list() throws SQLException {
        List<Request> requests = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            Statement stmt = conn.createStatement();

            String sql = "SELECT * REQUEST";

            ResultSet res = stmt.executeQuery(sql);

            // TODO - arrumar isso, mas depende dos metódos das outras classes
            while (res.next()) {
                Request request = new Request();
                request.setIdRequest(res.getInt("id_request"));
                request.setTitle(res.getString("title"));
                request.setDescription(res.getString("description"));
                request.setAccount(null);
                request.setCategory(null);
                request.setUser(null);
                request.setGoal(res.getDouble("goal"));
                request.setReachedValue(res.getDouble("reached_value"));

                requests.add(request);
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
        return requests;
    }
}
