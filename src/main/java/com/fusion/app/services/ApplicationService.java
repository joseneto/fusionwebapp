/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fusion.app.services;

import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.javalite.activejdbc.Errors;
import org.javalite.activejdbc.Model;

/**
 *
 * @author FusionWebApp Default Service
 */
public abstract class ApplicationService {
    
    public abstract Map<String, Object> paginate(int page, int rows);
    public abstract Map<String,String> save(Map<String,String> params);
    public abstract Map<String,String> update(Map<String,String> params);
    public abstract Map<String,String> destroy(Map<String,String> params);
    
    protected String handledModelErrors(Errors errors){
        
        StringBuilder errorsToView = new StringBuilder();        
        
       for (Object key : errors.keySet()) {
		errorsToView.append(key.toString() + ": "
			+ errors.get(key));
	}
      
       return errorsToView.toString();
    }
    
     protected String handledCommonErrors(Exception message){
    
       return message.toString();
    }
    
    
     public void reportImpl(List<Map> parameters, ServletContext context, HttpServletResponse response, String path, String filename) throws Exception{

        JasperDesign jd = JRXmlLoader.load(context.getRealPath("WEB-INF" + File.separator + path));
        JasperReport jr = JasperCompileManager.compileReport(jd);
    
        JRBeanCollectionDataSource list = new JRBeanCollectionDataSource(parameters);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jr, null, list);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
       
        OutputStream out = response.getOutputStream();
       
        out.write(JasperExportManager.exportReportToPdf(jasperPrint));
        out.flush();
        out.close();

    }
        
    
}
