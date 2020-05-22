--16/18/2019
--Cambio contactabiliad fecha creacion y actualizacion y Contrato se agresa una columna gestion
--Autor Kevin Chamorro
ALTER TABLE tb_mi_contactabilidad ALTER COLUMN fecha_creacion TYPE timestamp USING fecha_creacion::timestamp;
ALTER TABLE tb_mi_contactabilidad ALTER COLUMN fecha_actualizacion TYPE timestamp USING fecha_actualizacion::timestamp;
ALTER TABLE tb_mi_contrato ADD COLUMN gestion varchar(256);