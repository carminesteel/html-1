<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ex01.*, org.json.simple.*" %>

<%
	String scode=request.getParameter("scode");
	String sname=request.getParameter("sname");
	String dept=request.getParameter("dept");
	
	SDAO sdao=new SDAO();
	SVO vo=new SVO();
	
	vo.setScode(scode);
	vo.setSname(sname);
	vo.setDept(dept);
	
	JSONObject jObject=sdao.sinsert(vo);
	out.print(jObject);
%>