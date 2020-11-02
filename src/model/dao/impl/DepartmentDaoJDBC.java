package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private static final String SQL_FINDBYID = "SELECT * FROM department where id = ?";
    private static final String INSERT = "INSERT INTO department (Name) VALUES (?)";
    private static final String UPDATE = "UPDATE department SET Name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM department WHERE id = ?";
    private static final String SQL_FINDALL = "SELECT * FROM department ORDER BY Name";

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, department.getName());
            var result = st.executeUpdate();
            if (result > 0) {
                var rs = st.getGeneratedKeys();
                if (rs.next()) {
                    department.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Nenhuma linha foi alterada!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(UPDATE);
            st.setString(1, department.getName());
            st.setInt(2, department.getId());
            var result = st.executeUpdate();
            if (result == 0) {
                throw new DbException("Nenhuma linha foi alterada");
            } else {
                System.out.println("Registro Atualizado com sucesso!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(DELETE);
            st.setInt(1, id);
            var result = st.executeUpdate();
            if (result == 0) {
                throw new DbException("Nenhuma linha foi alterada");
            } else {
                System.out.println("Registro excluido com sucesso!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(SQL_FINDBYID);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                var department = new Department(rs.getInt(1), rs.getString(2));
                return department;
            }
            return null;
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Department> findAll() {
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(SQL_FINDALL);
            var departments = new ArrayList<Department>();
            while (rs.next()) {
                var department = new Department(rs.getInt(1), rs.getString(2));
                departments.add(department);
            }
            return departments;
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
