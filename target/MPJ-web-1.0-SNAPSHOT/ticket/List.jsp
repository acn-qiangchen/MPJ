<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Ticket Items</title>
            <link rel="stylesheet" type="text/css" href="/MPJ/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Ticket Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Ticket Items Found)<br />" rendered="#{ticketAdmin.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{ticketAdmin.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{ticketAdmin.pagingInfo.firstItem + 1}..#{ticketAdmin.pagingInfo.lastItem} of #{ticketAdmin.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{ticketAdmin.prev}" value="Previous #{ticketAdmin.pagingInfo.batchSize}" rendered="#{ticketAdmin.pagingInfo.firstItem >= ticket.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{ticketAdmin.next}" value="Next #{ticketAdmin.pagingInfo.batchSize}" rendered="#{ticketAdmin.pagingInfo.lastItem + ticket.pagingInfo.batchSize <= ticket.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{ticketAdmin.next}" value="Remaining #{ticketAdmin.pagingInfo.itemCount - ticket.pagingInfo.lastItem}"
                               rendered="#{ticketAdmin.pagingInfo.lastItem < ticket.pagingInfo.itemCount && ticket.pagingInfo.lastItem + ticket.pagingInfo.batchSize > ticket.pagingInfo.itemCount}"/>
                <h:dataTable value="#{ticketAdmin.ticketItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="CarNo"/>
                        </f:facet>
                        <h:outputText value="#{item.carNo}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="MemberId"/>
                        </f:facet>
                        <h:outputText value="#{item.memberId}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="PlanId"/>
                        </f:facet>
                        <h:outputText value="#{item.planId}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="InTime"/>
                        </f:facet>
                        <h:outputText value="#{item.inTime}">
                            <f:convertDateTime pattern="yyyy/MM/dd hh:mm:ss" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="OutTime"/>
                        </f:facet>
                        <h:outputText value="#{item.outTime}">
                            <f:convertDateTime pattern="yyyy/MM/dd hh:mm:ss" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="PurAmount"/>
                        </f:facet>
                        <h:outputText value="#{item.purAmount}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Fee"/>
                        </f:facet>
                        <h:outputText value="#{item.fee}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Location"/>
                        </f:facet>
                        <h:outputText value="#{item.location}"/>
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
                        <h:commandLink value="Show" action="#{ticketAdmin.detailSetup}">
                            <f:param name="jsfcrud.currentTicket" value="#{item.id}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{ticketAdmin.editSetup}">
                            <f:param name="jsfcrud.currentTicket" value="#{item.id}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{ticketAdmin.remove}">
                            <f:param name="jsfcrud.currentTicket" value="#{item.id}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{ticketAdmin.createSetup}" value="New Ticket"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
