<%-- 
    Document   : menu
    Created on : 2014-06-11, 17:59:09
    Author     : Adam
--%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<header>
    <div class="logo"><h1><a href=""></a></h1></div>
    <nav>
        <ul id="navlist">
            <li id="<logic:equal name="currentPage" scope="session" value="Home">active</logic:equal>"><html:link action="MainPage.do?nextPage=Home">Home</html:link></li>
            <li id="<logic:equal name="currentPage" scope="session" value="Species">active</logic:equal>"><html:link action="MainPage.do?nextPage=Species">Species</html:link></li>
            <li id="<logic:equal name="currentPage" scope="session" value="Publication">active</logic:equal>"><html:link action="MainPage.do?nextPage=Publication">Publication</html:link></li>
            <li id="<logic:equal name="currentPage" scope="session" value="About">active</logic:equal>"><html:link action="MainPage.do?nextPage=About">About</html:link></li>
        </ul>
    </nav>
    <div class="clearfloat"></div>
</header>