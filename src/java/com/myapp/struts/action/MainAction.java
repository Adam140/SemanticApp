/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.bean.Fish;
import com.myapp.bean.Publication;
import com.myapp.sparql.FishSparql;
import com.myapp.sparql.PublicationSparql;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Adam
 */
public class MainAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SPECIES_SCREEN = "Species";
    private static final String HOME_SCREEN = "Home";
    private static final String ABOUT_SCREEN = "About";
    private static final String PUBLICATION_SCREEN = "Publication";
    private static final String PUBLICATION_RESULT = "PublicationResult";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession(false);
        String currentPage = (String) session.getAttribute("currentPage");
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = HOME_SCREEN;
            session.setAttribute("currentPage", currentPage);
        }
        String nextPage = request.getParameter("nextPage");

        if (null == nextPage) {
            nextPage = "";
        } else {
            currentPage = nextPage;
        }

        session.setAttribute("currentPage", currentPage);

        if (nextPage.equals(SPECIES_SCREEN) || (currentPage.equals(SPECIES_SCREEN) && request.getParameter("offset") != null)) {
            if (nextPage.equals(SPECIES_SCREEN)) {
                session.removeAttribute("offset");
            }
            FishSparql fishes = new FishSparql();
            Vector<Fish> vector = new Vector<Fish>();
            int offset = 0;
            int limit = 10;
//            if(session.getAttribute("limit") == null)
//                session.setAttribute("limit", limit);   

            if (session.getAttribute("offset") != null) {
                offset = (int) session.getAttribute("offset");
            }

            if (request.getParameter("offset") != null && !"".equals((String) request.getParameter("offset"))) {
                offset += Integer.parseInt((String) request.getParameter("offset"));
            }

            int numberOfFishes = fishes.getNumberOfFishes();

            if (offset < 0) {
                offset = 0;
            } else if (offset >= numberOfFishes) {
                offset -= limit;
            }
            vector = fishes.downloadFishes(offset, limit);

            session.setAttribute("offset", offset);
            request.setAttribute("fishes", vector);
            request.setAttribute("totalPageNumber", Math.round(numberOfFishes / limit + 0.5));
            request.setAttribute("actualPage", offset / limit + 1);
        }

        if (nextPage.equals(PUBLICATION_SCREEN)) {
            session.removeAttribute("offset");
            if (null == session.getAttribute("publicationNames") || ((Vector<String>) session.getAttribute("publicationNames")).size() == 0) {
                PublicationSparql publication = new PublicationSparql();
                session.setAttribute("publicationNames", publication.getAllJournal());
            }
            if (null == session.getAttribute("fishNames") || ((Vector<String>) session.getAttribute("fishNames")).size() == 0) {
                FishSparql fishes = new FishSparql();
                session.setAttribute("fishNames", fishes.getAllFishName());
            }

        }
        if (currentPage.equals(PUBLICATION_SCREEN) && "search".equals(request.getParameter("action")) || (currentPage.equals(PUBLICATION_SCREEN) && request.getParameter("offset") != null)) {
            String year = (String) request.getParameter("year");
            String beforeOrAfter = (String) request.getParameter("beforeOrAfter");
            String journalName = (String) request.getParameter("journalName");
            String subjectSpeciesName = (String) request.getParameter("subjectSpeciesName");

            if (journalName.equals("")) {
                journalName = null;
            }
            if (subjectSpeciesName.equals("")) {
                subjectSpeciesName = null;
            }
            if (year.equals("")) {
                year = null;
            }

            int offset = 0;
            int limit = 10;
//            if(session.getAttribute("limit") == null)
//                session.setAttribute("limit", limit);   
            if (session.getAttribute("offset") != null) {
                offset = (int) session.getAttribute("offset");
            }

            if (request.getParameter("offset") != null && !"".equals((String) request.getParameter("offset"))) {
                offset += Integer.parseInt((String) request.getParameter("offset"));
            }
            else
                offset = 0;

            PublicationSparql publication = new PublicationSparql();
            int numberOfPublication = publication.getNumberOfPublication(beforeOrAfter, year, journalName, subjectSpeciesName);

            if (offset < 0) {
                offset = 0;
            } else if (offset >= numberOfPublication) {
                offset -= limit;
            }

            session.setAttribute("offset", offset);
            request.setAttribute("totalPageNumber", Math.round(numberOfPublication / limit + 0.5 ));
            request.setAttribute("actualPage", offset / limit + 1);

            Vector<Publication> vector = new Vector<Publication>();
            vector = publication.downloadPublications(offset, limit, beforeOrAfter, year, journalName, subjectSpeciesName);
            request.setAttribute("publications", vector);

            return mapping.findForward(PUBLICATION_RESULT);
        }
        return mapping.findForward(currentPage);
    }
}
