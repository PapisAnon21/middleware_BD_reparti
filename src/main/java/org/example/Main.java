package org.example;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.json.simple.JSONObject;

import org.example.Site;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.get;




public class Main {



    public static void main(String[] args) throws SQLException {



        System.out.println("Middleware demaarré, en attente des requetes");

        get("/hello", (req, res) -> "Hello World");


        createProduct();
        select_product();
        upadate_product();
        delete_product();


    }

    public static void create()
    {
        get("/test", (req, resp) -> {
            System.out.println("test reussi");
            resp.status(200);
            return  resp;
        });
    }

    public static void createStudent() throws SQLException {
        // dans un premier temps , on appelle le site a qui on veut s'adresser
        // puis qu'on fait une creation on appelle tous les sites

        Site site_1 = new Site();
        site_1.setId("1");
        site_1.setAdresse("jdbc:mysql://localhost:3306/site1");
        site_1.setUser_name("root");
        site_1.setPassword("papis");

        ConnectionSource connectionSource1 = new JdbcConnectionSource(site_1.getAdresse());
        ((JdbcConnectionSource)connectionSource1).setUsername(site_1.getUser_name());
        ((JdbcConnectionSource)connectionSource1).setPassword(site_1.getPassword());

        // initialisons a present le DAO
        Dao<Student, String> StudentDao  = DaoManager.createDao(connectionSource1, Student.class);

        // if you need to create the 'accounts' table make this call
        TableUtils.createTableIfNotExists(connectionSource1, Student.class);


        // nous allons simuler la creation des elements dans la base
        get("/createStudent", (req, rep) -> {
            // dans un premier temps, on recupere d'abord les parametres de la requete
            String prenom = req.queryParams("prenom");
            String nom = req.queryParams("nom");
            String age = req.queryParams("age");
            // une fois avoir recuperé tous ces params on s'adresse aux differents sgbd
            // on s'adresse au site 1

            Student ele1 = new Student();

            ele1.setPrenom(prenom);ele1.setAge(age);ele1.setNom(nom);

            System.out.println("creation en cours");

            StudentDao.create(ele1);


            System.out.println("creation terminée");
            return null;


        });
    }

    public static void createProduct() throws SQLException {
        // on appelle les 3 sites

        Site site_1 = new Site();
        site_1.setId("1");
        site_1.setAdresse("jdbc:mysql://localhost:3306/site1");
        site_1.setUser_name("root");
        site_1.setPassword("papis");

        Site site_2 = new Site();
        site_2.setId("2");
        site_2.setAdresse("jdbc:mysql://192.168.153.27:3306/site2");
        site_2.setUser_name("papis");
        site_2.setPassword("papis");

        Site site_3 = new Site();
        site_3.setId("3");
        site_3.setAdresse("jdbc:mysql://192.168.153.106:3306/Site3");
        site_3.setUser_name("maria");
        site_3.setPassword("maria");

        // la nous avons nos 3 sites

        // config pour le site 1
        ConnectionSource connectionSource1 = new JdbcConnectionSource(site_1.getAdresse());
        ((JdbcConnectionSource)connectionSource1).setUsername(site_1.getUser_name());
        ((JdbcConnectionSource)connectionSource1).setPassword(site_1.getPassword());

        // config pour le site 2
        ConnectionSource connectionSource2 = new JdbcConnectionSource(site_2.getAdresse());
        ((JdbcConnectionSource)connectionSource2).setUsername(site_2.getUser_name());
        ((JdbcConnectionSource)connectionSource2).setPassword(site_2.getPassword());

        // config pour le site 3

        ConnectionSource connectionSource3 = new JdbcConnectionSource(site_3.getAdresse());
        ((JdbcConnectionSource)connectionSource3).setUsername(site_3.getUser_name());
        ((JdbcConnectionSource)connectionSource3).setPassword(site_3.getPassword());

        // initialisons a present le DAO
        Dao<ProductPart1, String> ProductPart1_dao  = DaoManager.createDao(connectionSource1, ProductPart1.class);

        Dao<ProductPart2, String> ProductPart1_dao_replique  = DaoManager.createDao(connectionSource1, ProductPart2.class);

        // if you need to create the 'accounts' table make this call
        TableUtils.createTableIfNotExists(connectionSource1, ProductPart1.class);
        TableUtils.createTableIfNotExists(connectionSource1, ProductPart2.class);



        // initialisons a present le DAO
        Dao<ProductPart2, String> ProductPart2_dao  = DaoManager.createDao(connectionSource2, ProductPart2.class);
        Dao<ProductPart3, String> ProductPart2_dao_replique  = DaoManager.createDao(connectionSource2, ProductPart3.class);

        // if you need to create the 'accounts' table make this call
        TableUtils.createTableIfNotExists(connectionSource2, ProductPart2.class);
        TableUtils.createTableIfNotExists(connectionSource2, ProductPart3.class);



        // initialisons a present le DAO
        Dao<ProductPart3, String> ProductPart3_dao  = DaoManager.createDao(connectionSource3, ProductPart3.class);
        Dao<ProductPart1, String> ProductPart3_dao_replique  = DaoManager.createDao(connectionSource3, ProductPart1.class);


        TableUtils.createTableIfNotExists(connectionSource3, ProductPart3.class);
        TableUtils.createTableIfNotExists(connectionSource3, ProductPart1.class);

        // voila on a les dao pour chaque partie du produit
        get("/createProduct", (req, rep) -> {
            // dans un premier temps, on recupere d'abord les parametres de la requete
            String designation = req.queryParams("designation");
            String description = req.queryParams("description");
            String prix_unitaire = req.queryParams("prix_unitaire");
            String quantite_restante = req.queryParams("quantite_restante");
            String nb_commande = req.queryParams("nb_commande");
            String provenance = req.queryParams("provenance");

            // nous savons la ou est stocké

            ProductPart1 prod_part_1 = new ProductPart1();
            ProductPart2 prod_part_2 = new ProductPart2();
            ProductPart3 prod_part_3 = new ProductPart3();

            prod_part_1.setDesignation(designation);
            prod_part_2.setPrix_unitaire(prix_unitaire);prod_part_2.setQuantite_restante(quantite_restante);
            prod_part_3.setProvenance(provenance);prod_part_3.setNb_commande(nb_commande);

            ProductPart1_dao.create(prod_part_1);
            ProductPart1_dao_replique.create(prod_part_2);
            System.out.println("creation de part 1 succes");


            //



            ProductPart2_dao.create(prod_part_2);
            ProductPart2_dao_replique.create(prod_part_3);
            System.out.println("creation de part 2 succes");

            //

          ProductPart3_dao.create(prod_part_3);
          ProductPart3_dao_replique.create(prod_part_1);
          System.out.println("creation de part 3 succes");

            //

            connectionSource1.close();
            connectionSource2.close();
            connectionSource3.close();

            return null;


        });

    }

    public static void select_product() throws SQLException {
        // on definit nos 3 trois
        Site site_1 = new Site();
        site_1.setId("1");
        site_1.setAdresse("jdbc:mysql://localhost:3306/site1");
        site_1.setUser_name("root");
        site_1.setPassword("papis");

        Site site_2 = new Site();
        site_2.setId("2");
        site_2.setAdresse("jdbc:mysql://192.168.153.27:3306/site2");
        site_2.setUser_name("papis");
        site_2.setPassword("papis");

        Site site_3 = new Site();
        site_3.setId("3");
        site_3.setAdresse("jdbc:mysql://192.168.153.106:3306/Site3");
        site_3.setUser_name("maria");
        site_3.setPassword("maria");

        //
        ConnectionSource connectionSource1 = new JdbcConnectionSource(site_1.getAdresse());
        ((JdbcConnectionSource)connectionSource1).setUsername(site_1.getUser_name());
        ((JdbcConnectionSource)connectionSource1).setPassword(site_1.getPassword());

        // config pour le site 2
        ConnectionSource connectionSource2 = new JdbcConnectionSource(site_2.getAdresse());
        ((JdbcConnectionSource)connectionSource2).setUsername(site_2.getUser_name());
        ((JdbcConnectionSource)connectionSource2).setPassword(site_2.getPassword());

        // config pour le site 3

        ConnectionSource connectionSource3 = new JdbcConnectionSource(site_3.getAdresse());
        ((JdbcConnectionSource)connectionSource3).setUsername(site_3.getUser_name());
        ((JdbcConnectionSource)connectionSource3).setPassword(site_3.getPassword());

        // initialisons a present le DAO
        Dao<ProductPart1, String> ProductPart1_dao  = DaoManager.createDao(connectionSource1, ProductPart1.class);

        Dao<ProductPart2, String> ProductPart1_dao_replique  = DaoManager.createDao(connectionSource1, ProductPart2.class);

        // if you need to create the 'accounts' table make this call
        TableUtils.createTableIfNotExists(connectionSource1, ProductPart1.class);
        TableUtils.createTableIfNotExists(connectionSource1, ProductPart2.class);



        // initialisons a present le DAO
        Dao<ProductPart2, String> ProductPart2_dao  = DaoManager.createDao(connectionSource2, ProductPart2.class);
        Dao<ProductPart3, String> ProductPart2_dao_replique  = DaoManager.createDao(connectionSource2, ProductPart3.class);

        // if you need to create the 'accounts' table make this call
        TableUtils.createTableIfNotExists(connectionSource2, ProductPart2.class);
        TableUtils.createTableIfNotExists(connectionSource2, ProductPart3.class);



        // initialisons a present le DAO
        Dao<ProductPart3, String> ProductPart3_dao  = DaoManager.createDao(connectionSource3, ProductPart3.class);
        Dao<ProductPart1, String> ProductPart3_dao_replique  = DaoManager.createDao(connectionSource3, ProductPart1.class);


        TableUtils.createTableIfNotExists(connectionSource3, ProductPart3.class);
        TableUtils.createTableIfNotExists(connectionSource3, ProductPart1.class);

        get("/selectProductById/:id", (req, rep) -> {

            // nous allons recuperer un element par son id

            // recuperation des données de l'id 1 present dans le premier table
            ProductPart1 productPart1 = ProductPart1_dao.queryForId(req.params(":id"));

            // recuperation des données de l'id 1 present dans le second table
            ProductPart2 productPart2 = ProductPart2_dao.queryForId(req.params(":id"));

            // recuperation des données de l'id 1 present dans le 3e table
            ProductPart3 productPart3 = ProductPart3_dao.queryForId(req.params(":id"));

            // on creer un object json
            JSONObject produit = new JSONObject();

            produit.put("designation",productPart1.getDesignation() );
            produit.put("description", productPart1.getDescription());
            produit.put("prix_unitaire", productPart2.getPrix_unitaire());
            produit.put("quantite_restante", productPart2.getQuantite_restante());
            produit.put("provenance", productPart3.getProvenance());
            produit.put("nb_commande", productPart3.getNb_commande());


            return produit;
        });

        get("/selectProduct/", (req, rep) -> {

            // nous allons recuperer tous les prduits
            // une iteration sur tous les produits
            List<Object> produits_1 = new ArrayList<>();
            List<Object> produits_2 = new ArrayList<>();
            List<Object> produits_3 = new ArrayList<>();

            // page through all of the accounts in the database
            // on fait la jointure de tous ls tables
            // on cree les 2 query builider
            QueryBuilder<ProductPart1, String> prod_1 = ProductPart1_dao.queryBuilder();
            QueryBuilder<ProductPart2, String> prod_2 = ProductPart2_dao.queryBuilder();
            QueryBuilder<ProductPart3, String> prod_3 = ProductPart3_dao.queryBuilder();
            prod_1.join(prod_2);
            prod_3.join(prod_1);

            // nos listes contient tous les enregistrements

            return prod_3;
        });
    }
    public static void upadate_product() throws SQLException {

        // on definit nos 3 trois
        Site site_1 = new Site();
        site_1.setId("1");
        site_1.setAdresse("jdbc:mysql://localhost:3306/site1");
        site_1.setUser_name("root");
        site_1.setPassword("papis");

        Site site_2 = new Site();
        site_2.setId("2");
        site_2.setAdresse("jdbc:mysql://192.168.153.27:3306/site2");
        site_2.setUser_name("papis");
        site_2.setPassword("papis");

        Site site_3 = new Site();
        site_3.setId("3");
        site_3.setAdresse("jdbc:mysql://192.168.153.106:3306/Site3");
        site_3.setUser_name("maria");
        site_3.setPassword("maria");

        //
        ConnectionSource connectionSource1 = new JdbcConnectionSource(site_1.getAdresse());
        ((JdbcConnectionSource)connectionSource1).setUsername(site_1.getUser_name());
        ((JdbcConnectionSource)connectionSource1).setPassword(site_1.getPassword());

        // config pour le site 2
        ConnectionSource connectionSource2 = new JdbcConnectionSource(site_2.getAdresse());
        ((JdbcConnectionSource)connectionSource2).setUsername(site_2.getUser_name());
        ((JdbcConnectionSource)connectionSource2).setPassword(site_2.getPassword());

        // config pour le site 3

        ConnectionSource connectionSource3 = new JdbcConnectionSource(site_3.getAdresse());
        ((JdbcConnectionSource)connectionSource3).setUsername(site_3.getUser_name());
        ((JdbcConnectionSource)connectionSource3).setPassword(site_3.getPassword());

        // initialisons a present le DAO
        Dao<ProductPart1, String> ProductPart1_dao  = DaoManager.createDao(connectionSource1, ProductPart1.class);

        Dao<ProductPart2, String> ProductPart1_dao_replique  = DaoManager.createDao(connectionSource1, ProductPart2.class);

        // if you need to create the 'accounts' table make this call
        TableUtils.createTableIfNotExists(connectionSource1, ProductPart1.class);
        TableUtils.createTableIfNotExists(connectionSource1, ProductPart2.class);



        // initialisons a present le DAO
        Dao<ProductPart2, String> ProductPart2_dao  = DaoManager.createDao(connectionSource2, ProductPart2.class);
        Dao<ProductPart3, String> ProductPart2_dao_replique  = DaoManager.createDao(connectionSource2, ProductPart3.class);

        // if you need to create the 'accounts' table make this call
        TableUtils.createTableIfNotExists(connectionSource2, ProductPart2.class);
        TableUtils.createTableIfNotExists(connectionSource2, ProductPart3.class);



        // initialisons a present le DAO
        Dao<ProductPart3, String> ProductPart3_dao  = DaoManager.createDao(connectionSource3, ProductPart3.class);
        Dao<ProductPart1, String> ProductPart3_dao_replique  = DaoManager.createDao(connectionSource3, ProductPart1.class);


        TableUtils.createTableIfNotExists(connectionSource3, ProductPart3.class);
        TableUtils.createTableIfNotExists(connectionSource3, ProductPart1.class);
        // on va updater un produit par son id

        get("/updateById/:id", (req,rep) -> {

            String designation = req.queryParams("designation");
            String description = req.queryParams("description");
            String prix_unitaire = req.queryParams("prix_unitaire");
            String quantite_restante = req.queryParams("quantite_restante");
            String provenance = req.queryParams("provenance");
            String nb_commande = req.queryParams("nb_commande");

            // dans un premier temps on get l'objet
            ProductPart1 prod_part_1 = ProductPart1_dao.queryForId(req.queryParams(":id"));
            ProductPart2 prod_part_2 = ProductPart2_dao.queryForId(req.queryParams(":id"));
            ProductPart3 prod_part_3 = ProductPart3_dao.queryForId(req.queryParams(":id"));

            if(designation != null){prod_part_1.setDesignation(designation);}
            if(description != null){prod_part_1.setDescription(description);}
            ProductPart1_dao.update(prod_part_1);
            ProductPart3_dao_replique.update(prod_part_1);

            if(prix_unitaire != null){prod_part_2.setPrix_unitaire(prix_unitaire);}
            if(quantite_restante != null){prod_part_2.setQuantite_restante(quantite_restante);}
            ProductPart2_dao.update(prod_part_2);
            ProductPart1_dao_replique.update(prod_part_2);

            if(provenance != null){prod_part_3.setProvenance(provenance);}
            if(nb_commande != null){prod_part_3.setNb_commande(nb_commande);}
            ProductPart3_dao.update(prod_part_3);
            ProductPart2_dao_replique.update(prod_part_3);

            return null;
        });
    }

    public static void delete_product() throws SQLException {

        // on definit nos 3 trois
        Site site_1 = new Site();
        site_1.setId("1");
        site_1.setAdresse("jdbc:mysql://localhost:3306/site1");
        site_1.setUser_name("root");
        site_1.setPassword("papis");

        Site site_2 = new Site();
        site_2.setId("2");
        site_2.setAdresse("jdbc:mysql://192.168.153.27:3306/site2");
        site_2.setUser_name("papis");
        site_2.setPassword("papis");

        Site site_3 = new Site();
        site_3.setId("3");
        site_3.setAdresse("jdbc:mysql://192.168.153.106:3306/Site3");
        site_3.setUser_name("maria");
        site_3.setPassword("maria");

        //
        ConnectionSource connectionSource1 = new JdbcConnectionSource(site_1.getAdresse());
        ((JdbcConnectionSource)connectionSource1).setUsername(site_1.getUser_name());
        ((JdbcConnectionSource)connectionSource1).setPassword(site_1.getPassword());

        // config pour le site 2
        ConnectionSource connectionSource2 = new JdbcConnectionSource(site_2.getAdresse());
        ((JdbcConnectionSource)connectionSource2).setUsername(site_2.getUser_name());
        ((JdbcConnectionSource)connectionSource2).setPassword(site_2.getPassword());

        // config pour le site 3

        ConnectionSource connectionSource3 = new JdbcConnectionSource(site_3.getAdresse());
        ((JdbcConnectionSource)connectionSource3).setUsername(site_3.getUser_name());
        ((JdbcConnectionSource)connectionSource3).setPassword(site_3.getPassword());

        // initialisons a present le DAO
        Dao<ProductPart1, String> ProductPart1_dao  = DaoManager.createDao(connectionSource1, ProductPart1.class);

        Dao<ProductPart2, String> ProductPart1_dao_replique  = DaoManager.createDao(connectionSource1, ProductPart2.class);


        TableUtils.createTableIfNotExists(connectionSource1, ProductPart1.class);
        TableUtils.createTableIfNotExists(connectionSource1, ProductPart2.class);



        // initialisons a present le DAO
        Dao<ProductPart2, String> ProductPart2_dao  = DaoManager.createDao(connectionSource2, ProductPart2.class);
        Dao<ProductPart3, String> ProductPart2_dao_replique  = DaoManager.createDao(connectionSource2, ProductPart3.class);

        // if you need to create the 'accounts' table make this call
        TableUtils.createTableIfNotExists(connectionSource2, ProductPart2.class);
        TableUtils.createTableIfNotExists(connectionSource2, ProductPart3.class);



        // initialisons a present le DAO
        Dao<ProductPart3, String> ProductPart3_dao  = DaoManager.createDao(connectionSource3, ProductPart3.class);
        Dao<ProductPart1, String> ProductPart3_dao_replique  = DaoManager.createDao(connectionSource3, ProductPart1.class);


        TableUtils.createTableIfNotExists(connectionSource3, ProductPart3.class);
        TableUtils.createTableIfNotExists(connectionSource3, ProductPart1.class);
        // on va updater un produit par son id

        get("/deleteById/:id", (req,rep) -> {



            // dans un premier temps on get l'objet
            ProductPart1 prod_part_1 = ProductPart1_dao.queryForId(req.queryParams(":id"));
            ProductPart2 prod_part_2 = ProductPart2_dao.queryForId(req.queryParams(":id"));
            ProductPart3 prod_part_3 = ProductPart3_dao.queryForId(req.queryParams(":id"));


            ProductPart1_dao.delete(prod_part_1);
            ProductPart3_dao_replique.delete(prod_part_1);


            ProductPart2_dao.delete(prod_part_2);
            ProductPart1_dao_replique.delete(prod_part_2);


            ProductPart3_dao.delete(prod_part_3);
            ProductPart2_dao_replique.delete(prod_part_3);

            return null;
        });
    }

    public static void gestion_panne()
    {
        // voici comment on gere la panne
        // 1. On s'adresse a un site
        // 2. Lorsqu'on obtient une erreur de connexion
        // 3. On recupere sur le site, le site qui le replique
        // 4. Et on s'adresse au site qui le replique
        // 5. Une fois qu'on obtient une reponse favorable
        // 6. On enregistre dans un objet l'etat du site qui n'a pas repondu  et la requete echouée
        // 7. Sur une prochaine requete on s'adresse au site, s'il repond
        // 8. On lui passe la requete pour qu'il se mette a jour
        // 9. Puis on lui fait executer la nouvelle requete
    }
}
