package com.ehab.newsapp.model;

/**
 * Created by ehabhamdy on 3/23/18.
 */


public class Results{
    private String id;

    private String webUrl;

    private String sectionId;

    private String pillarId;

    private String isHosted;

    private String apiUrl;

    private String sectionName;

    private String webTitle;

    private Tag[] tags;

    private String type;

    private String webPublicationDate;

    private String pillarName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getPillarId() {
        return pillarId;
    }

    public void setPillarId(String pillarId) {
        this.pillarId = pillarId;
    }

    public String getIsHosted() {
        return isHosted;
    }

    public void setIsHosted(String isHosted) {
        this.isHosted = isHosted;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getPillarName() {
        return pillarName;
    }

    public void setPillarName(String pillarName) {
        this.pillarName = pillarName;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", webUrl = " + webUrl + ", sectionId = " + sectionId + ", pillarId = " + pillarId + ", isHosted = " + isHosted + ", apiUrl = " + apiUrl + ", sectionName = " + sectionName + ", webTitle = " + webTitle + ", type = " + type + ", webPublicationDate = " + webPublicationDate + ", pillarName = " + pillarName + "]";
    }
}