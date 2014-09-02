<%-- 
    Document   : mainPage
    Created on : 2014-06-11, 17:50:14
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css'>
        <link href="Assets/styles.css" rel="stylesheet" type="text/css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <title><bean:message key="application.name"/></title>
    </head>
    <body>
        <script>
            $(function() {
                if("Species" == "<%= session.getAttribute("currentPage") %>")
                {
                    $(document).on('click', '.arrow', function(e) {
                        window.location.href = "/SemanticApp/MainPage.do?offset=" + $(this).children("a").attr("val");
                        e.preventDefault();
                    });
                }
                else{
                    $(document).on('click', '.arrow', function(e) {
                        $("input[id=offset]").val($(this).children("a").attr("val"));
                        $("input[name=SearchButton]").click();
                        e.preventDefault();
                    });
                }
            });
        </script>
        <div class="wrapper">
            <tiles:insert attribute="menu"/>
            <section>
                <div class="horSeparator"></div>
                <h1><tiles:getAsString name="sectionTitle"/></h1>
                <p>
                    <tiles:insert attribute="sectionBody"/>
                </p>
                <div class="horSeparator"></div>
            </section>
            <article class="post">
                <tiles:insert attribute="body"/> 
            </article>
            <section class="sectionFooter">
                <div class="footerBox">
                    <h2>Abstract</h2>
                    <p>
                       One of the main characteristics of biodiversity data is its
cross-disciplinary feature and the extremely broad range of data types,
structures, and semantic concepts which encompasses. Moreover, biodi-
versity data, especially in the marine domain, is widely distributed, with
few well-established repositories or standard protocols for their archiving,
access, and retrieval...
                    </p>
                    <a href="">read more...</a>
                </div>
                <div class="footerBox">
                    <h2>Concluding Remarks</h2>
                    <p>
                       To tackle the need for having integrated sets of facts about marine species, and
thus to assist research about species and biodiversity, we have described a toplevel...
ontology for that domain
                    </p>
                    <a href="">read more...</a>
                </div>
                <div class="clearfloat"></div>
            </section>
        </div>
        <footer>
            <p>
                Copyright &copy; Adam Kunikowski. All rights reserved. Designed by <a href="http://www.free-responsive-templates.com" title="free responsive templates">Free Responsive Templates</a>, Validation 
                <a class="footerLink" href="http://validator.w3.org/check/referer" title="This page validates as HTML5"><abbr title="HyperText Markup Language">HTML5</abbr></a> | 
                <a class="footerLink" href="http://jigsaw.w3.org/css-validator/check/referer" title="This page validates as CSS"><abbr title="Cascading Style Sheets">CSS3</abbr></a>
            </p>
        </footer>
    </body>
</html>
