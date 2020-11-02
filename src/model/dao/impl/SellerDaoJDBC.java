package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {
    private static final String SQL_FINDBYID = "SELECT seller.*,department.Name as DepName\n" +
            "FROM seller INNER JOIN department\n" +
            "ON seller.DepartmentId = department.Id\n" +
            "WHERE seller.Id = ?";
    private static final String SQL_FINDBYID_DEPARTMENT = "SELECT seller.*,department.Name as DepName\n" +
            "FROM seller INNER JOIN department\n" +
            "ON seller.DepartmentId = department.Id\n" +
            "WHERE DepartmentId = ?\n" +
            "ORDER BY Name";

    private static final String SQL_FINDBYID_ALL = "SELECT seller.*,department.Name as DepName\n" +
            "FROM seller INNER JOIN department\n" +
            "ON seller.DepartmentId = department.Id\n" +
            "ORDER BY Name";
    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(SQL_FINDBYID);
            st.setInt(1,id);
            rs = st.executeQuery();
            return rs.next() ? rsSeller(rs, rsDepartment(rs)) : null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Seller> sellers = new ArrayList<>();
        Map<Integer, Department> map = new HashMap<>();
        try {
            st = conn.prepareStatement(SQL_FINDBYID_DEPARTMENT);
            st.setInt(1, id);
            rs = st.executeQuery();

            while (rs.next()){
                var department = map.get(rs.getInt("DepartmentId"));

                if (department == null) {
                    department = rsDepartment(rs);
                    map.put(department.getId(), department);
                }
                sellers.add(rsSeller(rs, department));
            }
            return sellers;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    private Department rsDepartment(ResultSet rs) throws SQLException {
        return new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
    }
    private Seller rsSeller(ResultSet rs, Department department) throws SQLException{
        return new Seller(rs.getInt("seller.Id"),
                rs.getString("seller.Name"), rs.getString("Email"),
                rs.getDate("BirthDate"),
                rs.getDouble("BaseSalary"),
                department);
    }


    @Override
    public List<Seller> findAll() {
        Statement st = null;
        ResultSet rs = null;
        List<Seller> sellers = new ArrayList<>();
        Map<Integer, Department> map = new HashMap<>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(SQL_FINDBYID_ALL);

            while (rs.next()){
                var department = map.get(rs.getInt("DepartmentId"));

                if (department == null) {
                    department = rsDepartment(rs);
                    map.put(department.getId(), department);
                }
                sellers.add(rsSeller(rs, department));
            }
            return sellers;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
}
