<%@ include file="../template/headerWithNav.jsp" %>
<style scoped>

</style>
<h:dataTable id="table1" value="#{shoppingCartBean.items}" var="item"
             border="1">
    <f:facet name="header">
        <h:outputText value="Your Shopping Cart"/>
    </f:facet>
    <h:column>
        <f:facet name="header">
            <h:outputText value="Item Description"/>
        </f:facet>
        <h:outputText value="#{item.description}"/>
    </h:column>
    <h:column>
        <f:facet name="header">
            <h:outputText value="Price"/>
        </f:facet>
        <h:outputText value="#{item.price}"/>
    </h:column>
    <f:facet name="footer">
        <h:outputText value="Total: #{shoppingCartBean.total}"/>
    </f:facet>
    <br/>
<%@ include file="../template/footer.jsp" %>