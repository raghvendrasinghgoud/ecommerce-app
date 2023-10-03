Problem Statement

Develop an ecommerce product management utility. This utility begins by presenting a login screen that looks like this






Admin user would enter a username and password for authentication. Upon clicking the Login button, the submitted username / password should be verified (authenticated) against existing users' information in the database.
Upon successful authentication, the user would be presented with a screen as follows:












User should be allowed to edit (change the added product’s attributes viz. title, quantity, size, image) and delete the product and total size of the uploaded products should be displayed at the bottom of the listing on the same page.


Expected Behavior and Output


Constraints
Max size of a single product’s uploaded image should be 1 MB
Max size of all uploaded product’s images should be 10 MB
Key Points
Design appropriate data model and object model 
Proper validation and info messages should be shown on console wherever required
Exception Handling is required
Make use of Hibernate Technology for Data Base connectivity
While writing APIs no SQL scripts should be used instead use Hibernate POJOs and HQL
Tomcat version 8 or above and hibernate 5.x should be used.


