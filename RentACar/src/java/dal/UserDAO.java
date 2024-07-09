/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import model.User;

/**
 *
 * @author admin
 */
public class UserDAO {
    
    public ResultSet getUserByUserUID(String userUID) throws SQLException {
        String query = """
                       SELECT [users_information].[user_uid], [full_name], [phone], [email], [address]
                       FROM [Rent_A_Car].[dbo].[users_auth], [Rent_A_Car].[dbo].[users_information]
                       WHERE [users_auth].[user_uid] = [users_information].[user_uid]
                       AND [users_auth].[user_uid] = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, userUID);

        return executeQuery(pstmt);
    }

    public void editUserByUserUID(User user) {

        String query = """
                       UPDATE [Rent_A_Car].[dbo].[users_information]
                       SET [full_name] = ?, [address] = ?
                       WHERE [user_uid] = ?
                       """;
        try {
            PreparedStatement pstmt = createPreparedStatement(query);
            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getAddress());
            pstmt.setString(3, user.getUserUID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }

        query = """
                       UPDATE [Rent_A_Car].[dbo].[users_auth]
                       SET [phone] = ?, [email] = ?
                       WHERE [user_uid] = ?
                       """;
        try {
            PreparedStatement pstmt = createPreparedStatement(query);
            pstmt.setString(1, user.getPhone());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getUserUID());
            pstmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    public ResultSet getUsersCountWithName(String name) throws SQLException {
        String full_name = "%" + name + "%";
        String query = """
                       SELECT count(*) FROM [Rent_A_Car].[dbo].[users_information]
                       WHERE full_name LIKE ?
                       """;

        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, full_name);

        return executeQuery(pstmt);
    }

    public ResultSet pagingUsersWithName(int index, String name) throws SQLException {
        int usersCountPerPage = 7;
        String full_name = "%" + name + "%";

        String query = """
                       SELECT [users_information].[user_uid], [full_name], [phone], [email], [address]
                       	FROM [Rent_A_Car].[dbo].[users_auth], [Rent_A_Car].[dbo].[users_information]
                       	WHERE [users_auth].[user_uid] = [users_information].[user_uid] AND [full_name] LIKE ?
                       	ORDER BY [users_information].[user_uid]
                       	OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                       """;

        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, full_name);
        pstmt.setInt(2, (index - 1) * usersCountPerPage);
        pstmt.setInt(3, usersCountPerPage);

        return executeQuery(pstmt);
    }

    public ResultSet getUsersCount() throws SQLException {
        String query = """
                       SELECT count(*) FROM [Rent_A_Car].[dbo].[users_auth]
                       """;

        PreparedStatement pstmt = createPreparedStatement(query);

        return executeQuery(pstmt);
    }

    public ResultSet pagingUsers(int index) throws SQLException {
        int usersCountPerPage = 7;

        String query = """
                       SELECT [users_information].[user_uid], [full_name], [phone], [email], [address]
                       	FROM [Rent_A_Car].[dbo].[users_auth], [Rent_A_Car].[dbo].[users_information]
                       	WHERE [users_auth].[user_uid] = [users_information].[user_uid]
                       	ORDER BY [users_information].[user_uid]
                       	OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setInt(1, (index - 1) * usersCountPerPage);
        pstmt.setInt(2, usersCountPerPage);

        return executeQuery(pstmt);
    }

    /**
     * Inserts a new user into the database.
     *
     * @param user The User object to insert.
     */
    public void insertUserToDatabases(User user) throws SQLException {
        String query = """
                       INSERT INTO [Rent_A_Car].[dbo].[users_information]
                       ([user_uid], [full_name], [address])
                       VALUES (?,?,?)
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);
        pstmt.setString(1, user.getUserUID());
        pstmt.setString(2, user.getFullName());
        pstmt.setString(3, user.getAddress());
        pstmt.executeUpdate();

        query = """
                       INSERT INTO [Rent_A_Car].[dbo].[users_auth]
                       ([user_uid], [phone], [email])
                       VALUES (?,?,?)
                       """;
        pstmt = createPreparedStatement(query);
        pstmt.setString(1, user.getUserUID());
        pstmt.setString(2, user.getPhone());
        pstmt.setString(3, user.getEmail());
        pstmt.executeUpdate();

    }

    /**
     * Deletes a user from the database by userUID.
     *
     * @param userUID The userUID of the user to delete.
     */
    public void deleteUserByUserUID(String userUID) throws SQLException {
        String query = "DELETE FROM [Rent_A_Car].[dbo].[users_auth] WHERE [user_uid] = ?";

        PreparedStatement pstmt = createPreparedStatement(query);
        pstmt.setString(1, userUID);
        pstmt.executeUpdate();

        query = "DELETE FROM [Rent_A_Car].[dbo].[users_information] WHERE [user_uid] = ?";
        pstmt = createPreparedStatement(query);
        pstmt.setString(1, userUID);
        pstmt.executeUpdate();
    }

    private static UserDAO instance;

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
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

    public UserDAO() {
    }
}
