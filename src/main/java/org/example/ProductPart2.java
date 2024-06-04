package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "ProductPart2")
public class ProductPart2 {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String prix_unitaire;

    @DatabaseField
    private String quantite_restante;

// les colones de l'autre table
   // @DatabaseField
    //private String nb_commande;

    //public void setNb_commande(String nb_commande) {
       // this.nb_commande = nb_commande;
    //}

   // public void setProvenance(String provenance) {
    //    this.provenance = provenance;
   // }

    //@DatabaseField
    //private String provenance;


    public void setPrix_unitaire(String prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public void setQuantite_restante(String quantite_restante) {
        this.quantite_restante = quantite_restante;
    }

    public void setId(int id) {
        this.id = id;
    }


}
