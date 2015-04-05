<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Plan Detail</title>
            <link rel="stylesheet" type="text/css" href="/MPJ/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Plan Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="PlanId:"/>
                <h:outputText value="#{planAdmin.plan.planId}" title="PlanId" />
                <h:outputText value="Kbn:"/>
                <h:outputText value="#{planAdmin.plan.kbn}" title="Kbn" />
                <h:outputText value="UnitKbn:"/>
                <h:outputText value="#{planAdmin.plan.unitKbn}" title="UnitKbn" />
                <h:outputText value="UnitValue:"/>
                <h:outputText value="#{planAdmin.plan.unitValue}" title="UnitValue" />
                <h:outputText value="TotalValue:"/>
                <h:outputText value="#{planAdmin.plan.totalValue}" title="TotalValue" />
                <h:outputText value="Duration:"/>
                <h:outputText value="#{planAdmin.plan.duration}" title="Duration" />
                <h:outputText value="EffFrom:"/>
                <h:outputText value="#{planAdmin.plan.effFrom}" title="EffFrom" />
                <h:outputText value="EffTo:"/>
                <h:outputText value="#{planAdmin.plan.effTo}" title="EffTo" />
                <h:outputText value="Id:"/>
                <h:outputText value="#{planAdmin.plan.id}" title="Id" />


            </h:panelGrid>
            <br />
            <h:commandLink action="#{planAdmin.remove}" value="Destroy">
                <f:param name="jsfcrud.currentPlan" value="#{planAdmin.plan.id}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{planAdmin.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentPlan" value="#{planAdmin.plan.id}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{planAdmin.createSetup}" value="New Plan" />
            <br />
            <h:commandLink action="#{planAdmin.listSetup}" value="Show All Plan Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
