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

    public ResultSet getUsersCountWithName(String name) throws SQLException {

        String full_name = "%" + name + "%";

        String query = """
                       SELECT count(*) FROM [Rent_A_Car].[dbo].[users]
                       WHERE full_name LIKE ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);
        pstmt.setString(1, full_name);
        return executeQuery(pstmt);
    }

    public ResultSet pagingUsersWithNameResultSet(int index, String name) throws SQLException {

        int usersCountPerPage = 7;
        String full_name = "%" + name + "%";

        String query = """
                       SELECT * FROM [Rent_A_Car].[dbo].[users]
                       WHERE full_name LIKE ?
                       ORDER BY user_uid
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
                       SELECT count(*) FROM [Rent_A_Car].[dbo].[users]
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);
        return executeQuery(pstmt);
    }

    public ResultSet pagingUsersResultSet(int index) throws SQLException {

        int usersCountPerPage = 7;

        String query = """
                       SELECT * FROM [Rent_A_Car].[dbo].[users]
                       ORDER BY user_uid
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
    public void insertUser(User user) {
        String query = """
                       INSERT INTO [Rent_A_Car].[dbo].[users_information]
                       ([user_uid], [full_name], [address])
                       VALUES (?,?,?)
                       """;
        try (PreparedStatement pstmt = createPreparedStatement(query)) {
            pstmt.setString(1, user.getUserUID());
            pstmt.setString(2, user.getFullName());
            pstmt.setString(3, user.getAddress());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        
        query = """
                       INSERT INTO [Rent_A_Car].[dbo].[users_auth]
                       ([user_uid], [phone], [email])
                       VALUES (?,?,?)
                       """;
        try (PreparedStatement pstmt = createPreparedStatement(query)) {
            pstmt.setString(1, user.getUserUID());
            pstmt.setString(2, user.getPhone());
            pstmt.setString(3, user.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Deletes a user from the database by userUID.
     *
     * @param userUID The userUID of the user to delete.
     */
    public void deleteUserByUserUID(String userUID) {
        String query = "DELETE FROM [Rent_A_Car].[dbo].[users] WHERE [user_uid] = ?";

        try (PreparedStatement pstmt = createPreparedStatement(query)) {
            pstmt.setString(1, userUID);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Retrieves all user set from the database.
     *
     * @return The all user object retrieved from the database except password
     * attribute, or null if there aren't any users.
     */
    public ResultSet getAllUsersExceptPassword() throws SQLException {
        String query = """
                       SELECT [user_uid]
                             ,[username]
                             ,[email]
                         FROM [Rent_A_Car].[dbo].[users]""";
        PreparedStatement pstmt = createPreparedStatement(query);
        return executeQuery(pstmt);
    }

    /**
     * Retrieves a user set from the database by username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object retrieved from the database, or null if not
     * found.
     */
    public ResultSet getUserByUsername(String username) throws SQLException {
        String query = "Select * from user-auth";
        PreparedStatement pstmt = createPreparedStatement(query);
        return executeQuery(pstmt);

    }

    /**
     * Retrieves a user set from the database by email.
     *
     * @param email The email of the user to retrieve.
     * @return The User object retrieved from the database, or null if not
     * found.
     */
    public ResultSet getUserByEmail(String email) {
        String query = "SELECT * FROM [dbo].[user-auth] WHERE "
                + "[email] = ? ";
        try (PreparedStatement pstmt = createPreparedStatement(query)) {
            pstmt.setString(1, email);
            return executeQuery(pstmt);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    /**
     * Checks if a username exist in the database
     *
     * @param username The username to check for existence
     * @return true if the username exist, false otherwise.j
     */
    public boolean isExistUserName(String username) {
        boolean flag = true;
        try {
            ResultSet rs = this.getUserByUsername(username);
            if (rs.next()) {  // Mean have duplicate username
                return flag;
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        flag = false;
        return flag;
    }

    /**
     * Checks if an email exists in the database.
     *
     * @param email The email address to check for existence.
     * @return true if the email exists, false otherwise.
     */
    public boolean isExistEmail(String email) {
        boolean flag = true;
        try {
            ResultSet rs = this.getUserByEmail(email);
            if (rs.next()) {  // Mean have duplicate email
                return flag;
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        flag = false;
        return flag;
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

    public void editUserByUserUID(User user) {
        String query = """
                         UPDATE [Rent_A_Car].[dbo].[users]
                         SET [full_name] = ?, [phone] = ?, [email] = ?, [address] = ?
                         WHERE [user_uid] = ?
                       """;
        try (PreparedStatement pstmt = createPreparedStatement(query)) {

            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getPhone());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getUserUID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }

    }
}
