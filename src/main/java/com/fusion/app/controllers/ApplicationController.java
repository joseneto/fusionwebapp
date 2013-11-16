/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fusion.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author neto
 */
@Controller
@RequestMapping("/start")
public class ApplicationController {
 
    @RequestMapping(method = RequestMethod.GET)
	public String init(ModelMap model) {
       
		
         return "layouts/index";
 
	}
    
}
