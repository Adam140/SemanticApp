<%-- 
    Document   : publication
    Created on : 2014-06-12, 12:29:26
    Author     : Adam
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<!--<script src="/resources/demos/external/jquery.mousewheel.js"></script>-->
<script>
    $(function() {
//        JOURNAL NAMES
        var availableJournalName = [
    <logic:iterate name="publicationNames" id="publicationName" type="String">
            "<bean:write name="publicationName"/>",
    </logic:iterate>
        ];
        $("#journalName").autocomplete({
            source: availableJournalName,
            minLength: 5,
            delay: 500
        });

//        FISH NAMES
        var availableFishName = [
    <logic:iterate name="fishNames" id="speciesName" type="String">"<bean:write name="speciesName"/>",</logic:iterate>
        ];
        $("#subjectSpeciesName").autocomplete({
            source: availableFishName,
            minLength: 4,
            delay: 500
        });

//        SPINNER FOR YEAR
        var spinner = $("#year").spinner();
        $('#loadingDiv').hide();
//        POST AJAX
        $("input[name=SearchButton]").click(function(){
            var $loading = $('#loadingDiv').show();
             $( "#result" ).html( "" );
            var posting = $.post( "/SemanticApp/MainPage.do", { action: "search", 
                                                                year: $('#year').val(), 
                                                                beforeOrAfter: $('#beforeOrAfter').val(),
                                                                journalName: $('#journalName').val(),
                                                                offset: $('#offset').val(),
                                                                subjectSpeciesName: $('#subjectSpeciesName').val()} );
            posting.done(function( data ) {
              $loading.hide();
              $( "#result" ).html( data );
              $("input[id=offset]").val("");
            });
        });
    });
</script>
<div>
    <label for="year">Publication year </label>
    <select class="ui-spinner-input" id="beforeOrAfter">
                <option value="after">after</option>
                <option value="before">before</option>
</select>
    <input id="year" name="value" value="2010">
</div>
</br>
<div class="ui-widget">
    <label for="journalName">Journal name: </label>
    <input id="journalName">
</div>
</br>
<div class="ui-widget">
    <label for="subjectSpeciesName">Fish name: </label>
    <input id="subjectSpeciesName">
</div>
<input type="hidden" id="offset" value=""/>
</br>
<input type="button" value="Search" name="SearchButton">
</br>
    <div id="loadingDiv" style="text-align: center;">
        <img src="Assets/spinner.GIF"/>
    </div>
<div id="result">
</div>