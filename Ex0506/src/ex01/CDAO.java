package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CDAO {
	//데이터베이스 연결 method
	public Connection con() throws Exception{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "haksa";
		String password = "pass";
		  
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		  
		return con;
	}
	
	//강좌별 평균출력 method
	public ArrayList<CVO> slist() throws Exception{
		ArrayList<CVO> array=new ArrayList<CVO>();
		String sql="select scode, sname, dept, pname from view_prostu group by scode, sname, dept, pname";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			CVO vo=new CVO();
			vo.setScode(rs.getString("scode"));
			vo.setSname(rs.getString("sname"));
			vo.setDept(rs.getString("dept"));
			vo.setPname(rs.getString("pname"));
			array.add(vo);
		}
		con().close();
		return array;
	}
	
	//학생별 평균출력 method
	public ArrayList<CVO> clist(String scode) throws Exception{
		ArrayList<CVO> array=new ArrayList<CVO>();
		String sql="select lcode, lname, grade from view_couenr where scode=? group by lcode, lname, grade";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, scode);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			CVO vo=new CVO();
			vo.setLcode(rs.getString("lcode"));
			vo.setLname(rs.getString("lname"));
			vo.setGrade(rs.getInt("grade"));
			array.add(vo);
		}
		con().close();
		return array;
	}
	
	//학생이 신청한 수강신청 과목수 출력 method
	public int count(String scode) throws Exception{
		int count=0;
		String sql="select count(*) from view_couenr where scode=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, scode);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			count=rs.getInt("count(*)");
		}
		con().close();
		return count;
	}
	
	//학생이 신청한 강좌들의 평균점수 method
	public double avg(String scode) throws Exception{
		double avg=0;
		String sql="select avg(grade) from view_couenr where scode=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, scode);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			avg=rs.getDouble("avg(grade)");
		}
		con().close();
		return avg;
	}
}
