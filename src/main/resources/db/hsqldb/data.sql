-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One owner user, named laucasort with password owner
INSERT INTO users(username,password,enabled) VALUES ('laucasort','owner',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'laucasort','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet2','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet2','veterinarian');


INSERT INTO users(username,password,enabled) VALUES ('diegillos','ete sech',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'diegillos','owner');

INSERT INTO users(username,password,enabled) VALUES ('gonlalle','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'gonlalle','owner');

INSERT INTO users(username,password,enabled) VALUES ('bencrealc','elpepe',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (16,'bencrealc','owner');

INSERT INTO users(username,password,enabled) VALUES ('javgutfal','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (17,'javgutfal','owner');

INSERT INTO users(username,password,enabled) VALUES ('blamaurob','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (18,'blamaurob','owner');


INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (13, 'Laura', 'Castillo', '2335 Independence La.', 'Sevilla', '6085555487', 'owner1');
INSERT INTO owners VALUES (14, 'Diego', 'Gil', '2335 Independence La.', 'Sevilla', '6085555488', 'owner1');
INSERT INTO owners VALUES (15, 'Gonzalo', 'Lallena', '2335 Independence La.', 'Sevilla', '6085555488', 'owner1');
INSERT INTO owners VALUES (16, 'Benjamin', 'Crespo', 'Calle Falsa 123', 'Sevilla', '6085555488', 'owner1');
INSERT INTO owners VALUES (17, 'Javier', 'Gutiérrez', 'Calle UWU 123', 'Sevilla', '6085555488', 'owner1');
INSERT INTO owners VALUES (18, 'Blanca', 'Mauri', '2335 Independence La.', 'Sevilla', '6085555488', 'owner1');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Xena', '2012-06-08', 2, 13);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Sif', '2012-06-08', 2, 14);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'Sif2', '2012-06-08', 2, 15);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (17, 'Coco', '2019-06-08', 5, 16);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (18, 'Cocos', '2019-06-08', 5, 17);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (19, 'Juanita', '2007-06-03', 2, 18);


INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');


INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,saques_acertados,saques_totales,porcentaje_saques) VALUES (1,'javgutfal','11111111A','Alberto','Clemente','1998-11-06','Calle Arrastre','arribaespaña@gmail.com','Villaviciosa',178,78,75,22.2,50,70,0.71);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (2,'gonlalle','11111112A','Paco','Tuerca','2004-11-06','Calle Demacracion','cataluñaindependiente@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (3,'javgutfal','11111113A','Paco','Tuerca','2004-11-06','Calle Demacracion','covidgod19@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (4,'javgutfal','11111114A','Paco','Tuerca','2004-11-06','Calle Demacracion','arsatratra@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (5,'javgutfal','11111115A','Paco','Tuerca','2004-11-06','Calle Demacracion','ojitoconeldato@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (6,'javgutfal','11111116A','Paco','Tuerca','2004-11-06','Calle Demacracion','otakumuertoabonopamihuerto@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (7,'javgutfal','11111117A','Paco','Tuerca','2004-11-06','Calle Demacracion','medijistequemecomprariasflores@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (8,'javgutfal','11111118A','Paco','Tuerca','2004-11-06','Calle Demacracion','siespañafueraundonutmadridnoexistiria@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (9,'javgutfal','11111119A','Paco','Tuerca','2004-11-06','Calle Demacracion','correoparacosasilegales52@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (10,'javgutfal','11111110A','Paco','Tuerca','2004-11-06','Calle Demacracion','barpepepedidos@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (11,'javgutfal','11111120A','Paco','Tuerca','2004-11-06','Calle Demacracion','sinomirasnoesta@gmail.com','Guarroman',175,78,75,23.2);

INSERT INTO equipos(id,categoria,federacion) VALUES (1,'Senior',false);
INSERT INTO equipos(id,categoria,federacion) VALUES (2,'Cadete',false);


INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento) VALUES (1,'2020-11-06','17:00',5);
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento) VALUES (2,'2020-11-06','18:00',5);
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento) VALUES (3,'2020-11-06','19:00',5);
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento) VALUES (4,'2020-12-06','17:00',5);
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento) VALUES (5,'2020-10-06','18:00',5);
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento) VALUES (6,'2020-11-07','19:00',5);

INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,jugador_id,partido_id) VALUES (1,20,20,1,10,20,0.5,5,20,0.25,5,20,0.25,1,1);
INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,jugador_id,partido_id) VALUES (2,12,18,0.6,10,20,0.5,5,20,0.25,5,20,0.25,1,2); 
INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,jugador_id,partido_id) VALUES (3,14,22,0.7,10,20,0.5,15,20,0.75,5,20,0.25,1,3);
INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,jugador_id,partido_id) VALUES (4,10,25,0.4,10,20,0.5,5,20,0.25,15,20,0.75,1,4);
INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,jugador_id,partido_id) VALUES (5,12,14,0.7,15,20,0.75,5,20,0.25,5,20,0.25,1,5); 
INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,jugador_id,partido_id) VALUES (6,14,22,0.63,5,20,0.25,5,20,0.25,5,20,0.25,1,6); 


INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (1,3,'2020-11-06','SALTOVERTICAL',1);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (2,3,'2020-11-06','AGILIDAD',1);

INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento) VALUES (1,'2020-11-11','17:00',5);
INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento) VALUES (2,'2020-11-05','18:00',5);
INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento) VALUES (3,'2020-11-07','19:00',5);
