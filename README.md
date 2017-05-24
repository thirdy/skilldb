# SkillDB

http://www.javavids.com/group-detail.xhtml?name=jsf-primefaces-spring-website-monitoring

Additional manual dependency setup: 

 1. Download the [primefaces all themese jar](http://repository.primefaces.org/org/primefaces/themes/all-themes/1.0.10/all-themes-1.0.10.jar).
 2. mvn install:install-file -Dfile=all-themes-1.0.10.jar -DgroupId=org.primefaces.themes -DartifactId=all-themes -Dversion=1.0.10 -Dpackaging=jar
 3. mvn clean install

To setup in Eclipse:

 1. Download lombok jar from https://projectlombok.org/download.html
 2. Install lombok into your Eclipse
 3. Clone this project and Import Existing Maven project
 4. If any errors, do a maven update project or rebuild project
 5. To Run, run maven: mvn jetty:run  