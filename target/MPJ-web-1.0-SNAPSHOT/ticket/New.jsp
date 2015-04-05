<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Ticket</title>
            <link rel="stylesheet" type="text/css" href="/MPJ/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>New Ticket</h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{ticketAdmin.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputText value="CarNo:"/>
                <h:inputText id="carNo" value="#{ticketAdmin.ticket.carNo}" title="CarNo" />
                <h:outputText value="MemberId:"/>
                <h:inputText id="memberId" value="#{ticketAdmin.ticket.memberId}" title="MemberId" />
                <h:outputText value="PlanId:"/>
                <h:inputText id="planId" value="#{ticketAdmin.ticket.planId}" title="PlanId" />
                <h:outputText value="InTime (yyyyMMddhhmmss):"/>
                <h:inputText id="inTime" value="#{ticketAdmin.ticket.inTime}" title="InTime" >
                    <f:convertDateTime pattern="yyyyMMddhhmmss" />
                </h:inputText>
                <h:outputText value="OutTime (yyyyMMddhhmmss):"/>
                <h:inputText id="outTime" value="#{ticketAdmin.ticket.outTime}" title="OutTime" >
                    <f:convertDateTime pattern="yyyyMMddhhmmss" />
                </h:inputText>
                <h:outputText value="PurAmount:"/>
                <h:inputText id="purAmount" value="#{ticketAdmin.ticket.purAmount}" title="PurAmount" />
                <h:outputText value="Fee:"/>
                <h:inputText id="fee" value="#{ticketAdmin.ticket.fee}" title="Fee" />
                <h:outputText value="Location:"/>
                <h:inputText id="location" value="#{ticketAdmin.ticket.location}" title="Location" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{ticketAdmin.create}" value="Create"/>
            <br />
            <br />
            <h:commandLink action="#{ticketAdmin.listSetup}" value="Show All Ticket Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
