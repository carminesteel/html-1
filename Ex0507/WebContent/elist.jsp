<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ex01.*, org.json.simple.*" %>

<%
	String scode=request.getParameter("scode");
	DAO dao=new DAO();
	JSONObject jObject=dao.elist(scode);
	out.print(jObject);
%>