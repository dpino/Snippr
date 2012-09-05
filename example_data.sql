insert into users (id, first_name, last_name, user_name, password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked) values
(1, NULL, NULL,  'test',    'test', NULL,  TRUE,   TRUE,   TRUE,   TRUE),
(2,  'José Manuel', 'Ciges Regueiro',  'ciges',   'ciges',   'jmanuel@ciges.net',  TRUE,  TRUE,   TRUE,   TRUE);

insert into label (id, name, user_id) values
(1, 'Etiqueta 1',  1),
(2,   'Etiqueta 2',  1),
(3,   'Etiqueta 3',  2);


insert into snippet (id, title, description, label_id, user_id) values
(2,   'Ejecución de una aplicación web Java con Jetty',  'Comandos de Maven y Jetty para compilar y ejecutar una aplicación web Java',  1,   1),
(1,   'Comandos prácticos de PostgreSQL',    'Recetas útiles para novatos con PostgreSQL',  1,   1),
(5,   'Prueba',  'Descripción de prueba',   2,   2),
(12,  'New Snippet, please fill it.',    'Add a description here.', 3,   2);

insert into roles (id, role_name) values
(1,   'ROLE_USER');

insert into snippet_code (id, code, snippet_id) values
(2,   '* Compilación e instalación\n\nmvn clean\nmvn install -Ppostgresql\n\n* Lanzamiento de Jetty\n\nmvn jetty:stop jetty:run',    2),
(1,   '* Conectarse a la base de datos local snipprdev con el usuario snippr\n\npsql -h localhost -U snippr -W -d snipprdev\n\n* Conectarse al servidor PostgreSQL como administrador\n\nsudo sh\nsu – postgres\npsql database username\n\n    Mostrar las bases de datos\n\n\\l\n\n    Mostrar las tablas de la base de datos\n\n\\dt\n\n    Mostrar la descripción de la tabla users\n\n\\dS+ users',  1),
(8,   'Code example for prueba Snippet', 5);

insert into users_roles (user_id, role_id) values
(1,   1);
