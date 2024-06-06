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


    public int getId() {
        return id;
    }

    public String getNb_commande() {
        return nb_commande;
    }

    public String getProvenance() {
        return provenance;
    }

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
