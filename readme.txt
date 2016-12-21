#Allure + TestNG project

#We have two pom.xml
pom.xml 				: pom contains jetty implementation
pom_without_jetty.xml  	: pom without jetty implementation 


CASE 1: jetty code (pom.xml)
#To run cases
mvn clean test site   
mvn jetty:run -Djetty.port=8090	 | generate report on jetty server  | http://localhost:8090/#/behaviors
open http://localhost:8090/#/    | to view report


#To find report locally:
/AllureTestNG/target/site/allure-maven-plugin/index.html   | report with new pom containing jetty code | refresh report if not updated automatically



CASE 2: normal code w/o jetty (pom_without_jetty.xml)

#To run cases with pom_without_jetty.xml
mvn clean test site     				| generate report in local


#To find report locally:
/AllureTestNG/site/allure-maven-plugin/index.html    | report path with pom_without_jetty.xml |  imp: refresh every-time after opening if not auto refresh



#Imp Links:
http://www.perfect-test.com/index.php/en/technologies-menu-en/technologies-other-menu-eng/38-allure-report-eng


#todo:
image attach: open site > take screenshot > save it > attach that file in tets case   			 - pending, no very imp
integrate jetty server, so that report get saved in that server, so that other can see report 	 - done

#Why to implement jetty server
Any body can see the report just by visiting http://localhost:8090/#/
We need to host Jenkin on any server where Jenkin can run code(from git) and email can be sent to people containing jetty server link to view report

















