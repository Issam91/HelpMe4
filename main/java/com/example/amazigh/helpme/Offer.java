package com.example.amazigh.helpme;

import java.util.Date;

/**
 * Created by hp on 10/03/2016.
 */
public class Offer {
    String login;
    String Category;
    String Descrip;
    Date date;
    Date dmax;
    Double prixmax;
    Double prixmin;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescrip() {
        return Descrip;
    }

    public void setDescrip(String descrip) {
        Descrip = descrip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDmax() {
        return dmax;
    }

    public void setDmax(Date dmax) {
        this.dmax = dmax;
    }

    public Double getPrixmax() {
        return prixmax;
    }

    public void setPrixmax(Double prixmax) {
        this.prixmax = prixmax;
    }

    public Double getPrixmin() {
        return prixmin;
    }

    public void setPrixmin(Double prixmin) {
        this.prixmin = prixmin;
    }
}
