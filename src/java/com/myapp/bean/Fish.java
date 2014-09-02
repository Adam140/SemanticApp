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
public class Fish {
    private String fishName;
    private String fishURL;
    private String fishPhoto;
    private String fishAbstract;

    /**
     * @return the fishName
     */
    public String getFishName() {
        return fishName;
    }

    /**
     * @param fishName the fishName to set
     */
    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    /**
     * @return the fishURL
     */
    public String getFishURL() {
        return fishURL;
    }

    /**
     * @param fishURL the fishURL to set
     */
    public void setFishURL(String fishURL) {
        this.fishURL = fishURL;
    }


    /**
     * @return the fishPhoto
     */
    public String getFishPhoto() {
        return fishPhoto;
    }

    /**
     * @param fishPhoto the fishPhoto to set
     */
    public void setFishPhoto(String fishPhoto) {
        this.fishPhoto = fishPhoto;
    }

    /**
     * @return the fishAbstract
     */
    public String getFishAbstract() {
        return fishAbstract;
    }

    /**
     * @param fishAbstract the fishAbstract to set
     */
    public void setFishAbstract(String fishAbstract) {
        this.fishAbstract = fishAbstract;
    }
    @Override
    public String toString() {
        return "Fish{" + "fishName=" + fishName + ", fishURL=" + fishURL + ", fishPhoto=" + fishPhoto + ", fishAbstract=" + fishAbstract + '}';
    }
}
