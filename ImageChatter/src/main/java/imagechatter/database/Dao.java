/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.database;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.sql.DriverManager;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author aatukallio
 */
public class Dao {
    
    private final String dbUrl;
    private final String dbUsername = "imageChatterApp";
    private final String dbPassword = "ax756f89C";
    
    public Dao(String url) {
        dbUrl = url;
        initializeDb();
    }
    
    /**
     * return 
     * @return String 
     */
    public String getdbUrl() {
        return this.dbUrl;
    }
    
    public String getdbUsername() {
        return this.dbUsername;
    }
    
    public String getdbPassword() {
        return this.dbPassword;
    }
    
    /**
     * Sets up the database. Table initializations are read from tables.sql
     */
    public void initializeDb() {
        try {
            File file = new File(dbUrl);
            if (!file.exists()) {
                InputStream luontiLauseetStream = Dao.class.getResourceAsStream("tables.sql");
                List<String> fileLines = new BufferedReader(new InputStreamReader(luontiLauseetStream, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
                Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbUrl, this.dbUsername, this.dbPassword);
                Statement s = conn.createStatement();
                for (String command:fileLines) {
                    s.execute(command);
                }
                DbUtils.closeQuietly(s);
                DbUtils.closeQuietly(conn);
            }    
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}