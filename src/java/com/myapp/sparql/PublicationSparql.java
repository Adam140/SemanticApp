/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sparql;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.myapp.bean.Fish;
import com.myapp.bean.Publication;
import java.io.IOException;
import java.util.Vector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Pavlos Fafalios
 */
public class PublicationSparql {

    String endpoint = "http://localhost:8890/sparql";
    final String username = "dba";
    final String password = "dba";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PublicationSparql f = new PublicationSparql();
        f.downloadPublications(0, 10, null, null, null, "Yellowfin tuna");
    }

    public Vector<Publication> downloadPublications(int offset, int limit, String afterOrBefore, String year, String journalName, String speciesName) {

        String endpointsSparql
                = "PREFIX mar: <http://ics.forth.gr/Ontology/MarineTLO/imarine#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT distinct *\n"
                + "WHERE {\n"
                + "?publication mar:hasSubjectSpecies ?species ;\n"
                + "		mar:hasPublicationTitle ?journal ;\n"
                + "             mar:hasDOI ?doi ;\n"
                + "             mar:hasTitle ?title ;\n"
                + "		mar:assignedYear ?year .\n"
                + "FILTER (";
        
        String filter = "";
        
        if(journalName != null)
            filter += "?journal = \"" + journalName + "\"";
        
        if(speciesName != null)
        {
            if(!"".equals(filter))
                filter += " && ";
            
            filter += "?species = <http://dbpedia.org/resource/" + speciesName.replaceAll(" ", "_") + ">";
        }
        
        if(year != null)
        {
            if(speciesName != null || journalName != null)
                filter += " && ";
            
            if("after".equals(afterOrBefore))
                filter += "xsd:decimal(?year) >= " + year ;
            
            if("before".equals(afterOrBefore))
                filter += "xsd:decimal(?year) <= " + year ;
        }

        endpointsSparql += filter + ")\n"
                + "}\n"
                + "LIMIT " + limit + " OFFSET " + offset;
        
        Vector<Publication> publications = new Vector<Publication>();
        try {
            System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
            QueryExecution x = QueryExecutionFactory.sparqlService(endpoint, endpointsSparql);
            ResultSet results = x.execSelect();
            while (results.hasNext()) {
                QuerySolution row = results.next();
                String journal = row.get("journal").toString();
                String species = row.get("species").toString();
                String yearRow = row.get("year").toString();
                String doi = row.get("doi").toString();
                String title = row.get("title").toString();
                String publicationName = row.get("publication").toString();
                Publication publication = new Publication();
                publication.setJournalName(journal);
                publication.setPublicationName(publicationName);
                publication.setYear(yearRow);
                publication.setSubjectSpecies(species);
                publications.add(publication);
                publication.setDoi(doi);
                publication.setPublicationName(title);
                publication = getPublicationDetails(publicationName, publication);
                System.out.println(publication);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return publications;

    }

    public Vector<String> getAllJournal() {
        String endpointsSparql
                = "select distinct ?c WHERE{ ?a <http://ics.forth.gr/Ontology/MarineTLO/imarine#hasPublicationTitle> ?c}";

        Vector<String> names = new Vector<String>();
        try {
            System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
            QueryExecution x = QueryExecutionFactory.sparqlService(endpoint, endpointsSparql);
            ResultSet results = x.execSelect();

            while (results.hasNext()) {
                QuerySolution row = results.next();
                String name = row.get("c").toString();
                names.add(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }
    
     Publication getPublicationDetails(String url, Publication publication)
    {
        Document doc;
	try {
 
		// need http protocol
		doc = Jsoup.connect(url).get();
 
		Elements photos = doc.select("img[class=look-inside-cover]");
		for (Element element : photos) {
                    publication.setPublicationPhoto("http://link.springer.com/" + element.attr("src"));
		}
		Elements abstracts = doc.select("div[class=abstract-content formatted]");
		for (Element element : abstracts) {
                    publication.setPublicationAbstract(element.html());
		}
                
                if("".equals(publication.getPublicationPhoto()) || null == publication.getPublicationPhoto())
                    publication.setPublicationPhoto("Assets\\noPhoto.jpg");
 
	} catch (IOException e) {
		e.printStackTrace();
	}
        return publication;
    }
     
       public int getNumberOfPublication(String afterOrBefore, String year, String journalName, String speciesName)
     {
           String endpointsSparql
                = "PREFIX mar: <http://ics.forth.gr/Ontology/MarineTLO/imarine#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "SELECT (count(*) AS ?count)\n"
                + "WHERE {\n"
                + "?publication mar:hasSubjectSpecies ?species ;\n"
                + "		mar:hasPublicationTitle ?journal ;\n"
                + "             mar:hasDOI ?doi ;\n"
                + "             mar:hasTitle ?title ;\n"
                + "		mar:assignedYear ?year .\n"
                + "FILTER (";
        
        String filter = "";
        
        if(journalName != null)
            filter += "?journal = \"" + journalName + "\"";
        
        if(speciesName != null)
        {
            if(!"".equals(filter))
                filter += " && ";
            
            filter += "?species = <http://dbpedia.org/resource/" + speciesName.replaceAll(" ", "_") + ">";
        }
        
        if(year != null)
        {
            if(speciesName != null || journalName != null)
                filter += " && ";
            
            if("after".equals(afterOrBefore))
                filter += "xsd:decimal(?year) >= " + year ;
            
            if("before".equals(afterOrBefore))
                filter += "xsd:decimal(?year) <= " + year ;
        }

        endpointsSparql += filter + ")\n"
                + "}";
        try {
            System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
            QueryExecution x = QueryExecutionFactory.sparqlService(endpoint, endpointsSparql);
            ResultSet results = x.execSelect();
            QuerySolution row = results.next();
            String count = row.get("count").toString();
            count = count.substring(0, count.indexOf("^"));
            return Integer.parseInt(count);
        } catch (Exception e) {
		e.printStackTrace();
	}
        return 0;
     }
}
