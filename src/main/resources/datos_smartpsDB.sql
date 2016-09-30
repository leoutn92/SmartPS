INSERT INTO Estado VALUES (1,'Plan presentado','Plan de PSpresentado aun no evaluado por el consejo');
INSERT INTO Estado VALUES (2,'Plan observado','Plan de PS visado por el consejo con correcciones pendientes');
INSERT INTO Estado VALUES (3,'Plan aprobado','Plan de PS aprobado por el consejo directivo');
INSERT INTO Estado VALUES (4,'Plan rechazado','Plan de PS rechazado de manera definitiva por el consejo');
INSERT INTO Estado VALUES (5,'Plan vencido','Plan de PS vencido');
INSERT INTO Estado VALUES (6,'Informe presentado','Informe final de PS presentado aun no evaluado por el consejo');
INSERT INTO Estado VALUES (7,'Informe observado','Informe final de PS visado por el conseco con correciones pendientes');
INSERT INTO Estado VALUES (8,'Informe aprobado','Informe final de PS aprobado por el consejo directivo');
INSERT INTO Estado VALUES (9,'Informe vencido','Informe final de PS vencido');
INSERT INTO Estado VALUES (10,'PS aprobada','PS aprobada');
INSERT INTO Estado VALUES (11,'PS cancelada','PS cancelada por Alumno u otros motivos');


INSERT INTO Alumno VALUES(18189,'Vallejos Lucas Matias',2010); /*que seria la columna ciclo lectivo???*/
INSERT INTO Alumno VALUES(17848,'Seniquiel Matias',2009);
INSERT INTO Alumno VALUES(19099,'Aquino Leonel',2011);
INSERT INTO Alumno VALUES(16632,'Grinberg David',2008);
INSERT INTO Alumno VALUES(16786,'Ali Maria',2008);
INSERT INTO Alumno VALUES(16000,'Aguirre Carolina',2008);
INSERT INTO Alumno VALUES(16271,'Juan Perez',2008);
INSERT INTO Alumno VALUES(18333,'Carlos Gomez',2010);
INSERT INTO Alumno VALUES(18981,'Gonzalo Gonzalez',2011);


INSERT INTO Area VALUES (1,'Desarrollo y programacion','Teclear todo el dia');
INSERT INTO Area VALUES (2,'Redes','Configuracion redes');
INSERT INTO Area VALUES (3,'Sistemas','Analisis y Diseño');
INSERT INTO Area VALUES (4,'Computacion','Mantenimiento de pc');
INSERT INTO Area VALUES (5,'Base de datos','Analisis sobre la gestion en el almacenamiento de datos');


INSERT INTO Organizacion VALUES(1,'UTN Facultad Regional Resistencia','Trabajo de investigacion');
INSERT INTO Organizacion VALUES(2,'Globant','We create digital journeys');
INSERT INTO Organizacion VALUES(3,'DesarrollosNEA','Dedicada al desarrollo de sw y administracion de infraestructura');
INSERT INTO Organizacion VALUES(4,'VGMSistemas','Dedicada al desarrollo de sistemas');
INSERT INTO Organizacion VALUES(5,'Banco del Chaco','Area de seguridad de la informacion');
INSERT INTO Organizacion VALUES(6,'Devlights','Implementacion de soluciones informaticas a medida');


INSERT INTO TipoActividad VALUES(1,'Pasantia','Acreditar una relacion laboral o pasantia rentada en empresas del medio');
INSERT INTO TipoActividad VALUES(2,'Proyecto Final','Desarrollo de proyecto final mediante convenio especifico con terceros');
INSERT INTO TipoActividad VALUES(3,'Grupos de Investigacion','Participacion activa en grupos de investigacion pertenecientes a instituciones de nivel academico reconocido');
INSERT INTO TipoActividad VALUES(4,'Tareas de ingenieria','Desarrollar tareas de ing en ambito de ONG, instituciones y/o empresas productivas o de servicios publicas o privadas');


INSERT INTO PS(id_ps,legajo,id_area,id_estado,id_org,id_tipo_actividad,ciclo_lectivo,cuatrimestre,titulo) VALUES(1,17848,1,1,1,3,2016,2,'Evaluacion de calidad en repositorios institucionales');
INSERT INTO PS(id_ps,legajo,id_area,id_estado,id_org,id_tipo_actividad,ciclo_lectivo,cuatrimestre,titulo) VALUES(2,18189,2,3,3,1,2016,1,'Implementacion de protocolos de transmision');
INSERT INTO PS(id_ps,legajo,id_area,id_estado,id_org,id_tipo_actividad,ciclo_lectivo,cuatrimestre,titulo) VALUES(3,16632,2,5,3,1,2014,2,'Configuracion de dispositivos de ruteo');
INSERT INTO PS(id_ps,legajo,id_area,id_estado,id_org,id_tipo_actividad,ciclo_lectivo,cuatrimestre,titulo) VALUES(4,19099,3,4,4,1,2015,1,'Desarrollo de sistema a medida para importante distribuidora');
INSERT INTO PS(id_ps,legajo,id_area,id_estado,id_org,id_tipo_actividad,ciclo_lectivo,cuatrimestre,titulo) VALUES(5,16786,4,7,5,1,2015,2,'Implementacion de estandares de seguridad en sistema bancario');
INSERT INTO PS(id_ps,legajo,id_area,id_estado,id_org,id_tipo_actividad,ciclo_lectivo,cuatrimestre,titulo,nro_disposicion) VALUES(6,16000,3,10,1,2,2013,1,'Ampliacion de funcionalidades sobre proyecto final','9755');
INSERT INTO PS(id_ps,legajo,id_area,id_estado,id_org,id_tipo_actividad,ciclo_lectivo,cuatrimestre,titulo) VALUES(7,16271,1,9,6,4,2013,2,'Restablecer proceso productivo mediante nuevas tecnologias de desarrollo de sw');
INSERT INTO PS(id_ps,legajo,id_area,id_estado,id_org,id_tipo_actividad,ciclo_lectivo,cuatrimestre,titulo) VALUES(8,18333,5,6,1,3,2015,2,'Invetigacion de DW mediante procesos de mineria de datos');
INSERT INTO PS(id_ps,legajo,id_area,id_estado,id_org,id_tipo_actividad,ciclo_lectivo,cuatrimestre,titulo) VALUES(9,18981,4,11,1,3,2016,1,'Reforma en sw de laboratorios informaticos de la UTN-FRRe');


INSERT INTO PlanDeTrabajo(id_plan_de_trabajo,id_ps,fecha_presentacion) VALUES(1,1,'2016-10-15');
INSERT INTO PlanDeTrabajo(id_plan_de_trabajo,id_ps,fecha_presentacion,fecha_aprob_desaprob) VALUES(2,2,'2016-05-26','2016-10-15');
INSERT INTO PlanDeTrabajo(id_plan_de_trabajo,id_ps,fecha_presentacion,fecha_aprob_desaprob,observaciones) VALUES(3,3,'2014-09-20','2015-04-12','Ha excedido el plazo para presentar informe');
INSERT INTO PlanDeTrabajo(id_plan_de_trabajo,id_ps,fecha_presentacion,fecha_aprob_desaprob,observaciones) VALUES(4,4,'2015-04-12','2015-09-02','No cumple aun con horas catedras establecidas para evaluar');
INSERT INTO PlanDeTrabajo(id_plan_de_trabajo,id_ps,fecha_presentacion,fecha_aprob_desaprob) VALUES(5,5,'2015-09-02','2016-05-26');
INSERT INTO PlanDeTrabajo(id_plan_de_trabajo,id_ps,fecha_presentacion,fecha_aprob_desaprob) VALUES(6,6,'2013-05-05','2014-06-01');
INSERT INTO PlanDeTrabajo(id_plan_de_trabajo,id_ps,fecha_presentacion,fecha_aprob_desaprob) VALUES(7,7,'2013-10-18','2014-06-01');
INSERT INTO PlanDeTrabajo(id_plan_de_trabajo,id_ps,fecha_presentacion,fecha_aprob_desaprob) VALUES(8,8,'2015-09-02','2016-05-26');
INSERT INTO PlanDeTrabajo(id_plan_de_trabajo,id_ps,fecha_presentacion,fecha_aprob_desaprob,observaciones) VALUES(9,9,'2016-05-26','2016-10-15','No cumple con atribuciones del ISI, Beca de servicio no es valida');


INSERT INTO InformeFinal(id_informe_final,id_ps,fecha_presentacion) VALUES(1,5,'2016-10-15');
INSERT INTO InformeFinal(id_informe_final,id_ps,fecha_presentacion,fecha_aprob_desaprob,observaciones) VALUES(2,6,'2014-09-20','2015-04-12','Aprueba Practica supervisada con nota 8');
INSERT INTO InformeFinal(id_informe_final,id_ps,fecha_presentacion,fecha_aprob_desaprob,observaciones) VALUES(3,7,'2014-09-20','2015-04-12','No ha vuelto ha presentar el informe y concluyo el plazo determinado para aprobar el mismo');
INSERT INTO InformeFinal(id_informe_final,id_ps,fecha_presentacion) VALUES(4,8,'2016-10-15');


UPDATE PS SET id_plan=1 WHERE id_ps=1;
UPDATE PS SET id_plan=2 WHERE id_ps=2;
UPDATE PS SET id_plan=3 WHERE id_ps=3;
UPDATE PS SET id_plan=4 WHERE id_ps=4;
UPDATE PS SET id_plan=5 WHERE id_ps=5;
UPDATE PS SET id_plan=6 WHERE id_ps=6;
UPDATE PS SET id_plan=7 WHERE id_ps=7;
UPDATE PS SET id_plan=8 WHERE id_ps=8;
UPDATE PS SET id_plan=9 WHERE id_ps=9;
UPDATE PS SET id_informe=1 WHERE id_ps=5;
UPDATE PS SET id_informe=2 WHERE id_ps=6;
UPDATE PS SET id_informe=3 WHERE id_ps=7;
UPDATE PS SET id_informe=4 WHERE id_ps=8;
