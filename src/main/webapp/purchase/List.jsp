<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Purchase Items</title>
            <link rel="stylesheet" type="text/css" href="/MPJ/jsfcrud.faces" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Purchase Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Purchase Items Found)<br />" rendered="#{purchase.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{purchase.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{purchase.pagingInfo.firstItem + 1}..#{purchase.pagingInfo.lastItem} of #{purchase.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{purchase.prev}" value="Previous #{purchase.pagingInfo.batchSize}" rendered="#{purchase.pagingInfo.firstItem >= purchase.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{purchase.next}" value="Next #{purchase.pagingInfo.batchSize}" rendered="#{purchase.pagingInfo.lastItem + purchase.pagingInfo.batchSize <= purchase.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{purchase.next}" value="Remaining #{purchase.pagingInfo.itemCount - purchase.pagingInfo.lastItem}"
                               rendered="#{purchase.pagingInfo.lastItem < purchase.pagingInfo.itemCount && purchase.pagingInfo.lastItem + purchase.pagingInfo.batchSize > purchase.pagingInfo.itemCount}"/>
                <h:dataTable value="#{purchase.purchaseItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="CreateTime"/>
                        </f:facet>
                        <h:outputText value="#{item.createTime}">
                            <f:convertDateTime pattern="HH:mm:ss" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="MemberId"/>
                        </f:facet>
                        <h:outputText value="#{item.memberId}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="TranId"/>
                        </f:facet>
                        <h:outputText value="#{item.tranId}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Status"/>
                        </f:facet>
                        <h:outputText value="#{item.status}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Amount"/>
                        </f:facet>
                        <h:outputText value="#{item.amount}"/>
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
                        <h:commandLink value="Show" action="#{purchase.detailSetup}">
                            <f:param name="jsfcrud.currentPurchase" value="#{jsfcrud_class['com.tink.mpj.app.bean.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][purchase.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{purchase.editSetup}">
                            <f:param name="jsfcrud.currentPurchase" value="#{jsfcrud_class['com.tink.mpj.app.bean.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][purchase.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{purchase.remove}">
                            <f:param name="jsfcrud.currentPurchase" value="#{jsfcrud_class['com.tink.mpj.app.bean.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][purchase.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{purchase.createSetup}" value="New Purchase"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
