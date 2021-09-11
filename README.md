
# EGS Assignment Task

This is a simple Shopping Application. Check the `./docs/Technical-Task-Java.pdf` for more information about their requirements.

## Getting Started

### Dependencies

* Java 11 JDK
* I used maven to handle application dependency, but you don't need Maven to build this app, you can easily use the Maven wrapper instance of installing Maven.
* For the dev environment I used H2 Database but for the prod environment, you need to provide PostgreSQL. You can find a simple docker-compose file of PostgreSQL under the `./docker/` directory for using it on the dev environment, but as I mentioned you don't need it.

### Runing

* Under root directory of the project run
	`./mvnw` or `mvnw.cmd` or `mvn`
* After successful run, go to http://localhost:8080 , you can see Swagger UI and start playing with it!

### Create FatJar

* Under root directory of the project run
	`./mvnw clean install` or `mvnw.cmd clean install` or `mvn clean install`

## Authors

Contributors names and contact info

 - [Hojjat Abedi](https://github.com/GLinBoy)

## Version History

* 1.0.4
	* Add This documents and archive project :)
* 1.0.3
	* Replace [spring-search](https://github.com/sipios/spring-search) with [rsql-parser](https://github.com/jirutka/rsql-parser)
	* Solved issue of [problem library](https://github.com/zalando/problem-spring-web)
* 1.0.1
    * Added Token field to Swagger UI
* 1.0.0
    * Initial Release

## License

This project is licensed under the MIT License - see the LICENSE.md file for details

## Acknowledgments

Last email that I share with them about tasks and how I finished them:
```
 1. User sign-in signup. The application should have 2 types of roles, ADMIN, and USER. **DONE**
 2. Admin functionality -> CRUD of products per category, block unblock the users. **Done**
 3. -   Users should be able to search for products per product name, price range, rate, etc. **Done**
	a.I used [https://github.com/sipios/spring-search](https://github.com/sipios/spring-search) to providing search functionality; This library helps me to use Spring Specification and provide search on APIs; Please check its docs to try product search;
 4. Users should be able to rate the product(1-5), leave a comment on it. **Done**
 5. Please use PostgreSQL or MySQL as RDBMS. Usage of Spring Data + JPA + Hibernate is preferable. **Done**
 6. UI is optional, only web service + postman collection for all possible requests is enough. **Done**
 7. Please push your work on GitLab, GitHub, or Bitbucket. **Done**
```
