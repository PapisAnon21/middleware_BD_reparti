package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "ProductPart1")
public class ProductPart1 {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String designation;

    @DatabaseField
    private String description;
// les colones de l'autre table
    //@DatabaseField
    //private String prix_unitaire;

   // @DatabaseField
   // private String quantite_restante;


    //public void setPrix_unitaire(String prix_unitaire) {
    //    this.prix_unitaire = prix_unitaire;
    //}

   // public void setQuantite_restante(String quantite_restante) {
    //    this.quantite_restante = quantite_restante;
    //}


    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }
}
