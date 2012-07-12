Snippr
******

Before deploying this project you need to create an empty database. Default database is postgreSQL.

Create database
---------------

<code>
> CREATE USER snippr WITH PASSWORD 'snippr';
> CREATE DATABASE snipprdev;
> GRANT ALL PRIVILEGES ON DATABASE snipprdev TO snippr;
</code>

Compile & deploy
----------------

<code>
$ mvn clean install
$ mvn jetty:stop jetty:run
</code>

Open website at localhost:8080
