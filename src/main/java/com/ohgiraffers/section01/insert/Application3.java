package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static jdk.internal.net.http.common.Utils.close;

public class Application3 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(
                    new FileInputStream(
                            "src/main/java/com/ohgiraffers/mapper/menu-query.xml"
                    )
            );

            String query = prop.getProperty("insertMenu");

            pstmt = con.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.println("메뉴의 이름을 입력하세요 : ");
            String menuName = sc.nextLine();

            System.out.println("메뉴 가격을 입력하세요 : ");
            int menuPrice = sc.nextInt();

            System.out.println("카테고리 코드를 입력하세요 : ");
            int categoryCode = sc.nextInt();

            System.out.println("판매 여부를 입력하세요(Y/N) : ");
            sc.nextLine();
            String orderableStatus = sc.nextLine().toUpperCase();

            MenuDTO newMenu = new MenuDTO();
            newMenu.setMenuName(menuName);
            newMenu.setMenuPrice(menuPrice);
            newMenu.setCategoryCode(categoryCode);
            newMenu.setOrderableStatus(orderableStatus);
            /* -------------------------------- 다른 클래스라고 생각하세요 -----------------------------*/

            pstmt.setString(1, newMenu.getMenuName());
            pstmt.setInt(2, newMenu.getMenuPrice());
            pstmt.setInt(3, newMenu.getCategoryCode());
            pstmt.setString(4, newMenu.gotOrderableStatus());

            result = pstmt.executeUpdate();


        } catch (IOException e) {
            throw new RuntimeException(e);
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