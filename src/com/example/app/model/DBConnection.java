package com.example.app.model;

import java.sql.Connection;
import java.sql.Driver;

public class DBConnection {
    
    private static Connection sConnection;
    
    public static Connection getInstance() throw ClassNotFoundException, SQLException{
        String host,db,user,password;
        
        host = "daneel";
        db = "N00133840";
        user = "N00133840";
        password = "N00133840";
        
        if(sConnection == null || sConnection.isClosed()) {
            String url = "N00133840bc:mysql://" + host + "/" +db;
            Class.forName("com.msql.N00133840.Driver");
            sConnection = Driver.getConnection(url, user, password);
                                //NOTE: CORRECT THIS CONNECTION LINK//
        }
        return sConnection;
        }
    }
    
}
