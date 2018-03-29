package com.ehab.newsapp.model;

/**
 * Created by ehabhamdy on 3/29/18.
 */

public class Tag {
    String webTitle;
    String firstName;
    String lastName;

    public Tag() {
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
