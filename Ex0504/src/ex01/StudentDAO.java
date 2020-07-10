package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentDAO {
	//�����ͺ��̽� ���� method
	public Connection con() throws Exception{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "haksa";
		String password = "pass";
		  
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		  
		return con;
	}
	
	//�л��� ������� ��� method
	public ArrayList<StudentVO> slist() throws Exception{
		ArrayList<StudentVO> sarray=new ArrayList<StudentVO>();
		String sql="select scode, sname, dept, avg(grade) s from view_view group by scode, sname, dept order by dept";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			StudentVO vo=new StudentVO();
			vo.setScode(rs.getString("scode"));
			vo.setSname(rs.getString("sname"));
			vo.setDept(rs.getString("dept"));
			vo.setAvg1(rs.getDouble("s"));
			sarray.add(vo);
		}
		con().close();
		return sarray;
	}
}
