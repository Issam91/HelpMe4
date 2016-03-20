package com.example.amazigh.helpme.Classe;

import java.util.Date;

/**
 * Created by hp on 10/03/2016.
 */
public class Request {
    String login;
    String Category;
    String title;
    String Descrip;
    Date date;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String titre) {
        this.title = titre;
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
