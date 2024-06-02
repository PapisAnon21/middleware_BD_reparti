package org.example;

import static spark.Spark.*;

public class Middleware {
    Site site = new Site();


    public Middleware()
    {
        get("/hello", (req, res) -> "Hello World");
    }
    // dans un premier temps, le middleware va se connecter a tous les 3 sites
}
