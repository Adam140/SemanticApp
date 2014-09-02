<%-- 
    Document   : pagination
    Created on : 2014-06-12, 18:24:14
    Author     : Adam
--%>
<script>
    function specialClick()
    {
        alert($(this).attr('val'));
//        if('publicationScreen' == <%= session.getAttribute("currentPage") %>)
//        {
            $("inpute[id=offset]").val($(this).attr('val'));
            $("input[name=SearchButton]").click();
//        }
    }
</script>
<div style="text-align: center">
    <div style="display:inline;" class="arrow">
        <a href="#" val="-10"><img src="Assets/back.png"/></a>
    </div>
    <div style="display: inline; vertical-align: middle;">
        <%= request.getAttribute( "actualPage" )%> from <%= request.getAttribute( "totalPageNumber" ) %> pages
    </div>
   <div style="display:inline;" class="arrow">
        <a href="#" val="10"><img src="Assets/forward.png"/></a>
    </div>
</div>