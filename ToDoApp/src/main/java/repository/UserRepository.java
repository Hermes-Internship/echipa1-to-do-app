package repository;

import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UserRepository implements UserRepositoryInterface {
    private JdbcUtils dbUtils;

    public UserRepository(Properties props) {
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public void create(User user) {

    }

    @Override
    public User findByUsername(String username) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("select * from users where username = ?")) {
            preStnt.setString(1, username);
            try(ResultSet result = preStnt.executeQuery()) {
                if (result.next()) {
                    String firstName = result.getString("firstName");
                    String lastName = result.getString("lastName");
                    String email = result.getString("email");
                    User user = new User(username, firstName, lastName, email);
                    return user;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return null;
    }
}
