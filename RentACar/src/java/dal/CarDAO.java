/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class CarDAO {
    
    public ResultSet getAvailableCarsEachCategory(String categoryUID, int carCount) throws SQLException {
        String query = """
                           SELECT TOP(?) [car_number_plate]
                                    ,[status]
                                    ,b.[category_uid]
                           FROM categories a, cars b
                           WHERE a.category_uid = b.category_uid AND b.status = 1
                           AND a.category_uid = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);
        
        pstmt.setInt(1, carCount);
        pstmt.setString(2, categoryUID);
        
        return executeQuery(pstmt);
    }

    public ResultSet getAvailableCarsEachCategory(String categoryUID) throws SQLException {
        String query = """
                           SELECT   [car_number_plate]
                                    ,[status]
                                    ,b.[category_uid]
                           FROM categories a, cars b
                           WHERE a.category_uid = b.category_uid AND b.status = 1
                           AND a.category_uid = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);
        
        pstmt.setString(1, categoryUID);
        
        return executeQuery(pstmt);
    }

    private static CarDAO instance;

    public static CarDAO getInstance() {
        if (instance == null) {
            instance = new CarDAO();
        }
        return instance;
    }

    /**
     * Create instance for PreparedStatement class
     *
     * @param query the SQL query to be prepared.
     *
     * @return a PreparedStatement object that can be used to execute the query
     * with specified parameters.
     *
     * @throws SQLException if a database access error occurs or the SQL
     * statement cannot be pre-compiled.
     */
    public PreparedStatement createPreparedStatement(String query) throws SQLException {
        Connection conn = DBContext.getInstance().getConnection();
        return conn.prepareStatement(query);
    }

    /**
     * Executes a SQL query using the provided PreparedStatement.
     *
     * @param pstmt the PreparedStatement to be executed.
     * @return the ResultSet obtained from executing the query.
     * @throws java.sql.SQLException
     */
    public ResultSet executeQuery(PreparedStatement pstmt) throws SQLException {
        return pstmt.executeQuery();
    }

    public CarDAO() {
    }

    public ResultSet getCarsEachOrder(String orderUID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
