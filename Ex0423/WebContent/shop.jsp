<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ex01.*" %>

<%
	String query=request.getParameter("query");
	String display=request.getParameter("display");
	String start=request.getParameter("start");
	String result=NaverAPI.search(query, display, start);
	out.print(result);
%>