package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
    private static final String SQL_FINDBYID = "SELECT seller.*,department.Name as DepName\n" +
            "FROM seller INNER JOIN department\n" +
            "ON seller.DepartmentId = department.Id\n" +
            "WHERE seller.Id = ?";
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
            return rs.next() ? rsSeller(rs) : null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    private Department rsDepartment(ResultSet rs) throws SQLException {
        return new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
    }
    private Seller rsSeller(ResultSet rs) throws SQLException{
        return new Seller(rs.getInt("seller.Id"),
                rs.getString("seller.Name"), rs.getString("Email"),
                rs.getDate("BirthDate"),
                rs.getDouble("BaseSalary"),
                rsDepartment(rs));
    }


    @Override
    public List<Seller> findAll() {
        return null;
    }
}
