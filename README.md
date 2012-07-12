Snippr
******

Before deploying this project you need to create an empty database. Default database is postgreSQL.

Create database
---------------

<pre>
> CREATE USER snippr WITH PASSWORD 'snippr';
> CREATE DATABASE snipprdev;
> GRANT ALL PRIVILEGES ON DATABASE snipprdev TO snippr;
</pre>


Compile & deploy
----------------

<pre>
$ mvn clean install
$ mvn jetty:stop jetty:run
</pre>

Open website at localhost:8080
