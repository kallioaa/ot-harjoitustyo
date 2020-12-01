/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.database;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.sql.DriverManager;
import java.util.List;

/**
 *
 * @author aatukallio
 */
public class Dao {
    private String url;
    private boolean testMode;
    private Connection connection;
    
    public Dao(String url, Boolean testMode) {
        
        this.testMode = testMode;
        this.url = url;
        this.initializeDb();
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + url);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }   

    public Connection getConnection() {
        return this.connection;
    }


    private void initializeDb() {
        try {
            File file = new File(url);
            if (!file.exists() | this.testMode) {
                Path path = new File("./resourcesDb/tables.sql").toPath();
                List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
                Connection db = DriverManager.getConnection("jdbc:sqlite:" + url);
                Statement s = db.createStatement();
                for (String command:fileLines) {
                    s.execute(command);
                }
            }    
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
