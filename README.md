## Problem Statement ##
A lot of people have turned to Etsy to buy/sell their custom crafts. The problem with Etsy is that
they consume a portion of your sales and charge you to post your products with a monthly fee.
Custom Crafts will allow crafters to sell for no charge at all. Custom Crafts also includes 
a small accounting assistant to document all sales and customers may choose to enter their costs
to see a total revenue amount. 

### Design ###
* [User Stories](DesignDocuments/userStories.md)
* [Project Plan](/ProjectPlan.md)
* [Database Design](DesignDocuments/databaseDesign.png)

## Project Technologies ##
- Database:
    - MySQL
    - Store users, products, and orders
- ORM Framework
    - Hibernate 5
- Dependency Management
    - Maven
- CSS
    - Bootstrap likely?
- Data Validation
    - Bootstrap validator for front end
    - Explore Hibernate's validation
- Logging
    - Log4J2
- Hosting
    - AWS
- Unit Testing
    - JUnit tests to achieve 80%+ code coverage
- IDE: IntelliJ IDEA
- API
    - sales tax calculator ?