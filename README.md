FusionWebApp
============

FusionwebApp is a set of Java Frameworks for Rapid Development of Enterprise Web Applications. It is a project fully configured and ready for you to start your own web applications.
And the best part is that FusionWebApp is completely customizable, it is a maven project built upon the following frameworks:

+ Jquery EasyUI
+ Sitemesh
+ Spring
+ ActiveJDBC
+ JasperReport

![FusionWebApp Crud](http://imageshack.com/a/img191/8933/74hs.png)

It is simple, requires minimal configuration, is fast, is scalable, view layer using lightweight requests RESTful/json, standardized on the MVC. Is Java!

## Getting Started

First, download Maven (http://maven.apache.org/download.cgi) and follow the installation instructions (http://maven.apache.org/download.cgi#Installation). After that, type the following in a terminal or in a command prompt:

```
$ mvn --version
```

Download the fusionwebapp and place in a folder of your choice, this will be your project folder. 

```
$ cd MyProjectFolder
$ mvn install
$ mvn package
```

Now your project can now be started!
With these commands you should have generated a WAR file relating to your project. This is all you need to know to generate your project, now let's learn how to configure it

##Database
FusionWebApp use the ActiveJdbc, what is a lightweight and fast Java ORM, its easy query with ActiveJDBC:

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
FusionWebApp has two configuration files to the database, the first refers to data migration and is found in **pom.xml**, if you want to use data migration then you need to change this file and put the properties pertaining to your database

    <properties>
       <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       <jdbc.url>jdbc:mysql://localhost:3306/mydb?characterEncoding=utf-8</jdbc.url>
       <jdbc.username>yourdatabaseuser</jdbc.username>
       <jdbc.password>yourdatabasepassword</jdbc.password>
    </properties>
    
    
The other configuration file is a type of properties, the main settings of the database and the application are there.

```
#Generate Config
app.package=com.fusion.app

#Datasource Config
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/fusiondb?characterEncoding=utf-8
jdbc.username=root
jdbc.password=root

#Locale Config
app.locale=en
```

In addition to the configuration database is possible to change the default package that generates code scaffold and internationalization.

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

With FusionWebApp is possible to develop several separate environments, by default we have two environments, prod.properties dev.properties and you can choose between them in the file **spring-mvc-servlet.xml**:

```
<bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
    <property name="locations">
        <list>
            <value>classpath:dev.properties</value>
        </list>
    </property>
</bean>
```

###Controller

We use spring-mvc for managing requests, using standard RESTful: /list, /save/, /update/{id}, /destroy/{id}.

```
@RequestMapping(value = "/save", method = RequestMethod.POST)
public @ResponseBody Map<String,String> save(@RequestParam Map<String,String> params, ModelMap model) {
 
  return personService.save(params);
 
}
```

You can learn more about Spring MVC here: http://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/mvc.html

###Scaffold

With FusionWebApp is possible to generate a CRUD code complete with Model, Controller, Service and View. This feature streamlines the development of various parts of a project, all code generated is easily customized, FusionWebApp comes with a crud (Person - people), simply run the migrete database to test. To generate a CRUD type the following in a terminal or in a command prompt:

```
$ cd MyProjectFolder
$ mvn exec:java -Dexec.args="-m=Person"
```

Simple as that! Our Generator read you table metadata, in this case people and create to you a complete crud, go to url /people and see it!.

![FusionWebApp Crud2](http://imageshack.com/a/img577/1306/sc0c.png)

It is also possible to send as parameter the desired table, or to inform that the generator does not pluralize the model.

-t for table

```
$ cd MyProjectFolder
$ mvn exec:java -Dexec.args="-m=Person -t=mytable"
```

or -np to no pluralize

```
$ cd MyProjectFolder
$ mvn exec:java -Dexec.args="-m=Person -np"
```

The code generator is free and is in the package scaffold.generate, feel free to customize it on your need.


##Why use FusionWebApp

There are several frameworks with the same proposal of agile development, which brings FusionWebApp novelty is freedom within your project, because it is built on everything that is being used more.
Here you increase your good skills, change that is needed and has complete mastery of your project. FusionWebApp does the hard work that the entire setup is based on best practices for development and also productivity of Ruby on Rails.
