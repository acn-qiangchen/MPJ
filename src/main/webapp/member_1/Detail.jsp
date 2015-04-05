<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Member Detail</title>
            <link rel="stylesheet" type="text/css" href="/MPJ/jsfcrud.faces" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Member Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="SeqNo:"/>
                <h:outputText value="#{member.member.seqNo}" title="SeqNo" />
                <h:outputText value="Name:"/>
                <h:outputText value="#{member.member.name}" title="Name" />
                <h:outputText value="Email:"/>
                <h:outputText value="#{member.member.email}" title="Email" />
                <h:outputText value="Address:"/>
                <h:outputText value="#{member.member.address}" title="Address" />
                <h:outputText value="ZipCode:"/>
                <h:outputText value="#{member.member.zipCode}" title="ZipCode" />
                <h:outputText value="CarNo:"/>
                <h:outputText value="#{member.member.carNo}" title="CarNo" />


            </h:panelGrid>
            <br />
            <h:commandLink action="#{member.remove}" value="Destroy">
                <f:param name="jsfcrud.currentMember" value="#{jsfcrud_class['com.tink.mpj.app.bean.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][member.member][member.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{member.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentMember" value="#{jsfcrud_class['com.tink.mpj.app.bean.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][member.member][member.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{member.createSetup}" value="New Member" />
            <br />
            <h:commandLink action="#{member.listSetup}" value="Show All Member Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
