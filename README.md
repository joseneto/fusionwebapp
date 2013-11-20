FusionWebApp
============

FusionWebApp is a set of Java Frameworks for Rapid Development of Enterprise Web Applications. It is a project fully configured and ready for you to start your own web applications.
Totally Customizable, it is a maven project built upon the following frameworks:

+ Jquery EasyUI
+ Sitemesh
+ Spring
+ ActiveJDBC
+ JasperReport

[![FusionWebApp Crud](http://imageshack.com/a/img191/8933/74hs.png "Click to see full size image")](http://imageshack.com/a/img191/8933/74hs.png)

Simple, fast, scalable. View layer using lightweight RESTful/json requests. Builds applications that conform to the MVC (Model-View-Controller) architectural paradigm. Start your Java project now!

## Getting Started

First, download Maven (http://maven.apache.org/download.cgi) and follow the installation instructions (http://maven.apache.org/download.cgi#Installation). After that, type the following in a terminal or in a command prompt:

```
$ mvn --version
```

Download the FusionWebApp and place in a folder of your choice, this will be your project folder. 

```
$ cd MyProjectFolder
$ mvn install
$ mvn package
```

Now your project can now be started!
With these commands you should have generated a WAR file. This is all you need to know to generate your project, now let's learn how FusionWebApp works.

##Database
FusionWebApp use the ActiveJDBC, what is a lightweight and fast Java ORM, its easy query with ActiveJDBC:

```
List<person> people = Person.where("name = 'John'");
Person aJohn = people.get(0);
String johnsLastName = aJohn.get("last_name");
```

Creating new records:

```
Person p = new Person();
p.set("name", "Marilyn");
p.set("last_name", "Monroe");
p.set("dob", "1935-12-06");
p.saveIt();
```

You can learn more about ActiveJDBC here: http://javalt.org/p/activejdbc

###Configure Database
FusionWebApp has two configuration, the first refers to data migration and is found in the maven **pom.xml**, if you want to use data migration then you need to change this file and put the properties pertaining to your database

    <properties>
       <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       <jdbc.url>jdbc:mysql://localhost:3306/mydb?characterEncoding=utf-8</jdbc.url>
       <jdbc.username>yourdatabaseuser</jdbc.username>
       <jdbc.password>yourdatabasepassword</jdbc.password>
    </properties>
    
    
The other configuration file is the main settings of the database and the application, for deafult is the **dev.properties**.

```
#Generate Config
app.package=com.fusion.app

#Datasource Config
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/mydb?characterEncoding=utf-8
jdbc.username=yourdatabaseuser
jdbc.password=yourdatabasepassword

#Locale Config
app.locale=en
```

In addition to the configuration database is possible to change the default package usable to generates code (scaffold) and internationalization locale.

###How to Migrate

FusionWebApp use the CarbonFive Plugin. To create a new migration, execute this command:

```
$ cd MyProjectFolder
$ mvn db-migration:new -Dname=create_people_table
```

this action would create an empty file in this localization:

```
$ MyProjectFolder\src\main\resources\db\migrations\20101112230703_create_people_table.sql
```
After that, add a sql code to the file:

    CREATE TABLE people (
      id  int(11) DEFAULT NULL auto_increment PRIMARY KEY,
      first_name VARCHAR(128),
      last_name VARCHAR(128),
      created_at DATETIME,
      updated_at DATETIME
    );

From the command line, you can run the migration plugin like this:

```
$ mvn db-migration:migrate
```

You can get more information about the CarbonFive migration here: https://code.google.com/p/c5-db-migration/wiki/MavenPlugin

##Environment

```
com.fusion.app
    controllers
    models
    services

WEB-INF/
    view/layouts/application.jsp
    spring-mvc-servlet.xml
            
resources/            
    db.migrations   
    dev.properties
    prod.properties
    messages_en.properties
    
```

With FusionWebApp is possible have many separate environments, by default we have two environments, prod.properties and dev.properties, you can choose between them in the file **spring-mvc-servlet.xml**:

```
<bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
    <property name="locations">
        <list>
            <value>classpath:dev.properties</value>
        </list>
    </property>
</bean>
```

###View
We chose to use Jquery EasyUI in our view layer, Jquery is commonly usable in many web projects, have a large library and a strong opensource community. Your project will be ready for Ajax, rest/json, HTML5 and much more!

We also used Sitemesh to manage the layouts, there is a single file **application.jsp** and all others views are rendered inside that file.

You can learn more about Jquery EasyUI here: http://www.jeasyui.com/

###Controller

We choose Spring why it help structure whole applications, productive manner, pulling together best-of-breed single-tier frameworks to create a coherent architecture, using standard RESTful: /list, /save/, /update/{id}, /destroy/{id}.

```
@RequestMapping(value = "/save", method = RequestMethod.POST)
public @ResponseBody Map<String,String> save(@RequestParam Map<String,String> params) {
 
  return personService.save(params);
 
}
```

You can learn more about Spring MVC here: http://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/mvc.html

###Scaffold

With FusionWebApp is possible to generate a complete CRUD code with *model, controller, service layer and view layer*. All code generated is easily customized, FusionWebApp comes with a crud (Person - people) built, simply run the migrate database to test it. 
To generate a CRUD type the following in a terminal or in a command prompt:

```
$ cd MyProjectFolder
$ mvn exec:java -Dexec.args="-m=Person"
```

Simple as that! The Generator read you table metadata, in this case people and create to you a complete crud, go to url /people and see it!.

![FusionWebApp Crud2](http://imageshack.com/a/img577/1306/sc0c.png "Click to see full size image")

Others arguments can be passed, to choose the desired table:

```
$ cd MyProjectFolder
$ mvn exec:java -Dexec.args="-m=Person -t=mytable"
```

or `-np` to no pluralize

```
$ cd MyProjectFolder
$ mvn exec:java -Dexec.args="-m=Person -np"
```

The code generator is free and opensource, it is in the package scaffold.generate, feel free to customize whenever you need.


##Why use FusionWebApp

There are several frameworks with the same proposal for rapid development, but FusionWebApp, uses what already exists and works in the market.
That way you can learn more and more, if you develop a project using FusionWebApp, your experience will be with Jquery, Spring, ActiveJDBC, Maven, JaspeReport and more.
Create incredible web applications and be ready for the market at the same time.
