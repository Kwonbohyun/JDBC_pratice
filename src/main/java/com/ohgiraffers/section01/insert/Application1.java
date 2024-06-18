package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static java.sql.DriverManager.getConnection;
import static jdk.internal.net.http.common.Utils.close;

public class Application1 {

    public static void main(String[] args) {


        Connection con = getConnection();

        PreparedStatement pstmt = null;


        int result = 0;

        Properties prop = new Properties();



    {
        try {
            prop.loadFromXML(
                    new FileInputStream(
                            "src/main/java/com/ohgiraffers/mapper/menu-query.xml"
                    )
            );
            String query = prop.getProperty("insertMenu");

            pstmt = con.prepareStatement(query);
            /*

             */


            pstmt.setString(1, "봉골레청국장");
            pstmt.setInt(2, 50000);
            pstmt.setInt(3, 1);
            pstmt.setString(4, "Y");


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("메뉴 저장 성공!!!");
        } else {
            System.out.println("메뉴 저장 실패!!!");
        }


    }


    }


    }