/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.squareapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import static spark.Spark.*;

/**
 *
 * @author andres
 */
public class SquareAPI {

    
    public static void main(String[] args) {
        staticFileLocation("/public");
        get("/square", (req,res) -> {
            return getResult(req.queryParams("value"));
        });
    }

    public static String getResult(String number) {
        String result = "";
        try {
            URL querry = new URL("https://8fkc0m7l5h.execute-api.us-west-2.amazonaws.com/prod/squareCalculator?value=" + number);

            try (BufferedReader reader
                    = new BufferedReader(new InputStreamReader(querry.openStream()))) {
                String inputLine = null;
                while ((inputLine = reader.readLine()) != null) {
                    result += inputLine;
                }
            } catch (IOException x) {
                System.err.println(x);
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(SquareAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
