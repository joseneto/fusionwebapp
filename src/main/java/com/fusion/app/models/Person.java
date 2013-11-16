package com.fusion.app.models;

import org.javalite.activejdbc.Model;

/**
 *
 * @author Auto Generate FusionWebApp Model
 */
public class Person extends Model{
    
     static{
        validatePresenceOf("first_name", "last_name");
    }    
}
