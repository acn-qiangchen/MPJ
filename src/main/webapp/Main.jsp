<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>駐車料金管理システム</title>
<link rel="stylesheet" type="text/css" href="/MPJ/jsfcrud.css" />
        </head>
        <body>
            <h1><h:outputText value="駐車料金管理システム"/></h1>
            <%--
                <h:form>
                    <h:commandLink action="#{ticketAdmin.listSetup}" value="Ticket管理"/>
                    <br/>
                    <h:commandLink action="#{planAdmin.listSetup}" value="料金Plan管理"/>
                    <br/>
                    <h:commandLink action="#{.listSetup}" value="料金Plan管理"/>
                    <br/>
                </h:form>
            --%>
        <a href="/MPJ/member/newMember.faces">10-会員登録</a>
        <br>
        <a href="/MPJ/member/searchMember.faces">11-会員検索</a>
        <br>
        <a href="/MPJ/plan/List.faces">21-Plan管理</a>
        <br>
        <a href="/MPJ/ticket/List.faces">31-Ticket管理</a>
        <br>
        <a href=".">入庫・出庫</a>
        </body>
    </html>
</f:view>
