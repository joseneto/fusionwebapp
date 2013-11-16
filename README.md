FusionWebApp
============

FusionwebApp is a set of Java Frameworks for Rapid Development of Enterprise Web Applications. It is a project fully configured and ready for you to start your own web applications.
And the best part is that FusionWebApp is completely customizable, it is a maven project built upon the following frameworks:

#####Jquery-EasyUi
#####Sitemesh
#####Spring
#####ActiveJdbc
#####JasperReport

### Getting Started

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

###Configuring the Database
FusionWebApp has two configuration files to the database, the first refers to data migration and is found in *pom.xml*, if you want to use data migration then you need to change this file and put the properties pertaining to your database

    <properties>
       <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       <jdbc.url>jdbc:mysql://localhost:3306/mydb?characterEncoding=utf-8</jdbc.url>
       <jdbc.username>yourdatabaseuser</jdbc.username>
       <jdbc.password>yourdatabasepassword</jdbc.password>
    </properties>

### Designer Templates
We've crafted some handsome templates for you to use. Go ahead and continue to layouts to browse through them. You can easily go back to edit your page before publishing. After publishing your page, you can revisit the page generator and switch to another theme. Your Page content will be preserved if it remained markdown format.

### Rather Drive Stick?
If you prefer to not use the automatic generator, push a branch named `gh-pages` to your repository to create a page manually. In addition to supporting regular HTML content, GitHub Pages support Jekyll, a simple, blog aware static site generator written by our own Tom Preston-Werner. Jekyll makes it easy to create site-wide headers and footers without having to copy them across every page. It also offers intelligent blog support and other advanced templating features.

### Authors and Contributors
You can @mention a GitHub username to generate a link to their profile. The resulting `<a>` element will link to the contributor's GitHub Profile. For example: In 2007, Chris Wanstrath (@defunkt), PJ Hyett (@pjhyett), and Tom Preston-Werner (@mojombo) founded GitHub.

### Support or Contact
Having trouble with Pages? Check out the documentation at http://help.github.com/pages or contact support@github.com and we’ll help you sort it out.
