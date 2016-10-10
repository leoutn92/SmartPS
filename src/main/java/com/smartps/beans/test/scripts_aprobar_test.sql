use smartpsdb;
insert into estado values (1,"Plan presentado","Plan de PS presentado aún no evaluado por el consejo");
insert into estado values (2,"Plan observado","Plan de PS visado por el consejo con correcciones pendientes");
insert into estado values (3,"Plan aprobado","Plan de PS aprobado por el consejo directivo");
insert into estado values (4,"Plan rechazado","Plan de PS rechazado de manera definitiva por el consejo");
insert into estado values (5,"Plan vencido","Plan de PS vencido");
insert into estado values (6,"Informe presentado","Informe final de PS presentado aún no evaluado por el consejo");
insert into estado values (7,"Informe observado","Informe final de PS visado por el conseco con correciones pendientes");
insert into estado values (8,"Informe aprobado","Informe final de PS aprobado por el consejo directivo");
insert into estado values (9,"Informe vencido","Informe final de PS vencido");
insert into estado values (10,"PS aprobada","PS aprobada");
insert into estado values (11,"PS cancelada","PS cancelada por alumno u otros motivos");
insert into alumno values(18189,"Vallejos Lucas Matias",0);
insert into alumno values(18671,"Aquino Keller Leonel",0);
insert into alumno values(15753,"Ali Mari",0);
insert into alumno values(16156,"Grinberg David",0);
/*que seria la columna ciclo lectivo???*/
insert into area values (1,"Desarrollo y programación","Teclear todo el dia");
insert into area values (2,"Redes","Mantenimiento");
insert into organizacion values(1,"UTN Facultad Regional Resistencia","Trabajo de investigación");
insert into organizacion values(2,"Globant","We create digital journeys");
insert into tipoactividad values(1,"Relacion Laboral","Jornada completa de 16 horas");
insert into tipoactividad values(2,"Proyecto independiente","Trabaja desde la comodidad de tu casa");
insert into ps(legajo,id_area,id_estado,id_org,id_tipo_actividad,ciclo_lectivo,cuatrimestre,titulo,nro_disposicion) values (18671,1,1,1,1,2016,2,"prueba",0);
insert into plandetrabajo(id_ps,fecha_presentacion) values (1,"2016-9-1")
