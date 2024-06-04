package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "ProductPart3")
public class ProductPart3 {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String nb_commande;

    @DatabaseField
    private String provenance;
// les colones de l'autre table
    //@DatabaseField
   // private String designation;

   // @DatabaseField
    private String description;

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public void setNb_commande(String nb_commande) {
        this.nb_commande = nb_commande;
    }

    public void setId(int id) {
        this.id = id;
    }


}
