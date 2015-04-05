<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Ticket Detail</title>
            <link rel="stylesheet" type="text/css" href="/MPJ/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Ticket Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="CarNo:"/>
                <h:outputText value="#{ticketAdmin.ticket.carNo}" title="CarNo" />
                <h:outputText value="MemberId:"/>
                <h:outputText value="#{ticketAdmin.ticket.memberId}" title="MemberId" />
                <h:outputText value="PlanId:"/>
                <h:outputText value="#{ticketAdmin.ticket.planId}" title="PlanId" />
                <h:outputText value="InTime:"/>
                <h:outputText value="#{ticketAdmin.ticket.inTime}" title="InTime" >
                    <f:convertDateTime pattern="yyyy/MM/dd hh:mm:ss" />
                </h:outputText>
                <h:outputText value="OutTime:"/>
                <h:outputText value="#{ticketAdmin.ticket.outTime}" title="OutTime" >
                    <f:convertDateTime pattern="yyyy/MM/dd hh:mm:ss" />
                </h:outputText>
                <h:outputText value="PurAmount:" />
                <h:outputText value="#{ticketAdmin.ticket.purAmount}" title="PurAmount" >
                                      <f:convertNumber currencySymbol="¥" groupingUsed="true" maxFractionDigits="0" type="currency" />
                </h:outputText>
                <h:outputText value="Fee:"/>
                <h:outputText value="#{ticketAdmin.ticket.fee}" title="Fee" />
                <h:outputText value="Location:"/>
                <h:outputText value="#{ticketAdmin.ticket.location}" title="Location" />
                <h:outputText value="Id:"/>
                <h:outputText value="#{ticketAdmin.ticket.id}" title="Id" />


            </h:panelGrid>
            <br />
            <h:commandLink action="#{ticketAdmin.remove}" value="Destroy">
                <f:param name="jsfcrud.currentTicket" value="#{ticketAdmin.ticket.id}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{ticketAdmin.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentTicket" value="#{ticketAdmin.ticket.id}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{ticketAdmin.createSetup}" value="New Ticket" />
            <br />
            <h:commandLink action="#{ticketAdmin.listSetup}" value="Show All Ticket Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
