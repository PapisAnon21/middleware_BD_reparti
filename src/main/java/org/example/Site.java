package org.example;

//import java.util.Dictionary;
//import java.util.Hashtable;
//import java.util.ArrayList;

public class Site {
// un site est caracterise par son id,  adresse,, les colones qu'il contient, le user name et le mot de passe

    private String id;
    private  String adresse;
    private  String adress_site_replicat; // adresse du site qui le replique
    private  String column_names;
    private  String user_name;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdress_site_replicat() {
        return adress_site_replicat;
    }

    public void setAdress_site_replicat(String adress_site_replicat) {
        this.adress_site_replicat = adress_site_replicat;
    }

    public String getColumn_names() {
        return column_names;
    }

    public void setColumn_names(String column_names) {
        this.column_names = column_names;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


// this is how we create diction