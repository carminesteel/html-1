<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ex01.*, java.util.*, org.json.simple.*" %>

<%
	String pcode=request.getParameter("pcode");
	String pname=request.getParameter("pname");
	String dept=request.getParameter("dept");
	String hiredate=request.getParameter("hiredate");
	String title=request.getParameter("title");
	String strSalary=request.getParameter("salary");
	int salary=Integer.parseInt(strSalary);

	DAO dao=new DAO();
	ArrayList<VO> array=dao.pinsert(pcode, pname, dept, hiredate, title, salary);
	
	JSONArray jArray=new JSONArray();
	for(VO vo:array){
		JSONObject obj=new JSONObject();
		obj.put("pcode", vo.getPcode());
		obj.put("pname", vo.getPname());
		obj.put("dept", vo.getDept());
		obj.put("hiredate", vo.getHiredate());
		obj.put("title", vo.getTitle());
		obj.put("salary", vo.getSalary());
		jArray.add(obj);
	}
	out.print(jArray);
%>