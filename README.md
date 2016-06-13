# RandomDataGenerator
Project makes random data from object relation model (ORM) classes with possibility for custom random value range,
filtering and sorting data. Exporting data to some type of file or jasper report. Way to show generated data. It is 
really useful when you need to test something and you need specific random field values from some object. You 
don't need to create data in database. All you need to do is to take class from witch you need data and setup specific 
range for property random values. For example there is possibility to fill empty database with random data generated 
from this project.

Check out TestDataUtil class for examples how to set up and use it.

To set limit for special property look at boundary.properties file. Without this settings always will be used maximum
or minimum value for given type.

There is some example of usage of this project in project SpringHibernate check it out.



# SpringHibernate
Spring, Hibernate, Bootstrap, Maven, JSP, MySql, RandomDataGenerator.

Example of spring, hibernate with random data generation.

Random data generator is added for generating random data, exporting. At the moment supported generating random
data is from ORM classes and predefined fields with output in JSON. GUI validation for fields in JSON random data
generation is not done yet. So there it is possible to have some errors if you don't create them correctly. There is
example for every type in sql script createDB.sql. If you create fields like that it will work.

GUI will be updated as soon as I have free time.

