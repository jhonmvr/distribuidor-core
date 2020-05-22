--7/8/2019
--SCRIP CLIENTE COLUMNAS ESTANDAR Y FECHAS
--AUTOR: KEVIN CHAMORRO
ALTER TABLE tb_mi_cliente ADD COLUMN apellido VARCHAR(200);
ALTER TABLE tb_mi_cliente ADD COLUMN estado VARCHAR(32);
ALTER TABLE tb_mi_cliente ADD COLUMN fecha_creacion timestamp;
ALTER TABLE tb_mi_cliente ADD COLUMN fecha_actualizacion timestamp;
ALTER TABLE tb_mi_cliente ADD COLUMN usuario_creacion VARCHAR(32);
ALTER TABLE tb_mi_cliente ADD COLUMN usuario_actualizacion VARCHAR(32);

ALTER TABLE tb_mi_cliente ALTER COLUMN fecha_nacimiento TYPE date USING fecha_nacimiento::date;
ALTER TABLE tb_mi_cliente ALTER COLUMN fecha_creacion TYPE date USING fecha_creacion::date;
ALTER TABLE tb_mi_cliente ALTER COLUMN fecha_actualizacion TYPE date USING fecha_actualizacion::date;