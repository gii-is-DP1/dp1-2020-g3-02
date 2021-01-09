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
INSERT INTO authorities(id,username,authority) VALUES (15,'gonlalle','jugador');

INSERT INTO users(username,password,enabled) VALUES ('gonlalle2','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'gonlalle2','entrenador');

INSERT INTO users(username,password,enabled) VALUES ('bencrealc','elpepe',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (16,'bencrealc','jugador');

INSERT INTO users(username,password,enabled) VALUES ('javgutfal','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (17,'javgutfal','jugador');

INSERT INTO users(username,password,enabled) VALUES ('blamaurob','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (18,'blamaurob','entrenador');

INSERT INTO users(username,password,enabled) VALUES ('user1','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (19,'user1','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user2','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (20,'user2','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user3','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (21,'user3','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user4','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (22,'user4','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user5','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (23,'user5','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user6','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (24,'user6','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user7','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (25,'user7','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user8','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (26,'user8','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user9','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (27,'user9','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user10','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (28,'user10','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user11','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (29,'user11','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user12','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (30,'user12','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user13','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (31,'user13','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user14','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (32,'user14','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user15','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (33,'user15','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user16','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (34,'user16','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user17','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (35,'user17','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user18','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (36,'user18','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user19','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (37,'user19','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user20','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (38,'user20','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user21','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (39,'user21','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user22','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (40,'user22','jugador');

INSERT INTO users(username,password,enabled) VALUES ('user23','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (41,'user23','entrenador');

INSERT INTO users(username,password,enabled) VALUES ('user24','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (42,'user24','entrenador');

INSERT INTO users(username,password,enabled) VALUES ('gonlalle3','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (43,'gonlalle3','estadistico');

INSERT INTO users(username,password,enabled) VALUES ('user26','asdf1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (44,'user26','estadistico');

INSERT INTO users(username,password,enabled) VALUES ('blamaurob2','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (45,'blamaurob2','jugador');

--INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
--INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
--INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
--INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
--INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');

-- INSERT  JUGADORES
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,saques_acertados,saques_totales,porcentaje_saques,posicion_principal,posicion_secundaria) VALUES (1,'javgutfal','11111111A','Alberto','Clemente','1998-11-06','Travesía Lorem ipsum dolor, 217 7ºD','arribaespaña@gmail.com','Villaviciosa',178,78,75,22.2,50,70,0.71,'PUNTA','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (2,'gonlalle','11111112A','Paco','Tuerca','1986-02-05','C. Comercial Lorem, 171A 3ºA','cataluñaindependiente@gmail.com','Guarroman',175,78,75,23.2,'COLOCADOR','CENTRAL');

INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria, estado_actual) VALUES (3,'user1','11111113A','Pepe','Tuerca','1978-11-24','Callejón Lorem, 172A 1ºF','covidgod19@gmail.com','Guarroman',175,78,75,23.2,'LIBERO','PUNTA','LESIONADO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (4,'user2','11111114A','Roberto','Canales','1987-06-08','C. Comercial Lorem, 216 12ºH','arsatratra@gmail.com','Guarroman',175,78,75,23.2,'COLOCADOR','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (5,'user3','11111115A','Julio','Medina','1989-03-23','Carrera Lorem ipsum dolor sit, 12 5ºB','ojitoconeldato@gmail.com','Guarroman',175,78,75,23.2,'CENTRAL','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (6,'user4','11111116A','Carlos','Bermejo','1988-01-03','Travesía Lorem ipsum dolor, 217B 14ºD','otakumuertoabonopamihuerto@gmail.com','Guarroman',175,78,75,23.2,'COLOCADOR','PUNTA');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (7,'user5','11111117A','Raul','Troya','1983-10-21','Avenida Lorem, 78B 11ºD','medijistequemecomprariasflores@gmail.com','Guarroman',175,78,75,23.2,'PUNTA','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (8,'user6','11111118A','Olivio','Peinado','1986-05-01','Acceso Lorem, 240 7ºC','siespañafueraundonutmadridnoexistiria@gmail.com','Guarroman',175,78,75,23.2,'PUNTA','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (9,'user7','11111119A','Iván','Arteaga','1983-12-29','Avenida Lorem ipsum dolor, 135B 14ºB','correoparacosasilegales52@gmail.com','Guarroman',175,78,75,23.2,'OPUESTO','COLOCADOR');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (10,'user8','11111110A','Benjamín','Camino','1997-12-24','C. Comercial Lorem ipsum, 113A 4ºH','barpepepedidos@gmail.com','Guarroman',175,78,75,23.2,'CENTRAL','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (11,'user9','11111120A','Raul','Parrado','1992-03-20','Rambla Lorem ipsum dolor, 275A 10ºG','mgfzaricyjkk@gmail.com','Guarroman',175,78,75,23.2,'LIBERO','PUNTA');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (12,'user10','33423595H','Jesús','Falcado','1998-07-22','C. Comercial Lorem ipsum dolor sit, 263B 18ºG','navkkrcdcuxq@gmail.com','Guarroman',175,78,75,23.2,'PUNTA','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (13,'user11','29390736W','Javier','Duro','1993-04-25','Calle Lorem ipsum dolor sit, 293B 1ºF','yppaumrghdqw@gmail.com','Guarroman',175,78,75,23.2,'PUNTA','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (14,'user12','27424684J','Horacio','Quevedo','1998-11-11','Cañada Lorem ipsum dolor sit, 256 4ºF','xzcveipypidy@gmail.com','Guarroman',175,78,75,23.2,'COLOCADOR','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (15,'user13','67570361H','Iñaqui','Williams','1990-12-08','Urbanización Lorem ipsum dolor sit, 148B 10ºA','edipacuwugtq@gmail.com','Guarroman',175,78,75,23.2,'PUNTA','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (16,'user14','34035611G','Cristiano','Ronaldo','2007-08-17','Carrer Lorem ipsum dolor, 12A','rkahdnxnjpxf@gmail.com','Guarroman',175,78,75,23.2,'OPUESTO','CENTRAL');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (17,'user15','09354596J','Fernando','Numancia','1989-01-11','Acceso Lorem, 267A 6ºC','xdgpzkqjfirz@gmail.com','Guarroman',175,78,75,23.2,'COLOCADOR','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (18,'user16','53931653B','Julio','Carrera','1998-03-06','Avenida Lorem, 106A 14ºG','htevagkwhcax@gmail.com','Guarroman',175,78,75,23.2,'CENTRAL','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (19,'user17','63567303H','Arturo','León','1981-07-08','Alameda Lorem ipsum dolor sit, 224B 17ºH','cgmufnqwtwjy@gmail.com','Guarroman',175,78,75,23.2,'COLOCADOR','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (20,'user18','02107187L','Paco','Manila','2000-01-15','Pasaje Lorem ipsum dolor sit, 220 2ºE','yrkgtnnhfujj@gmail.com','Guarroman',175,78,75,23.2,'PUNTA','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (21,'user19','17004574F','Manuel','Camilo','1991-09-06','Carrer Lorem ipsum, 218 5ºB','rwrqpckdeaat@gmail.com','Guarroman',175,78,75,23.2,'OPUESTO','CENTRAL');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (22,'user20','83896479T','Venancio','Paleto','2008-07-11','Plaza Lorem ipsum, 109B 17ºA','dmwtvbuuwukt@gmail.com','Guarroman',175,78,75,23.2,'CENTRAL','OPUESTO');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (23,'user21','72458570Z','Norberto','Ugami','2006-09-09','Callejón Lorem ipsum, 141A 9ºF','tdhcrgehfpaf@gmail.com','Guarroman',175,78,75,23.2,'OPUESTO','CENTRAL');
INSERT INTO jugadores(id,username,dni,first_name,last_name,fecha_nacimiento,direccion,email,localidad,altura,peso,peso_ideal,imc,posicion_principal,posicion_secundaria) VALUES (24,'user22','52031289E','Eren','Jaeger','1982-11-04','Vía Lorem ipsum dolor sit, 267B 10ºC','pcbatcerenqt@gmail.com','Guarroman',175,78,75,23.2,'PUNTA','LIBERO');




-- INSERT  CAPITANES
INSERT INTO capitanes(id,ntiemposmuertos,actitud,jugador_id) VALUES (1,6,'POSITIVA',1);
INSERT INTO capitanes(id,ntiemposmuertos,actitud,jugador_id) VALUES (2,10,'POSITIVA', 2);

-- INSERT  ENTRENADORES
INSERT INTO entrenadores(id,username,first_name, last_name, email, fecha_nacimiento) VALUES (1,'gonlalle2','Gonzalo', 'Lallena Alva', 'gonalleAlva@gmail.com','2000-8-19');
INSERT INTO entrenadores(id,username,first_name, last_name, email, fecha_nacimiento) VALUES (2,'user23','Teodoro', 'Sánchez Bermejo', 'teodorocoach@gmail.com','1987-8-19');
INSERT INTO entrenadores(id,username,first_name, last_name, email, fecha_nacimiento) VALUES (3,'user24','Mauricio', 'Colmenero Muñoz', 'mastodonquijote@gmail.com','1997-8-19');
INSERT INTO entrenadores(id,username,first_name, last_name, email, fecha_nacimiento) VALUES (4,'blamaurob','Mauricio', 'Colmenero Muñoz', 'mastodonqusijote@gmail.com','1997-8-19');

-- INSERT  ESTADISTICOS
INSERT INTO estadisticos(id,username,first_name, last_name, email, fecha_nacimiento) VALUES (1,'gonlalle3','Romualdo', 'Sánchez Bermejo', 'romualdostats@gmail.com','1987-8-19');
INSERT INTO estadisticos(id,username,first_name, last_name, email, fecha_nacimiento) VALUES (2,'user26','Julio', 'Iglesias', 'julioiglesias@gmail.com','1997-8-19');



-- INSERT  AUTORIZACIONES
INSERT INTO autorizaciones(id,fecha,tipo, jugador_id) VALUES (1,'2020-10-09','TRANSPORTE',1);
INSERT INTO autorizaciones(id,fecha,tipo, jugador_id) VALUES (3,'2020-10-09','EXCURSIONES',1);
INSERT INTO autorizaciones(id,fecha,tipo, jugador_id) VALUES (2,'2020-09-09','EXCURSIONES',4);

-- INSERT  EQUIPOS
INSERT INTO equipos(id,categoria,federacion,capitan_id, entrenador_id, sistema_juego) VALUES (1,'Senior',false,1,1,'CINCO_UNO');
INSERT INTO equipos(id,categoria,federacion,capitan_id, entrenador_id, sistema_juego) VALUES (2,'Cadete',false,2,1,'COLOCADOR_GENERAL');
INSERT INTO equipos(id,categoria,federacion,capitan_id, entrenador_id, sistema_juego) VALUES (3,'Junior',false,2,1,'COLOCADOR_GENERAL');

-- INSERT  PERTENECEA

INSERT INTO pertenecea(jugador_id,equipo_id) VALUES (1,1);
INSERT INTO pertenecea(jugador_id,equipo_id) VALUES (2,1);
INSERT INTO pertenecea(jugador_id,equipo_id) VALUES (3,1);
INSERT INTO pertenecea(jugador_id,equipo_id) VALUES (4,1);
INSERT INTO pertenecea(jugador_id,equipo_id) VALUES (5,1);
INSERT INTO pertenecea(jugador_id,equipo_id) VALUES (6,1);
INSERT INTO pertenecea(jugador_id,equipo_id) VALUES (7,1);
INSERT INTO pertenecea(jugador_id,equipo_id) VALUES (8,1);
INSERT INTO pertenecea(jugador_id,equipo_id) VALUES (9,2);
INSERT INTO pertenecea(jugador_id,equipo_id) VALUES (10,2);
INSERT INTO pertenecea(jugador_id,equipo_id) VALUES (1,2);
INSERT INTO pertenecea(jugador_id,equipo_id) VALUES (2,2);
INSERT INTO pertenecea(jugador_id,equipo_id) VALUES (2,3);

-- INSERT  PARTIDOS

INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (1,'2021-11-06','17:00',5,1,20,20,1,5,20,0.25,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);
	
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (2,'2021-11-07','18:00',5,1,17,20,0.85,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);
	
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (3,'2021-11-08','19:00',5,1,15,20,0.75,12,20,0.6,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);
	
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (4,'2021-12-06','17:00',5,1,12,20,0.6,15,20,0.75,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);
	
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (5,'2021-12-07','18:00',5,2,10,20,0.5,17,20,0.85,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);
	
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (6,'2021-12-08','19:00',5,2,5,20,0.25,20,20,1,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);	

INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (7,'2021-12-08','19:00',5,3,5,20,0.25,20,20,1,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);		

--Mitad 

INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (8,'2022-08-06','17:00',5,3,20,20,1,5,20,0.25,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);
	
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (9,'2022-08-07','18:00',5,3,17,20,0.85,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);
	
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (10,'2022-08-04','19:00',5,3,15,20,0.75,12,20,0.6,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);
	
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (11,'2022-08-09','17:00',5,3,12,20,0.6,15,20,0.75,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);
	
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (12,'2022-08-07','18:00',5,2,10,20,0.5,17,20,0.85,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);
	
INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (13,'2022-08-12','19:00',5,2,5,20,0.25,20,20,1,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);	

INSERT INTO partidos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,tiempo_5_1,tiempo_4_2,tiempo_6_2) VALUES (14,'2022-08-08','19:00',5,3,5,20,0.25,20,20,1,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,30,15,45);	
	
			
INSERT INTO juega_partido(jugador_id,partido_id) VALUES (1,1);
INSERT INTO juega_partido(jugador_id,partido_id) VALUES (2,1);	
INSERT INTO juega_partido(jugador_id,partido_id) VALUES (3,1);	
INSERT INTO juega_partido(jugador_id,partido_id) VALUES (4,1);
INSERT INTO juega_partido(jugador_id,partido_id) VALUES (5,1);
INSERT INTO juega_partido(jugador_id,partido_id) VALUES (6,1);	
INSERT INTO juega_partido(jugador_id,partido_id) VALUES (7,1);	
INSERT INTO juega_partido(jugador_id,partido_id) VALUES (8,1);	
INSERT INTO juega_partido(jugador_id,partido_id) VALUES (9,2);
INSERT INTO juega_partido(jugador_id,partido_id) VALUES (2,2);
INSERT INTO juega_partido(jugador_id,partido_id) VALUES (3,2);
INSERT INTO juega_partido(jugador_id,partido_id) VALUES (4,2);
INSERT INTO juega_partido(jugador_id,partido_id) VALUES (5,2);

--INSERT INTO jugando_partido(jugador_id,partido_id) VALUES (1,1);
--INSERT INTO jugando_partido(jugador_id,partido_id) VALUES (2,1);
--INSERT INTO jugando_partido(jugador_id,partido_id) VALUES (3,1);
--INSERT INTO jugando_partido(jugador_id,partido_id) VALUES (4,1);
--INSERT INTO jugando_partido(jugador_id,partido_id) VALUES (5,1);
--INSERT INTO jugando_partido(jugador_id,partido_id) VALUES (6,1);

-- INSERT  ENTRENAMIENTOS

INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates) VALUES (1,'2020-11-06','11:00',5,1,20,20,1,5,20,0.25,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75);
	
INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates) VALUES (2,'2020-11-07','12:00',5,1,17,20,0.85,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75);
	
INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates) VALUES (3,'2020-11-08','11:00',5,1,15,20,0.75,12,20,0.6,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75);
	
INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates) VALUES (4,'2020-12-06','11:00',5,1,12,20,0.6,15,20,0.75,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75);
	
INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates) VALUES (5,'2020-12-07','12:00',5,2,10,20,0.5,17,20,0.85,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75);
	
INSERT INTO entrenamientos(id,fecha,hora,tiempo_calentamiento,equipo_id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates) VALUES (6,'2020-12-08','12:00',5,2,5,20,0.25,20,20,1,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75);	



-- INSERT ESTADISITICAS PERSONALES PARTIDO Jugador 1

--INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
--	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
--	remates_acertados,remates_totales,porcentaje_remates,jugador_id,partido_id) VALUES (1,20,20,1,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,1,1);


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
	num_ataques_rapidos_acertados,num_ataques_rapidos_totales,porcentaje_ataques_rapidos,jugador_id,partido_id) VALUES (6,14,22,0.63,5,20,0.25,5,20,0.25,5,20,0.25,10,20,0.5,15,20,0.75,1,6); 

-- INSERT ESTADISITICAS PERSONALES PARTIDO Jugador 2

INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,jugador_id,partido_id) VALUES (7,20,20,1,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,2,2);

INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,jugador_id,partido_id) VALUES (8,12,18,0.6,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,5,20,0.25,2,3); 

--INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
--	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
--	jugador_id,partido_id) VALUES (9,14,22,0.7,10,20,0.5,15,20,0.75,5,20,0.25,2,1);
	
INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,partido_id) VALUES (10,10,25,0.4,10,20,0.5,5,20,0.25,15,20,0.75,2,6);
	
INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,partido_id) VALUES (11,12,14,0.7,15,20,0.75,5,20,0.25,5,20,0.25,2,4); 
	
INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,fintas_acertadas,fintas_totales,porcentaje_fintas,
	num_ataques_rapidos_acertados,num_ataques_rapidos_totales,porcentaje_ataques_rapidos,jugador_id,partido_id) VALUES (12,14,22,0.63,5,20,0.25,5,20,0.25,5,20,0.25,10,20,0.5,15,20,0.75,2,5); 


-- INSERT ESTADISITICAS PERSONALES PARTIDO Jugador 3

INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,jugador_id,partido_id) VALUES (13,20,20,1,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,3,6);

INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,jugador_id,partido_id) VALUES (14,12,18,0.6,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,5,20,0.25,3,5); 

INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,partido_id) VALUES (15,14,22,0.7,10,20,0.5,15,20,0.75,5,20,0.25,3,4);
	
INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,partido_id) VALUES (16,10,25,0.4,10,20,0.5,5,20,0.25,15,20,0.75,3,3);
	
INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,partido_id) VALUES (17,12,14,0.7,15,20,0.75,5,20,0.25,5,20,0.25,3,2); 
	
--INSERT INTO estadisticas_personal_partido(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
--	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,fintas_acertadas,fintas_totales,porcentaje_fintas,
--	num_ataques_rapidos_acertados,num_ataques_rapidos_totales,porcentaje_ataques_rapidos,jugador_id,partido_id) VALUES (18,14,22,0.63,5,20,0.25,5,20,0.25,5,20,0.25,10,20,0.5,15,20,0.75,3,1); 





-- INSERT ESTADISITICAS PERSONALES ENTRENAMIENTO Jugador 1

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
	num_ataques_rapidos_acertados,num_ataques_rapidos_totales,porcentaje_ataques_rapidos,jugador_id,entrenamiento_id) VALUES (6,14,22,0.63,5,20,0.25,5,20,0.25,5,20,0.25,10,20,0.5,15,20,0.75,1,6); 

-- INSERT ESTADISITICAS PERSONALES ENTRENAMIENTO Jugador 2

INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,jugador_id,entrenamiento_id) VALUES (7,20,20,1,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,2,2);

INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,jugador_id,entrenamiento_id) VALUES (8,12,18,0.6,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,5,20,0.25,2,3); 

INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,entrenamiento_id) VALUES (9,14,22,0.7,10,20,0.5,15,20,0.75,5,20,0.25,2,1);
	
INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,entrenamiento_id) VALUES (10,10,25,0.4,10,20,0.5,5,20,0.25,15,20,0.75,2,6);
	
INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,entrenamiento_id) VALUES (11,12,14,0.7,15,20,0.75,5,20,0.25,5,20,0.25,2,4); 
	
INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,fintas_acertadas,fintas_totales,porcentaje_fintas,
	num_ataques_rapidos_acertados,num_ataques_rapidos_totales,porcentaje_ataques_rapidos,jugador_id,entrenamiento_id) VALUES (12,14,22,0.63,5,20,0.25,5,20,0.25,5,20,0.25,10,20,0.5,15,20,0.75,2,5); 


-- INSERT ESTADISITICAS PERSONALES ENTRENAMIENTO Jugador 3

INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,jugador_id,entrenamiento_id) VALUES (13,20,20,1,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,15,20,0.75,3,6);

INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,bloqueos_acertados,bloqueos_totales,porcentaje_bloqueos,
	remates_acertados,remates_totales,porcentaje_remates,jugador_id,entrenamiento_id) VALUES (14,12,18,0.6,10,20,0.5,5,20,0.25,5,20,0.25,5,20,0.25,5,20,0.25,3,5); 

INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,entrenamiento_id) VALUES (15,14,22,0.7,10,20,0.5,15,20,0.75,5,20,0.25,3,4);
	
INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,entrenamiento_id) VALUES (16,10,25,0.4,10,20,0.5,5,20,0.25,15,20,0.75,3,3);
	
INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,
	jugador_id,entrenamiento_id) VALUES (17,12,14,0.7,15,20,0.75,5,20,0.25,5,20,0.25,3,2); 
	
INSERT INTO estadisticas_personal_entrenamiento(id,saques_acertados,saques_totales,porcentaje_saques,recepciones_acertadas,recepciones_totales,porcentaje_recepciones,
	colocaciones_acertadas,colocaciones_totales,porcentaje_colocaciones,defensas_acertadas,defensas_totales,porcentaje_defensas,fintas_acertadas,fintas_totales,porcentaje_fintas,
	num_ataques_rapidos_acertados,num_ataques_rapidos_totales,porcentaje_ataques_rapidos,jugador_id,entrenamiento_id) VALUES (18,14,22,0.63,5,20,0.25,5,20,0.25,5,20,0.25,10,20,0.5,15,20,0.75,3,1); 



INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (1,10,'2020-11-06','ABDOMINAL',1);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (2,12,'2020-11-07','ABDOMINAL',1);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (3,15,'2020-11-08','ABDOMINAL',1);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (4,3,'2020-11-06','SALTOVERTICAL',1);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (5,3,'2020-11-06','AGILIDAD',1);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (6,10,'2020-11-06','FLEXIBILIDAD',1);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (7,12,'2020-11-07','FLEXIBILIDAD',1);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (8,15,'2020-11-08','RESISTENCIA',1);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (9,3,'2020-11-06','PULSACIONESMINIMAS',1);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (10,3,'2020-11-06','AGILIDAD',1);

INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (11,10,'2020-11-06','ABDOMINAL',2);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (12,12,'2020-11-07','ABDOMINAL',2);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (13,15,'2020-11-08','ABDOMINAL',2);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (14,3,'2020-11-06','SALTOVERTICAL',2);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (15,3,'2020-11-06','AGILIDAD',2);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (16,10,'2020-11-06','FLEXIBILIDAD',2);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (17,12,'2020-11-07','FLEXIBILIDAD',2);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (18,15,'2020-11-08','RESISTENCIA',2);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (19,3,'2020-11-06','PULSACIONESMINIMAS',2);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (20,3,'2020-11-06','AGILIDAD',2);

INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (21,10,'2020-11-06','ABDOMINAL',3);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (22,12,'2020-11-07','ABDOMINAL',3);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (23,15,'2020-11-08','ABDOMINAL',3);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (24,3,'2020-11-06','SALTOVERTICAL',3);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (25,3,'2020-11-06','AGILIDAD',3);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (26,10,'2020-11-06','FLEXIBILIDAD',3);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (27,12,'2020-11-07','FLEXIBILIDAD',3);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (28,15,'2020-11-08','RESISTENCIA',3);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (29,3,'2020-11-06','PULSACIONESMINIMAS',3);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (30,3,'2020-11-07','AGILIDAD',3);

INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (31,3,'2020-11-07','VELOCIDAD',1);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (32,3,'2020-11-07','VELOCIDAD',2);
INSERT INTO pruebas_condicion_fisica (id,dato,fecha,tipo_prueba,jugador_id) VALUES (33,3,'2020-11-07','VELOCIDAD',3);



INSERT INTO realiza_entrenamiento (entrenamiento_id,jugador_id) values (1,1);
INSERT INTO realiza_entrenamiento (entrenamiento_id,jugador_id) values (2,1);
INSERT INTO realiza_entrenamiento (entrenamiento_id,jugador_id) values (3,1);





INSERT INTO materiales (id,descripcion,tipo,stock,estado) VALUES (1,'balon duro','BALONMEDICINAL',3,'INSERVIBLE');
INSERT INTO materiales (id,descripcion,tipo,stock,estado) VALUES (2,'balon blando','BALONDEJUEGO',9,'ACEPTABLE');
INSERT INTO materiales (id,descripcion,tipo,stock,estado) VALUES (3,'cuerda','CUERDA',8,'BUENO');
INSERT INTO materiales (id,descripcion,tipo,stock,estado) VALUES (4,'cinta','CINTA',10,'NUEVO');
INSERT INTO materiales (id,descripcion,tipo,stock,estado) VALUES (5,'poste','POSTE',11,'NUEVO');
INSERT INTO materiales (id,descripcion,tipo,stock,estado) VALUES (6,'red','RED',11,'NUEVO');
INSERT INTO materiales (id,descripcion,tipo,stock,estado) VALUES (7,'cono bajo','CONOBAJO',10,'INSERVIBLE');
INSERT INTO materiales (id,descripcion,tipo,stock,estado) VALUES (8,'cono medio','CONOMEDIO',1,'BUENO');
INSERT INTO materiales (id,descripcion,tipo,stock,estado) VALUES (9,'cono alto nuevos','CONOALTO',3,'NUEVO');
INSERT INTO materiales (id,descripcion,tipo,stock,estado) VALUES (10,'cono alto bueno','CONOALTO',6,'BUENO');
INSERT INTO materiales (id,descripcion,tipo,stock,estado) VALUES (11,'cono alto inservibles','CONOALTO',3,'INSERVIBLE');

	

INSERT INTO linea_material(id,cantidad,entrenamiento_id,material_id) VALUES (1,2,1,1);
INSERT INTO linea_material(id,cantidad,entrenamiento_id,material_id) VALUES (2,2,1,2);
INSERT INTO linea_material(id,cantidad,entrenamiento_id,material_id) VALUES (3,2,1,3);
INSERT INTO linea_material(id,cantidad,entrenamiento_id,material_id) VALUES (4,1,1,4);
INSERT INTO linea_material(id,cantidad,entrenamiento_id,material_id) VALUES (5,6,1,5);
INSERT INTO linea_material(id,cantidad,entrenamiento_id,material_id) VALUES (6,3,2,1);
INSERT INTO linea_material(id,cantidad,entrenamiento_id,material_id) VALUES (7,4,2,2);
INSERT INTO linea_material(id,cantidad,entrenamiento_id,material_id) VALUES (8,1,2,3);
INSERT INTO linea_material(id,cantidad,entrenamiento_id,material_id) VALUES (9,5,2,4);
INSERT INTO linea_material(id,cantidad,entrenamiento_id,material_id) VALUES (10,2,2,5);
INSERT INTO linea_material(id,cantidad,entrenamiento_id,material_id) VALUES (11,3,1,10);
INSERT INTO linea_material(id,cantidad,entrenamiento_id,material_id) VALUES (12,1,2,11);

-- INSERT AUTOBUSES
INSERT INTO autobus(id) VALUES (1);
INSERT INTO autobus(id) VALUES (2);
INSERT INTO autobus(id) VALUES (3);


-- INSERT PERSONALES
INSERT INTO personales(id,propietario,jugador_id) VALUES (1,'Pablo',1);
INSERT INTO personales(id,propietario,jugador_id) VALUES (2,'Ana',1);
INSERT INTO personales(id,propietario,jugador_id) VALUES (3,'Rosa',2);
INSERT INTO personales(id,propietario,jugador_id) VALUES (4,'Pepa',3);

--INSERT VIAJE

INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (1,'IDA',1,1,null,1,'12:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (2,'IDA',2,1,1,null,'12:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (3,'IDA',3,1,null,3,'12:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (4,'IDA',4,1,1,null,'12:30',false);

INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (17,'IDA',5,1,null,1,'12:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (18,'IDA',6,1,1,null,'12:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (19,'IDA',7,1,1,null,'12:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (20,'IDA',8,1,1,null,'12:30',false);

INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (5,'VUELTA',1,1,1,null,'14:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (6,'VUELTA',2,1,null,2,'14:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (7,'VUELTA',3,1,1,null,'14:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (8,'VUELTA',4,1,1,null,'14:30',false);

INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (9,'IDA',1,2,null,1,'12:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (10,'IDA',2,2,1,null,'12:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (11,'IDA',3,2,1,null,'12:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (12,'IDA',4,2,1,null,'12:30',false);

INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (13,'VUELTA',1,2,1,null,'14:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (14,'VUELTA',2,2,null,2,'14:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (15,'VUELTA',3,2,1,null,'14:30',false);
INSERT INTO viaje(id,tipo_viaje,jugador_id,partido_id,autobus_id,personal_id,hora_salida, ha_llegado) VALUES (16,'VUELTA',4,2,1,null,'14:30',false);



-- INSERT NUMCAMISETAS
INSERT INTO num_camisetas(id,numero,jugador_id,equipo_id) VALUES (1,10,1,1);
INSERT INTO num_camisetas(id,numero,jugador_id,equipo_id) VALUES (2,10,3,1);
INSERT INTO num_camisetas(id,numero,jugador_id,equipo_id) VALUES (3,11,2,1);
INSERT INTO num_camisetas(id,numero,jugador_id,equipo_id) VALUES (4,2,4,1);
INSERT INTO num_camisetas(id,numero,jugador_id,equipo_id) VALUES (5,3,5,1);
INSERT INTO num_camisetas(id,numero,jugador_id,equipo_id) VALUES (6,12,6,1);
INSERT INTO num_camisetas(id,numero,jugador_id,equipo_id) VALUES (7,4,7,1);
INSERT INTO num_camisetas(id,numero,jugador_id,equipo_id) VALUES (8,5,8,1);

-- INSERT SUSTITUCIONES
INSERT INTO sustituciones(id,minuto_sustitucion,jugador_entra,jugador_sale,partido_id) VALUES (1,25,1,2,1);
INSERT INTO sustituciones(id,minuto_sustitucion,jugador_entra,jugador_sale,partido_id) VALUES (2,30,1,2,2);
INSERT INTO sustituciones(id,minuto_sustitucion,jugador_entra,jugador_sale,partido_id) VALUES (3,45,2,1,1);

-- INSERT PRIVILEGIOS
INSERT INTO privilegios(descripcion,tipo_privilegio,jugador_id,equipo_id) VALUES('de locos','PARTIDOS',1,1);


--INSERT EJERCICIOS INDIVIDUALES
INSERT INTO ejercicios_individuales(id,nombre,descripcion,tipo_ejercicio) VALUES(1,'Saque','Realizar 30 saques del tipo que sea',3);
INSERT INTO ejercicios_individuales(id,nombre,descripcion,tipo_ejercicio) VALUES(2,'Ataque','Ataque',0);
INSERT INTO ejercicios_individuales(id,nombre,descripcion,tipo_ejercicio) VALUES(3,'Recepcion','Recibir la pelota 10 veces',1);

--INSERT REALIZA EJERCICIOS
INSERT INTO realiza_ejercicios(id,jugador_id, ejercicio_individual_id, fecha) VALUES(1,1,1,'2020-11-06');
INSERT INTO realiza_ejercicios(id,jugador_id, ejercicio_individual_id, fecha) VALUES(2,6,2,'2020-10-06');
