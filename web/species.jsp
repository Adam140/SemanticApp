<%-- 
    Document   : species
    Created on : 2014-06-11, 21:58:36
    Author     : Adam
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<logic:notEmpty name="fishes">
    <c:import url="pagination.jsp"/>
</logic:notEmpty>
</br>
 <logic:iterate name="fishes" id="fishID">
      <article class="post">
    	<header><h1><bean:write name="fishID" property="fishName"/></h1></header>
        <p>
        	<bean:write name="fishID" property="fishAbstract"/>
        </p>
        <aside>
        	<div><h3></h3></div>
            <div><a href="<bean:write name="fishID" property="fishURL"/>">www</a></div>
            <div><img src='<bean:write name='fishID' property='fishPhoto'/>' alt="post pic"></div>
        </aside>
        <div class="clearfloat"></div>
    </article>
 </logic:iterate>
</br>
<logic:notEmpty name="fishes">
    <c:import url="pagination.jsp"/>
</logic:notEmpty>