<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Purchase</title>
            <link rel="stylesheet" type="text/css" href="/MPJ/jsfcrud.faces" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Purchase</h1>
        <h:form>
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
                <h:outputText value="Id:"/>
                <h:outputText value="#{purchase.purchase.id}" title="Id" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{purchase.edit}" value="Save">
                <f:param name="jsfcrud.currentPurchase" value="#{jsfcrud_class['com.tink.mpj.app.bean.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][purchase.purchase][purchase.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{purchase.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentPurchase" value="#{jsfcrud_class['com.tink.mpj.app.bean.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][purchase.purchase][purchase.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{purchase.listSetup}" value="Show All Purchase Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
