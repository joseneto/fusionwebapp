FusionWebApp
============

FusionwebApp is a set of Java Frameworks for Rapid Development of Enterprise Web Applications. It is a project fully configured and ready for you to start your own web applications.
And the best part is that FusionWebApp is completely customizable, it is a maven project built upon the following frameworks:

+ Jquery-EasyUi
+ Sitemesh
+ Spring
+ ActiveJdbc
+ JasperReport

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

##Configuring the Database
FusionWebApp has two configuration files to the database, the first refers to data migration and is found in **pom.xml**, if you want to use data migration then you need to change this file and put the properties pertaining to your database

    <properties>
       <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       <jdbc.url>jdbc:mysql://localhost:3306/mydb?characterEncoding=utf-8</jdbc.url>
       <jdbc.username>yourdatabaseuser</jdbc.username>
       <jdbc.password>yourdatabasepassword</jdbc.password>
    </properties>

###How to Migrate

To create a new migration, execute this command:

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

You can get more information about the carbon five migration here: https://code.google.com/p/c5-db-migration/wiki/MavenPlugin

