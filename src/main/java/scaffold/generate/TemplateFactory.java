/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scaffold.generate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author neto
 */
public class TemplateFactory {
    
    private String content;
    private String templatePath;
    private String outFilePath;
    private Map<String, String> replaceContent;
    
    public TemplateFactory(String templatePath_, String outFilePath_){
        templatePath = templatePath_;
        outFilePath = outFilePath_;
        replaceContent = new HashMap<String, String>();
    }
    
    
    public void addReplace(String oldString, String newString){
        replaceContent.put(oldString, newString);
    }
    
    public void build() throws FileNotFoundException, IOException{
        
       InputStream res = new FileInputStream(templatePath);
       content = new Scanner(res).useDelimiter("\\Z").next();
       
       for (Object key : replaceContent.keySet()) {
           content = content.replace(key.toString(), replaceContent.get(key));
	}
        FileOutputStream fop = null;
        File file;
        
        file = new File(outFilePath);
        file.getParentFile().mkdirs();
        fop = new FileOutputStream(file);

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        // get the content in bytes
        byte[] contentInBytes = content.getBytes();

        fop.write(contentInBytes);
        fop.flush();
        fop.close();
       
    }
    
}
