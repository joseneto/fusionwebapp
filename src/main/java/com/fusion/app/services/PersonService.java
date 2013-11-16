package com.fusion.app.services;

import com.fusion.app.models.Person;
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
public class PersonService extends ApplicationService {

    @Autowired
    private DataSource dataSource;

    @Override
    public Map<String, Object> paginate(int page, int rows) {

        Map<String, Object> restReturn = new HashMap();
        try {
            Base.open(dataSource);
            long total = Person.count();
            Paginator pages = new Paginator(Person.class, rows, "").orderBy("created_at asc");
            List<Person> people = pages.getPage(page);

            List<Map> lista = new ArrayList<Map>();

            for (Person person : people) {
                lista.add(person.toMap());
            }
            restReturn.put("rows", lista);
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
  
            Person person = new Person();
            person.fromMap(params);
         
            if (person.save()) {
                message.put("success", "true");
            } else {
                message.put("errorMsg", handledModelErrors(person.errors()));
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
            Person person = Person.findFirst("id = ?", params.get("id"));
            person.fromMap(params);
            if (person.save()) {
                message.put("success", "true");
            } else {
                message.put("errorMsg", handledModelErrors(person.errors()));
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
            Person person = Person.findFirst("id = ?", params.get("id"));
            if (person.delete()) {
                message.put("success", "true");
            } else {
                message.put("errorMsg", handledModelErrors(person.errors()));
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
            List<Map> parameters = Person.findAll().toMaps();
            reportImpl(parameters, context ,response, "views/people/report.jrxml", "people_list");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Base.close();
        }

    }
}
