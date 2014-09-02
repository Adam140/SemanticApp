/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.bean;

/**
 *
 * @author Adam
 */
public class Publication {
    private String publicationURL;
    private String publicationName;
    private String journalName;
    private String year;
    private String subjectSpecies;
    private String doi;
    private String publicationPhoto;
    private String publicationAbstract;

    /**
     * @return the publicationURL
     */
    public String getPublicationName() {
        return getPublicationURL();
    }

    /**
     * @param publicationName the publicationURL to set
     */
    public void setPublicationName(String publicationName) {
        this.setPublicationURL(publicationName);
    }

    /**
     * @return the journalName
     */
    public String getJournalName() {
        return journalName;
    }

    /**
     * @param journalName the journalName to set
     */
    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the subjectSpecies
     */
    public String getSubjectSpecies() {
        return subjectSpecies;
    }

    /**
     * @param subjectSpecies the subjectSpecies to set
     */
    public void setSubjectSpecies(String subjectSpecies) {
        this.subjectSpecies = subjectSpecies;
    }

    /**
     * @return the doi
     */
    public String getDoi() {
        return doi;
    }

    /**
     * @param doi the doi to set
     */
    public void setDoi(String doi) {
        this.doi = doi;
    }

    /**
     * @return the publicationPhoto
     */
    public String getPublicationPhoto() {
        return publicationPhoto;
    }

    /**
     * @param publicationPhoto the publicationPhoto to set
     */
    public void setPublicationPhoto(String publicationPhoto) {
        this.publicationPhoto = publicationPhoto;
    }

    @Override
    public String toString() {
        return "Publication{" + "publicationName=" + getPublicationURL() + ", journalName=" + getJournalName() + ", year=" + getYear() + ", subjectSpecies=" + getSubjectSpecies() + ", doi=" + getDoi() + ", publicationPhoto=" + getPublicationPhoto() + '}';
    }

    /**
     * @return the publicationAbstract
     */
    public String getPublicationAbstract() {
        return publicationAbstract;
    }

    /**
     * @param publicationAbstract the publicationAbstract to set
     */
    public void setPublicationAbstract(String publicationAbstract) {
        this.publicationAbstract = publicationAbstract;
    }

    /**
     * @return the publicationURL
     */
    public String getPublicationURL() {
        return publicationURL;
    }

    /**
     * @param publicationURL the publicationURL to set
     */
    public void setPublicationURL(String publicationURL) {
        this.publicationURL = publicationURL;
    }
    
}
