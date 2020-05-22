--14/18/2019
--Script menu habilitantes
--Autor Kevin Chamorro
-- Auto-generated SQL script #201908141225
INSERT INTO tb_seg_aplicacion_opcion (id_aplicacion,id_opcion,nombre,descripcion,tipo,estado,usr_ins,fch_ins,id_opcion_padre,url,nivel,icono)
	VALUES (1,24,'Habilitantes','Habilitantes','S','A','admin','2018-11-14 00:00:00.000',9,'/midas-oro/habilitante/list',2,'flaticon-menu-button');

-- Auto-generated SQL script #201908141227
INSERT INTO tb_seg_rol_aplicacion_opcion (id_secuencia,id_rol,id_aplicacion,id_opcion)
	VALUES (43,1,1,24);