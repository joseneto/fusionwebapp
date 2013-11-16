package com.fusion.app.models;

import org.javalite.activejdbc.Model;


/**
 *
 * @author Auto Generate FusionWebApp Model
 */

public class Empresa extends Model{
    
    static{
   convertTimestamp("criacao", "dd/MM/yyyy HH:mm:ss");
   }

public void beforeSave(){
   if (getString("normal").equals("true")) {set("normal", 1);} else {set("normal", 0);}
}

    
}