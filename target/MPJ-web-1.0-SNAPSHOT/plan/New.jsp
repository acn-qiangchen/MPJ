<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Plan</title>
            <link rel="stylesheet" type="text/css" href="/MPJ/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>New Plan</h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{planAdmin.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputText value="PlanId:"/>
                <h:inputText id="planId" value="#{planAdmin.plan.planId}" title="PlanId" />
                <h:outputText value="Kbn:"/>
                <h:inputText id="kbn" value="#{planAdmin.plan.kbn}" title="Kbn" />
                <h:outputText value="UnitKbn:"/>
                <h:inputText id="unitKbn" value="#{planAdmin.plan.unitKbn}" title="UnitKbn" />
                <h:outputText value="UnitValue:"/>
                <h:inputText id="unitValue" value="#{planAdmin.plan.unitValue}" title="UnitValue" />
                <h:outputText value="TotalValue:"/>
                <h:inputText id="totalValue" value="#{planAdmin.plan.totalValue}" title="TotalValue" />
                <h:outputText value="Duration:"/>
                <h:inputText id="duration" value="#{planAdmin.plan.duration}" title="Duration" />
                <h:outputText value="EffFrom:"/>
                <h:inputText id="effFrom" value="#{planAdmin.plan.effFrom}" title="EffFrom" />
                <h:outputText value="EffTo:"/>
                <h:inputText id="effTo" value="#{planAdmin.plan.effTo}" title="EffTo" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{planAdmin.create}" value="Create"/>
            <br />
            <br />
            <h:commandLink action="#{planAdmin.listSetup}" value="Show All Plan Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
