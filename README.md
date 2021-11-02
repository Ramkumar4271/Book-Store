# Book-Store
Book Store

Created Book Store API using Spring Boot.
Backend connected with Postgres SQL.
Below Features are available in this API:
  1. Added Spring Web, Data JPA, PostgresSQL Driver, DevTools, Validation, Lombok, Security and JJWT.
  2. Added CRUD operations for Books Table which includes Filteration, Pagination and Sorting.
  3. Enabled Spring Security to access the Books by added Spring Security and JWT.
  4. Both Authentication and Authorization features are added.

NOTE:
  1. Inserted below rows into Roles table:
        insert into dev.roles values (1, 'ADMIN');
        insert into dev.roles values (2, 'USER');
        commit;
     

