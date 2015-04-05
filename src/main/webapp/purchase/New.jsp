<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Purchase</title>
            <link rel="stylesheet" type="text/css" href="/MPJ/jsfcrud.faces" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>New Purchase</h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{purchase.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputText value="CreateTime (HH:mm:ss):"/>
                <h:inputText id="createTime" value="#{purchase.purchase.createTime}" title="CreateTime" >
                    <f:convertDateTime pattern="HH:mm:ss" />
                </h:inputText>
                <h:outputText value="MemberId:"/>
                <h:inputText id="memberId" value="#{purchase.purchase.memberId}" title="MemberId" />
                <h:outputText value="TranId:"/>
                <h:inputText id="tranId" value="#{purchase.purchase.tranId}" title="TranId" />
                <h:outputText value="Status:"/>
                <h:inputText id="status" value="#{purchase.purchase.status}" title="Status" />
                <h:outputText value="Amount:"/>
                <h:inputText id="amount" value="#{purchase.purchase.amount}" title="Amount" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{purchase.create}" value="Create"/>
            <br />
            <br />
            <h:commandLink action="#{purchase.listSetup}" value="Show All Purchase Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
