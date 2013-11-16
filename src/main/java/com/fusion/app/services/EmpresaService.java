package com.fusion.app.services;

import com.fusion.app.models.Empresa;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Auto Generate FusionWebApp Service
 */
@Service
public class EmpresaService extends ApplicationService {

    @Autowired
    private DataSource dataSource;

    @Override
    public Map<String, Object> paginate(int page, int rows) {

        Map<String, Object> restReturn = new HashMap();
        try {
            Base.open(dataSource);
            long total = Empresa.count();
            Paginator pages = new Paginator(Empresa.class, rows, "").orderBy("created_at asc");
            List<Empresa> listPage = pages.getPage(page);

            List<Map> list = new ArrayList<Map>();

            for (Empresa empresa : listPage) {
                list.add(empresa.toMap());
            }
            restReturn.put("rows", list);
            restReturn.put("total", total);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            Base.close();
        }

        return restReturn;

    }

    @Override
    public Map<String, String> save(Map<String, String> params) {

        Map message = new HashMap<String, String>();
        try {
            Base.open(dataSource);
  
            Empresa empresa = new Empresa();
            empresa.fromMap(params);
            if (empresa.save()) {
                message.put("success", "true");
            } else {
                message.put("errorMsg", handledModelErrors(empresa.errors()));
            }


        } catch (Exception e) {
            e.printStackTrace();
            message.put("errorMsg", handledCommonErrors(e));
        } finally {
            Base.close();
        }

        return message;
    }

    @Override
    public Map<String, String> update(Map<String, String> params) {
        Map message = new HashMap<String, String>();
        try {
            Base.open(dataSource);
            Empresa empresa = Empresa.findFirst("id = ?", params.get("id"));
            empresa.fromMap(params);
            if (empresa.save()) {
                message.put("success", "true");
            } else {
                message.put("errorMsg", handledModelErrors(empresa.errors()));
            }
        } catch (Exception e) {
            
            message.put("errorMsg", handledCommonErrors(e));
            e.printStackTrace();
         
        } finally {
            Base.close();
        }

        return message;
    }

    @Override
    public Map<String, String> destroy(Map<String, String> params) {

        Map message = new HashMap<String, String>();
        try {
            Base.open(dataSource);
            Empresa empresa = Empresa.findFirst("id = ?", params.get("id"));
            if (empresa.delete()) {
                message.put("success", "true");
            } else {
                message.put("errorMsg", handledModelErrors(empresa.errors()));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            message.put("errorMsg", handledCommonErrors(e));
        } finally {
            Base.close();
        }

        return message;
    }

    public void report(ServletContext context, HttpServletResponse response) {

        try {
            Base.open(dataSource);
            List<Map> parameters = Empresa.findAll().toMaps();
            reportImpl(parameters, context, response, "views"+ File.separator + "empresas" + File.separator + "report.jrxml", "empresas_list");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Base.close();
        }

    }
}