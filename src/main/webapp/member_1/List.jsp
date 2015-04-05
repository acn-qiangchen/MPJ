<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Member Items</title>
            <link rel="stylesheet" type="text/css" href="/MPJ/jsfcrud.faces" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Member Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Member Items Found)<br />" rendered="#{member.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{member.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{member.pagingInfo.firstItem + 1}..#{member.pagingInfo.lastItem} of #{member.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{member.prev}" value="Previous #{member.pagingInfo.batchSize}" rendered="#{member.pagingInfo.firstItem >= member.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{member.next}" value="Next #{member.pagingInfo.batchSize}" rendered="#{member.pagingInfo.lastItem + member.pagingInfo.batchSize <= member.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{member.next}" value="Remaining #{member.pagingInfo.itemCount - member.pagingInfo.lastItem}"
                               rendered="#{member.pagingInfo.lastItem < member.pagingInfo.itemCount && member.pagingInfo.lastItem + member.pagingInfo.batchSize > member.pagingInfo.itemCount}"/>
                <h:dataTable value="#{member.memberItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Name"/>
                        </f:facet>
                        <h:outputText value="#{item.name}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Email"/>
                        </f:facet>
                        <h:outputText value="#{item.email}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Address"/>
                        </f:facet>
                        <h:outputText value="#{item.address}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="ZipCode"/>
                        </f:facet>
                        <h:outputText value="#{item.zipCode}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="CarNo"/>
                        </f:facet>
                        <h:outputText value="#{item.carNo}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{member.detailSetup}">
                            <f:param name="jsfcrud.currentMember" value="#{jsfcrud_class['com.tink.mpj.app.bean.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][member.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{member.editSetup}">
                            <f:param name="jsfcrud.currentMember" value="#{jsfcrud_class['com.tink.mpj.app.bean.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][member.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{member.remove}">
                            <f:param name="jsfcrud.currentMember" value="#{jsfcrud_class['com.tink.mpj.app.bean.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][member.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{member.createSetup}" value="New Member"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
