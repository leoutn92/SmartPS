insert into Estado values (1,'Plan presentado','Plan de PSpresentado aun no evaluado por el consejo');
insert into Estado values (2,'Plan observado','Plan de PS visado por el consejo con correcciones pendientes');
insert into Estado values (3,'Plan aprobado','Plan de PS aprobado por el consejo directivo');
insert into Estado values (4,'Plan rechazado','Plan de PS rechazado de manera definitiva por el consejo');
insert into Estado values (5,'Plan vencido','Plan de PS vencido');
insert into Estado values (6,'Informe presentado','Informe final de PS presentado aun no evaluado por el consejo');
insert into Estado values (7,'Informe observado','Informe final de PS visado por el conseco con correciones pendientes');
insert into Estado values (8,'Informe aprobado','Informe final de PS aprobado por el consejo directivo');
insert into Estado values (9,'Informe vencido','Informe final de PS vencido');
insert into Estado values (10,'PS aprobada','PS aprobada');
insert into Estado values (11,'PS cancelada','PS cancelada por Alumno u otros motivos');

insert into Alumno values(18189,'Vallejos Lucas Matias',2010); /*que seria la columna ciclo lectivo???*/
insert into Alumno values(17848,'Seniquiel Matias',2009);

insert into Area values (1,'Desarrollo y programacion','Teclear todo el dia');
insert into Area values (2,'Redes','Mantenimiento');

insert into Organizacion values(1,'UTN Facultad Regional Resistencia','Trabajo de investigacion');
insert into Organizacion values(2,'Globant','We create digital journeys');
insert into Organizacion values(3,'DesarrollosNEA','Dedicada al desarrollo de sw y administracion de infraestructura');
insert into Organizacion values(4,'VGMSistemas','Dedicada al desarrollo de sistemas');

insert into TipoActividad values(1,'Pasantia','Acreditar una relacion laboral o pasantia rentada en empresas del medio');
insert into TipoActividad values(2,'Proyecto Final','Desarrollo de proyecto final mediante convenio especifico con terceros');
insert into TipoActividad values(3,'Grupos de Investigacion','Participacion activa en grupos de investigacion pertenecientes a instituciones de nivel academico reconocido');
insert into TipoActividad values(4,'Tareas de ingenieria','Desarrollar tareas de ing en ambito de ONG, instituciones y/o empresas productivas o de servicios publicas o privadas');

insert into PS(id_ps,legajo,id_area,id_estado,id_org,id_tipo_actividad,ciclo_lectivo,cuatrimestre,titulo) values(1,17848,1,1,1,3,2016,2,'evaluacion de calidad en repositorios institucionales');
insert into PS(id_ps,legajo,id_area,id_estado,id_org,id_tipo_actividad,ciclo_lectivo,cuatrimestre,titulo) values(2,18189,2,3,3,1,2016,2,'Implementacion de protocolos de transmicion');

insert into PlanDeTrabajo(id_plan_de_trabajo,id_ps,fecha_presentacion) values(1,1,'2016-09-07');
insert into PlanDeTrabajo(id_plan_de_trabajo,id_ps,fecha_presentacion) values(2,2,'2016-09-26');