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
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.myapp.bean.Fish;
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
public class FishSparql {

    String endpoint = "http://localhost:8890/sparql";
    final String username = "dba";
    final String password = "dba";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FishSparql f = new FishSparql();
        f.test();
    }

    public Vector<Fish> downloadFishes(int offset, int limit) {

        String endpointsSparql
                = "select distinct ?a where{?a a <http://www.w3.org/2002/07/owl#Thing>} LIMIT " + limit + " OFFSET " + offset;

        Vector<Fish> fishes = new Vector<Fish>();
        try {
            System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
            QueryExecution x = QueryExecutionFactory.sparqlService(endpoint, endpointsSparql);
            ResultSet results = x.execSelect();
//            ResultSetFormatter.output(System.out, results, ResultsFormat.FMT_RS_BIO);
            while (results.hasNext()) {
                QuerySolution row = results.next();
                String url = row.get("a").toString();
                Fish fish = new Fish();
                fish.setFishURL(url);   // URL
                fish.setFishName(url.substring(28, url.length()).replace("_", " "));    // geting only name
                fish = getFishDetails(url, fish);
                fishes.add(fish);
                System.out.println(fish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fishes;

    }

    Fish getFishDetails(String url, Fish fish) {
        Document doc;
        try {

            // need http protocol
            doc = Jsoup.connect(url).get();

            Elements abstracts = doc.select("span[property=dbpedia-owl:abstract][xml:lang=en]");
            for (Element element : abstracts) {
                fish.setFishAbstract(element.text());
            }
            Elements photos = doc.select("a[rel=dbpedia-owl:thumbnail nofollow]");
            for (Element element : photos) {
                fish.setFishPhoto(element.text());
            }

            if ("".equals(fish.getFishPhoto()) || null == fish.getFishPhoto()) {
                fish.setFishPhoto("Assets\\noPhoto.jpg");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fish;
    }

    public Vector<String> getAllFishName() {
        String endpointsSparql
                = "select distinct ?a where{?a a <http://www.w3.org/2002/07/owl#Thing>}";

        Vector<String> names = new Vector<String>();
        try {
            System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
            QueryExecution x = QueryExecutionFactory.sparqlService(endpoint, endpointsSparql);
            ResultSet results = x.execSelect();

            while (results.hasNext()) {
                QuerySolution row = results.next();
                String name = row.get("a").toString();
                names.add(name.substring(28, name.length()).replace("_", " "));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }

    public int getNumberOfFishes() {
        String endpointsSparql
                = "select (count(distinct ?a) AS ?count) where{?a a <http://www.w3.org/2002/07/owl#Thing>}";
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

    void test() {
        String endpoint2 = "http://sparql.bioontology.org/";
        String endpointsSparql
                = "PREFIX omv: <http://omv.ontoware.org/2005/05/ontology#>\n" +
"\n" +
"SELECT ?ont ?name ?acr\n" +
"WHERE {\n" +
"	?ont a omv:Ontology .\n" +
"	?ont omv:acronym ?acr .\n" +
"	?ont omv:name ?name .\n" +
"}\n" +
"        ";

        try {
            System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
            QueryExecution x = QueryExecutionFactory.sparqlService(endpoint, endpointsSparql);
            ResultSet results = x.execSelect();
            ResultSetFormatter.out(System.out, results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
