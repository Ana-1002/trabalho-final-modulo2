package com.dbc.repository;

import com.dbc.entities.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements Repository<Integer, Category> {

    @Override
    public Integer getNextId(Connection connection) throws SQLException {
        String sql = "SELECT DONATOR_PROJECT.category_seq.nextval mysequence FROM DUAL";

        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Category add(Category category) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            Integer nextId = this.getNextId(conn);
            category.setIdCategory(nextId);

            String sql = "INSERT INTO CATEGORY\n" +
                    "(id_category, name, category_description)\n" +
                    "VALUES (?, ?, ?)\n";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, category.getIdCategory());
            stmt.setString(2, category.getName());
            stmt.setString(3, category.getDescription());

            int res = stmt.executeUpdate();
            System.out.println("addRequest.res=" + res);
            return category;
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

            String sql = "DELETE FROM CATEGORY WHERE id_category = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);

            int res = statement.executeUpdate();
            System.out.println("removeCategoryId.res=" + res);

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
    public boolean update(Integer id, Category category) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CATEGORY SET ");
            sql.append(" name = ?,");
            sql.append(" category_description = ?");
            sql.append("WHERE id_category = ?");

            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, id);

            int res = stmt.executeUpdate();
            System.out.println("updateCategory.res=" + res);

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
    public List<Category> list() throws SQLException {
        List<Category> categories = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM CATEGORY";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Category category = new Category();
                category.setIdCategory(res.getInt("id_category"));
                category.setName(res.getString("name"));
                category.setDescription(res.getString("category_description"));

                categories.add(category);
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
        return categories;
    }

    public boolean nameAlreadyExists(String name) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM CATEGORY WHERE LOWER(name) = " + name;

            ResultSet res = stmt.executeQuery(sql);

            return res.first();
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

    public Category getCategoryById(Integer id) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM CATEGORY WHERE id_category = " + id;

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Category category = new Category();
                category.setIdCategory(res.getInt("id_category"));
                category.setName(res.getString("name"));
                category.setDescription(res.getString("category_description"));

                return category;
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
        //TODO - Conferir tratamento
        return null;
    }
}
