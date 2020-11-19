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


INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,saques_acertados,saques_totales,porcentaje_saques) VALUES (1,'javgutfal','11111111A','Alberto','Clemente','1998-11-06','Travesía Lorem ipsum dolor, 217 7ºD','arribaespaña@gmail.com','Villaviciosa',178,78,75,22.2,50,70,0.71);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (2,'gonlalle','11111112A','Paco','Tuerca','1986-02-05','C. Comercial Lorem, 171A 3ºA','cataluñaindependiente@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (3,'javgutfal','11111113A','Paco','Tuerca','1978-11-24','Callejón Lorem, 172A 1ºF','covidgod19@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (4,'javgutfal','11111114A','Paco','Tuerca','1987-06-08','C. Comercial Lorem, 216 12ºH','arsatratra@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (5,'javgutfal','11111115A','Paco','Tuerca','1989-03-23','Carrera Lorem ipsum dolor sit, 12 5ºB','ojitoconeldato@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (6,'javgutfal','11111116A','Paco','Tuerca','1988-01-03','Travesía Lorem ipsum dolor, 217B 14ºD','otakumuertoabonopamihuerto@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (7,'javgutfal','11111117A','Paco','Tuerca','1983-10-21','Avenida Lorem, 78B 11ºD','medijistequemecomprariasflores@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (8,'javgutfal','11111118A','Paco','Tuerca','1986-05-01','Acceso Lorem, 240 7ºC','siespañafueraundonutmadridnoexistiria@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (9,'javgutfal','11111119A','Paco','Tuerca','1983-12-29','Avenida Lorem ipsum dolor, 135B 14ºB','correoparacosasilegales52@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (10,'javgutfal','11111110A','Paco','Tuerca','1997-12-24','C. Comercial Lorem ipsum, 113A 4ºH','barpepepedidos@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (11,'javgutfal','11111120A','Paco','Tuerca','1992-03-20','Rambla Lorem ipsum dolor, 275A 10ºG','mgfzaricyjkk@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (12,'javgutfal','33423595H','Paco','Tuerca','1998-07-22','C. Comercial Lorem ipsum dolor sit, 263B 18ºG','navkkrcdcuxq@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (13,'javgutfal','29390736W','Paco','Tuerca','1993-04-25','Calle Lorem ipsum dolor sit, 293B 1ºF','yppaumrghdqw@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (14,'javgutfal','27424684J','Paco','Tuerca','1998-11-11','Cañada Lorem ipsum dolor sit, 256 4ºF','xzcveipypidy@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (15,'javgutfal','67570361H','Paco','Tuerca','1990-12-08','Urbanización Lorem ipsum dolor sit, 148B 10ºA','edipacuwugtq@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (16,'javgutfal','34035611G','Paco','Tuerca','2007-08-17','Carrer Lorem ipsum dolor, 12A','rkahdnxnjpxf@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (17,'javgutfal','09354596J','Paco','Tuerca','1989-01-11','Acceso Lorem, 267A 6ºC','xdgpzkqjfirz@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (18,'javgutfal','53931653B','Paco','Tuerca','1998-03-06','Avenida Lorem, 106A 14ºG','htevagkwhcax@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (29,'javgutfal','63567303H','Paco','Tuerca','1981-07-08','Alameda Lorem ipsum dolor sit, 224B 17ºH','cgmufnqwtwjy@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (20,'javgutfal','02107187L','Paco','Tuerca','2000-01-15','Pasaje Lorem ipsum dolor sit, 220 2ºE','yrkgtnnhfujj@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (21,'javgutfal','17004574F','Paco','Tuerca','1991-09-06','Carrer Lorem ipsum, 218 5ºB','rwrqpckdeaat@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (22,'javgutfal','83896479T','Paco','Tuerca','2008-07-11','Plaza Lorem ipsum, 109B 17ºA','dmwtvbuuwukt@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (23,'javgutfal','72458570Z','Paco','Tuerca','2006-09-09','Callejón Lorem ipsum, 141A 9ºF','tdhcrgehfpaf@gmail.com','Guarroman',175,78,75,23.2);
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc) VALUES (24,'javgutfal','52031289E','Paco','Tuerca','1982-11-04','Vía Lorem ipsum dolor sit, 267B 10ºC','pcbatcerenqt@gmail.com','Guarroman',175,78,75,23.2);

INSERT INTO equipos(id,categoria,federacion) VALUES (1,'Senior',false);
INSERT INTO equipos(id,categoria,federacion) VALUES (2,'Cadete',false);

INSERT INTO entrenadores(id,username,first_name, last_name, email, fecha_nacimiento) VALUES (1,'bencrealc','Teodoro', 'Sánchez Bermejo', 'teodorocoach@gmail.com','1987-8-19');
INSERT INTO entrenadores(id,username,first_name, last_name, email, fecha_nacimiento) VALUES (2,'bencrealc','Mauricio', 'Colmenero Muñoz', 'mastodonquijote@gmail.com','1997-8-19');


INSERT INTO personales(id,propietario) VALUES(1,'Pablo');
INSERT INTO personales(id,propietario) VALUES(2,'Ana');
INSERT INTO personales(id,propietario) VALUES(3,'Rosa');
INSERT INTO personales(id,propietario) VALUES(4,'Pepa');

-- INSERT  PARTIDOS

INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento) VALUES (1,'2020-11-06','17:00',5);
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento) VALUES (2,'2020-11-06','18:00',5);
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento) VALUES (3,'2020-11-06','19:00',5);
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento) VALUES (4,'2020-12-06','17:00',5);
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento) VALUES (5,'2020-10-06','18:00',5);
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento) VALUES (6,'2020-11-07','19:00',5);


-- INSERT  ENTRENAMIENTOS

INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento) VALUES (1,'2020-11-11','17:00',5);
INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento) VALUES (2,'2020-11-05','18:00',5);
INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento) VALUES (3,'2020-11-07','19:00',5);
INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento) VALUES (4,'2020-11-12','17:00',5);
INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento) VALUES (5,'2020-11-08','18:00',5);
INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento) VALUES (6,'2019-11-07','19:00',5);



-- INSERT ESTADISITICAS PERSONALES PARTIDO

INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,jugador_id,partido_id) VALUES (1,20,20,1,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,1,1);

INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,jugador_id,partido_id) VALUES (2,12,18,0.6,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,5,20,0.25,1,2); 

INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,partido_id) VALUES (3,14,22,0.7,10,20,0.5,15,20,0.75,5,20,0.25,1,3);
	
INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,partido_id) VALUES (4,10,25,0.4,10,20,0.5,5,20,0.25,15,20,0.75,1,4);
	
INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,partido_id) VALUES (5,12,14,0.7,15,20,0.75,5,20,0.25,5,20,0.25,1,5); 
	
INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,fintas_acertadas,fintas_totales,porcentaje_fintas,
	num_ataques_rapidos_acertados,num_ataques_rapidos_totales,porcentaje_ataques_rapidos,jugador_id,partido_id) VALUES (6,14,22,0.63,5,20,0.25,5,20,0.25,5,20,0.25,10,20,0.5,15,20,0.75,2,6); 


-- INSERT ESTADISITICAS PERSONALES ENTRENAMIENTO

INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,jugador_id,entrenamiento_id) VALUES (1,20,20,1,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,1,1);

INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,jugador_id,entrenamiento_id) VALUES (2,12,18,0.6,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,5,20,0.25,1,2); 

INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,entrenamiento_id) VALUES (3,14,22,0.7,10,20,0.5,15,20,0.75,5,20,0.25,1,3);
	
INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,entrenamiento_id) VALUES (4,10,25,0.4,10,20,0.5,5,20,0.25,15,20,0.75,1,4);
	
INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,entrenamiento_id) VALUES (5,12,14,0.7,15,20,0.75,5,20,0.25,5,20,0.25,1,5); 
	
INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,fintas_acertadas,fintas_totales,porcentaje_fintas,
	num_ataques_rapidos_acertados,num_ataques_rapidos_totales,porcentaje_ataques_rapidos,jugador_id,entrenamiento_id) VALUES (6,14,22,0.63,5,20,0.25,5,20,0.25,5,20,0.25,10,20,0.5,15,20,0.75,2,6); 

INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (1,3,'2020-11-06','SALTOVERTICAL',1);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (2,3,'2020-11-06','AGILIDAD',1);


INSERT INTO realiza_entrenamiento (entrenamiento_id,jugador_id) values (1,1);
INSERT INTO realiza_entrenamiento (entrenamiento_id,jugador_id) values (2,1);
INSERT INTO realiza_entrenamiento (entrenamiento_id,jugador_id) values (3,1);

INSERT INTO linea_material(id,cantidad) VALUES (1,9);
INSERT INTO linea_material(id,cantidad) VALUES (2,2);
INSERT INTO linea_material(id,cantidad) VALUES (3,1);
INSERT INTO linea_material(id,cantidad) VALUES (4,7);
INSERT INTO linea_material(id,cantidad) VALUES (5,21);
INSERT INTO linea_material(id,cantidad) VALUES (6,14);


INSERT INTO autobus(id,hora_salida,hora_llegada) VALUES (1,'12:30','14:00');
INSERT INTO autobus(id,hora_salida,hora_llegada) VALUES (2,'07:30','00:30');

