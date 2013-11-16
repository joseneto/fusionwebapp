
package com.fusion.app.controllers;

import com.fusion.app.services.EmpresaService;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Auto Generate FusionWebApp Controller
 */
@Controller
@RequestMapping("/empresas")
public class EmpresaController {
    
    @Autowired
    private ServletContext context;
    @Autowired
    private EmpresaService empresaService;
   
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
       
        return "empresas/index";

    }
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody  Map<String, Object> list(@RequestParam Map<String,String> params, ModelMap model) {
     
        return  empresaService.paginate(Integer.valueOf(params.get("page")), Integer.valueOf(params.get("rows")));

    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> save(@RequestParam Map<String,String> params, ModelMap model) {
     
      return empresaService.save(params);
     
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String id, @RequestParam Map<String,String> params, ModelMap model) {
      params.put("id", id);
      return empresaService.update(params);
      
    }
     
    @RequestMapping(value = "/destroy/{id}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> destroy(@PathVariable String id, @RequestParam Map<String,String> params, ModelMap model) {
       params.put("id", id); 
      return empresaService.destroy(params);
    }
    
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public void report(@RequestParam Map<String,String> params, HttpServletResponse response) {
            
        empresaService.report(context, response);

     }
    
    
}