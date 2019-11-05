package com.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;

import java.beans.PropertyVetoException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class KuduPool implements Serializable {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(KuduPool.class);
    private static KuduPool pool;
    private static ComboPooledDataSource ds_kudu;

    private KuduPool() {
        ds_kudu = new ComboPooledDataSource();
        try {
            ds_kudu.setDriverClass("org.apache.hive.jdbc.HiveDriver");
            ds_kudu.setJdbcUrl("jdbc:hive2://172.26.137.230:21050/lyua2;auth=noSasl");
            ds_kudu.setUser("root");
            ds_kudu.setPassword("root");
            ds_kudu.setInitialPoolSize(8);
            ds_kudu.setMinPoolSize(8);
            ds_kudu.setMaxPoolSize(20);
            ds_kudu.setAcquireIncrement(5);
            ds_kudu.setMaxStatements(0);
            ds_kudu.setMaxIdleTime(3600);
            ds_kudu.setIdleConnectionTestPeriod(120);
        } catch (PropertyVetoException e) {
            logger.error(e.getMessage());
        }

    }

    public final static KuduPool getInstance() {

        synchronized(KuduPool.class) {
            try {
                if (pool == null) {
                    pool = new KuduPool();
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return pool;
    }

    public synchronized final Connection getConnection() {
        Connection conn = null;
        try {
            conn = ds_kudu.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return conn;
    }

    public static void release(Connection conn, PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    public static void releaseStat(Connection conn, Statement stm) {
        if (stm != null){
            try{
                stm.close();
            }catch(Exception e){
                logger.error(e.getMessage());
            }
        }

        if (conn != null){
            try{
                conn.close();
            }catch(Exception e){
                logger.error(e.getMessage());
            }
        }
    }
}