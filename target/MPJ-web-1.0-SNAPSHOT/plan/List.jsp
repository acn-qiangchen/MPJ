<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Plan Items</title>
            <link rel="stylesheet" type="text/css" href="/MPJ/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Plan Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Plan Items Found)<br />" rendered="#{planAdmin.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{planAdmin.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{planAdmin.pagingInfo.firstItem + 1}..#{planAdmin.pagingInfo.lastItem} of #{planAdmin.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{planAdmin.prev}" value="Previous #{planAdmin.pagingInfo.batchSize}" rendered="#{planAdmin.pagingInfo.firstItem >= plan.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{planAdmin.next}" value="Next #{planAdmin.pagingInfo.batchSize}" rendered="#{planAdmin.pagingInfo.lastItem + plan.pagingInfo.batchSize <= plan.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{planAdmin.next}" value="Remaining #{planAdmin.pagingInfo.itemCount - plan.pagingInfo.lastItem}"
                               rendered="#{planAdmin.pagingInfo.lastItem < plan.pagingInfo.itemCount && plan.pagingInfo.lastItem + plan.pagingInfo.batchSize > plan.pagingInfo.itemCount}"/>
                <h:dataTable value="#{planAdmin.planItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="PlanId"/>
                        </f:facet>
                        <h:outputText value="#{item.planId}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Kbn"/>
                        </f:facet>
                        <h:outputText value="#{item.kbn}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="UnitKbn"/>
                        </f:facet>
                        <h:outputText value="#{item.unitKbn}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="UnitValue"/>
                        </f:facet>
                        <h:outputText value="#{item.unitValue}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="TotalValue"/>
                        </f:facet>
                        <h:outputText value="#{item.totalValue}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Duration"/>
                        </f:facet>
                        <h:outputText value="#{item.duration}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="EffFrom"/>
                        </f:facet>
                        <h:outputText value="#{item.effFrom}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="EffTo"/>
                        </f:facet>
                        <h:outputText value="#{item.effTo}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Id"/>
                        </f:facet>
                        <h:outputText value="#{item.id}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{planAdmin.detailSetup}">
                            <f:param name="jsfcrud.currentPlan" value="#{item.id}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{planAdmin.editSetup}">
                            <f:param name="jsfcrud.currentPlan" value="#{item.id}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{planAdmin.remove}">
                            <f:param name="jsfcrud.currentPlan" value="#{item.id}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{planAdmin.createSetup}" value="New Plan"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
