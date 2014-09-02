<%-- 
    Document   : publicationResult
    Created on : 2014-06-12, 15:37:44
    Author     : Adam
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<logic:notEmpty name="publications">
    <c:import url="pagination.jsp"/>
</logic:notEmpty>
    </br>
<logic:iterate name="publications" id="publicationID">
    <article class="post">
        <header><h1><bean:write name="publicationID" property="publicationName"/></h1></header>
        <p>
            <bean:write name="publicationID" property="publicationAbstract" filter="false"/>
        </p><br>
        <p>
            Publication about: <a href="<bean:write name="publicationID" property="subjectSpecies"/>">
                <bean:write name="publicationID" property="subjectSpecies"/></a>
        </p>
        <aside>
            <div><h5><bean:write name="publicationID" property="journalName"/></h5></div>
            <div><a href="<bean:write name="publicationID" property="publicationURL"/>">www</a></div>
            <div><img src='<bean:write name='publicationID' property='publicationPhoto'/>' alt="post pic"></div>
        </aside>
        <div class="clearfloat"></div>
    </article>
</logic:iterate>
    </br>
    <logic:notEmpty name="publications">
    <c:import url="pagination.jsp"/>
</logic:notEmpty>
<logic:empty name="publications">
    </br>
    Nothing Found
    </br>
    Apologies, but nothing matched your search criteria.
</logic:empty>