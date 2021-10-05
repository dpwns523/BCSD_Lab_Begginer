package JDBC_CRUD;
import java.sql.*;

public class Main {
    private static String className = "com.mysql.jdbc.Driver";
    private static String dbUrl = "jdbc:mysql://localhost:3306/mysql";
    private static String dbUser = "root";
    private static String DbPassword = "";  // password
    public static void main(String[] args) throws SQLException {
        System.out.println("하이~~");
        Connection con = null;
        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.연결
        try {
            con = DriverManager.getConnection(dbUrl, dbUser, DbPassword);
            System.out.println("정상적으로 연결되었습니다.");
        } catch(SQLException e) {
            System.err.println("connect 오류:" + e.getMessage());
            e.printStackTrace();
        }
        // CRUD 연습

        String query = "select * from student ";
        // DB에 쿼리 전달을 위한 객체
        PreparedStatement ps = con.prepareStatement(query);
        // 첫번째 ?에 인자 전달
        //ps.setString(1, "y");
        // 쿼리 실행 및 결과 얻기
        ResultSet rs = ps.executeQuery();

        // read
        System.out.println("-----------Read-----------");
        while(rs.next()){
            String id = rs.getString("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");

            System.out.println("id: "+id +"\nName: "+name+"\nGender: "+gender);
        }

        // insert 구문
        query = "insert into student (id, name, gender) values (10, 'bigman', 'x')";
        ps = con.prepareStatement(query);   // 쿼리를 실행할 준비
        ps.executeUpdate();     // 쿼리 실행 및 업데이트

        // read
        query = "select * from student";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        System.out.println("-----------insert-----------");
        while(rs.next()){
            String id = rs.getString("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");

            System.out.println("id: "+id +"\nName: "+name+"\nGender: "+gender);
        }
        // update
        query = "update student set name ='canyon' where name='canon'";
        ps = con.prepareStatement(query);
        ps.executeUpdate();

        // read
        query = "select * from student";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        System.out.println("-----------update-----------");
        while(rs.next()){
            String id = rs.getString("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");

            System.out.println("id: "+id +"\nName: "+name+"\nGender: "+gender);
        }
        // delete
        query = "delete from student where name = 'bigman'";
        ps = con.prepareStatement(query);
        ps.executeUpdate();

        // read
        query = "select * from student";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        System.out.println("-----------delete-----------");
        while(rs.next()){
            String id = rs.getString("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");

            System.out.println("id: "+id +"\nName: "+name+"\nGender: "+gender);
        }
        // 3.해제
        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {}
    }

}
