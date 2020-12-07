/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.database;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.sql.DriverManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author aatukallio
 */
public class Dao {
    
    private static Dao dao_instance;
    protected Connection conn;
    private String url;
    
    private Dao() {
        this.url = "resourcesDb/kkDatabase.db";
        this.initializeDb();
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + url);
        } catch (SQLException e) {
            System.out.println(e);;
        }
    }
    
    public static Dao getInstance() {
        if (dao_instance == null) {
            dao_instance = new Dao();
        }
        return dao_instance;
    }   

    private void initializeDb() {
        try {
            File file = new File(url);
            if (!file.exists()) {
                InputStream luontiLauseetStream = Dao.class.getResourceAsStream("tables.sql");
                List<String> fileLines = new BufferedReader(new InputStreamReader(luontiLauseetStream, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
                Connection db = DriverManager.getConnection("jdbc:sqlite:" + url);
                Statement s = db.createStatement();
                for (String command:fileLines) {
                    s.execute(command);
                }
            }    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
