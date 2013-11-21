package scaffold.generate;



import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.javalite.activejdbc.Base;
import org.xml.sax.SAXException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author neto
 */
public class Generate {
    
    public static void main(String[] args) throws SQLException{
        
        //Definations
        String model = null;
        String definationModel = null;
        String tableName = null;
        boolean pluralize = true;
        
        //Check Arguments
        if (args.length == 0){
            throw new IllegalArgumentException("Incorrect arguments: 0"); 
          
        }else{
            
            for (String argument : args) {
                
                if(argument.indexOf("-m") != -1){
                    model = argument.replace("-m=", "");
                }
                
                if(argument.indexOf("-t") != -1){
                    tableName = argument.replace("-t=", "");
                }
                
                if(argument.indexOf("-np") != -1){
                    pluralize = false;
                }
                
            }
            
            if(model == null){
               throw new IllegalArgumentException("Please set a model argument: -m=MyModel");  
            }
            
            if(pluralize){
                definationModel = Inflector.pluralize(model.toLowerCase());
            }else{
                definationModel = model.toLowerCase();
            }
            
            if (tableName == null){
                tableName = definationModel;
            }
          
        }
        
        //Start building scaffold
        TemplateUtil util = new TemplateUtil();
        TemplateFactory modelFactory;
        TemplateFactory controllerFactory;
        TemplateFactory serviceFactory;
        TemplateFactory indexFactory;
        TemplateFactory formFactory;
        TemplateFactory reportFactory;
        
        String dirApp = System.getProperty("user.dir"); 
        String dirTemplate = File.separator + "src"+File.separator+"main"+File.separator+"resources"+File.separator+"scaffold"+File.separator+"generate"+File.separator+"templates"+File.separator;
        String dirSource = File.separator + "src"+ File.separator +"main"+ File.separator +"java" + File.separator;
        String dirView = File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "WEB-INF" + File.separator + "views" + File.separator;
        
        
        try {
            util.readProperties(util.readSpringXML());
            String outFilePath = (System.getProperty("app.package").replace(".", File.separator) + File.separator);
            String fDate = System.getProperty("app.format.date");
            String fDateTime = System.getProperty("app.format.datetime");
            
            Base.open(System.getProperty("jdbc.driver"), System.getProperty("jdbc.url"), 
                    System.getProperty("jdbc.username"), System.getProperty("jdbc.password"));
            
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("--------------------------FUSIONWEBAPP SCAFFOLD-------------------------------");
            System.out.println("------------------------------------------------------------------------------");
            
            System.out.println("Reading database table.");
            PreparedStatement statement = Base.connection().prepareStatement("select * from " + tableName);
            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            //Content Variables
            StringBuilder indexContent = new StringBuilder();
            StringBuilder formContent = new StringBuilder();
            StringBuilder modelContent = new StringBuilder();
            StringBuilder modelStaticContent = new StringBuilder();
            StringBuilder modelBeforeContent = new StringBuilder();
            StringBuilder modelTableNameContent = new StringBuilder();
            
            //Report variables
            String header1 = null;
            String header2 = null;
            String attr1 = null;
            String attr2 = null;
            
            System.out.println("Reading metadata table.");
             for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                 int type = rsmd.getColumnType(i);
                 String cname = rsmd.getColumnName(i);
                 if(!cname.equals("id") ){
                     
                     if(header1 == null){
                         header1 = util.capitalize(cname); 
                         attr1 = cname;
                     }else if(header2 == null){
                         header2 = util.capitalize(cname); 
                         attr2 = cname;
                     }
                     
                     if (type == java.sql.Types.DATE){
                        indexContent.append("<th field=\""+cname+"\" width=\"50\" data-options=\"formatter:formatDate\">"+util.capitalize(cname)+"</th>\n");  
                     }else if(type == java.sql.Types.TIMESTAMP){
                        indexContent.append("<th field=\""+cname+"\" width=\"50\" data-options=\"formatter:formatDateTime\">"+util.capitalize(cname)+"</th>\n"); 
                     }else{
                        indexContent.append("<th field=\""+cname+"\" width=\"50\" >"+util.capitalize(cname)+"</th>\n");
                     }
                    
                     
                      if(!cname.equals("created_at") && !cname.equals("updated_at")){
                          formContent.append("<div class=\"fitem\">\n");
                          formContent.append("<label>" + util.capitalize(cname) + ":</label>\n");
                          switch (type) {
                              case (java.sql.Types.INTEGER):
                                  formContent.append("<input name=\"" + cname + "\" class=\"easyui-numberbox\" type=\"text\">\n");
                                  break;
                              case (java.sql.Types.BOOLEAN):
                                  formContent.append("<input name=\"" + cname + "\" type=\"checkbox\" value=\"true\">\n");
                                  formContent.append("<input name=\"" + cname + "\" type=\"hidden\" value=\"false\">\n");
                                  modelBeforeContent.append("   if (getString(\""+ cname +"\").equals(\"true\")) {set(\""+ cname +"\", 1);} else {set(\""+ cname +"\", 0);}\n");
                                  break;
                              case (java.sql.Types.BIT):
                                  formContent.append("<input name=\"" + cname + "\" type=\"checkbox\" value=\"true\">\n");
                                  formContent.append("<input name=\"" + cname + "\" type=\"hidden\" value=\"false\">\n");
                                  modelBeforeContent.append("   if (getString(\""+ cname +"\").equals(\"true\")) {set(\""+ cname +"\", 1);} else {set(\""+ cname +"\", 0);}\n");
                                  break;
                              case (java.sql.Types.DATE):
                                  formContent.append("<input name=\"" + cname + "\" class=\"easyui-datebox\" data-options=\"parser:parseDate,formatter:formatInputDate\" type=\"text\">\n");
                                   modelStaticContent.append("  convertTimestamp(\""+ cname +"\", \""+fDate+"\");\n");
                                  break;
                              case (java.sql.Types.TIMESTAMP):
                                  formContent.append("<input name=\"" + cname + "\" class=\"easyui-datetimebox\" data-options=\"parser:parseDateTime,formatter:formatInputDateTime\" type=\"text\">\n");
                                  modelStaticContent.append("   convertTimestamp(\""+ cname +"\", \""+fDateTime+"\");\n");
                                  break;
                              case (java.sql.Types.TIME):
                                  formContent.append("<input name=\"" + cname + "\" class=\"easyui-timespinner\" type=\"text\">\n");
                                   modelStaticContent.append("  convertTimestamp(\""+ cname +"\", \"HH:mm:ss\");");
                                  break;
                              default:
                                  formContent.append("<input name=\"" + cname + "\" type=\"text\">\n");
                          }
                        
                          formContent.append("</div>");
                      }
                     
                 }
                 
             }
            
            rs.close();
            Base.close();
            
            if(!tableName.equals(definationModel)){
                modelTableNameContent.append("@Table(\""+tableName+"\") ");
            }
            
            if (modelStaticContent.length() > 0){
                
                modelContent.append("static{\n");
                modelContent.append(modelStaticContent.toString());
                modelContent.append("   }\n\n");
            }
            
              if (modelBeforeContent.length() > 0){
                
                modelContent.append("public void beforeSave(){\n");
                modelContent.append(modelBeforeContent.toString());
                modelContent.append("}\n");
            }
            
            //Building templates
            System.out.println("Building model.");
            modelFactory = new TemplateFactory(dirApp + dirTemplate + "Model.java_", dirApp + dirSource + outFilePath + "models" +File.separator + model +".java");
            modelFactory.addReplace(TemplateUtil.PACKAGE, System.getProperty("app.package"));
            modelFactory.addReplace(TemplateUtil.UPPER_MODEL, model);
            modelFactory.addReplace(TemplateUtil.MODEL_CONVERTES, modelContent.toString());
            if (modelTableNameContent.length() > 0) {
                modelFactory.addReplace(TemplateUtil.TABLE_NAME, modelTableNameContent.toString());
                modelFactory.addReplace(TemplateUtil.TABLE_NAME_PKG, "import org.javalite.activejdbc.annotations.Table;");
            } else {
                modelFactory.addReplace(TemplateUtil.TABLE_NAME, "");
                modelFactory.addReplace(TemplateUtil.TABLE_NAME_PKG, "");
            }
            
            modelFactory.build();
            
            System.out.println("Building controller.");
            controllerFactory = new TemplateFactory(dirApp + dirTemplate +"ModelController.java_", dirApp + dirSource + outFilePath + "controllers" +File.separator + model +"Controller.java");
            controllerFactory.addReplace(TemplateUtil.PACKAGE, System.getProperty("app.package"));
            controllerFactory.addReplace(TemplateUtil.UPPER_MODEL, model);
            controllerFactory.addReplace(TemplateUtil.DOWN_MODEL, model.toLowerCase());
            controllerFactory.addReplace(TemplateUtil.PLURAL_DOWN_MODEL, definationModel);
            controllerFactory.build();
            
            System.out.println("Building service.");
            serviceFactory = new TemplateFactory(dirApp + dirTemplate +"ModelService.java_", dirApp + dirSource + outFilePath + "services" + File.separator + model + "Service.java");
            serviceFactory.addReplace(TemplateUtil.PACKAGE, System.getProperty("app.package"));
            serviceFactory.addReplace(TemplateUtil.DOWN_MODEL, model.toLowerCase());
            serviceFactory.addReplace(TemplateUtil.UPPER_MODEL, model);
            serviceFactory.addReplace(TemplateUtil.PLURAL_DOWN_MODEL, definationModel);
            serviceFactory.build();
            
            System.out.println("Building index view.");
            indexFactory = new TemplateFactory(dirApp + dirTemplate + "index.jsp_", dirApp + dirView + definationModel +File.separator + "index.jsp");
            indexFactory.addReplace(TemplateUtil.GENERATE_TABLE, indexContent.toString());
            indexFactory.addReplace(TemplateUtil.UPPER_MODEL, model);
            indexFactory.addReplace(TemplateUtil.DOWN_MODEL, model.toLowerCase());
            indexFactory.addReplace(TemplateUtil.PLURAL_DOWN_MODEL, definationModel);
            indexFactory.build();
            
            System.out.println("Building form view.");
            formFactory = new TemplateFactory(dirApp + dirTemplate + "form.jsp_", dirApp + dirView + definationModel +File.separator + "form.jsp");
            formFactory.addReplace(TemplateUtil.GENERATE_FORM, formContent.toString());
            formFactory.addReplace(TemplateUtil.UPPER_MODEL, model);
            formFactory.addReplace(TemplateUtil.DOWN_MODEL, model.toLowerCase());
            formFactory.addReplace(TemplateUtil.PLURAL_DOWN_MODEL, definationModel);
            formFactory.build();
            
            System.out.println("Building report.");
            reportFactory = new TemplateFactory(dirApp + dirTemplate + "report.jrxml_", dirApp + dirView + definationModel +File.separator + "report.jrxml");
            reportFactory.addReplace(TemplateUtil.UPPER_MODEL, model);
            reportFactory.addReplace(TemplateUtil.REPORT_HEADER1, header1);
            reportFactory.addReplace(TemplateUtil.REPORT_HEADER2, header2);
            reportFactory.addReplace(TemplateUtil.REPORT_ATTR1, attr1);
            reportFactory.addReplace(TemplateUtil.REPORT_ATTR2, attr2);
            reportFactory.build();
            
            System.out.println("--------------------------------FINISHED---------------------------------------");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Generate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Generate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Generate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
}

