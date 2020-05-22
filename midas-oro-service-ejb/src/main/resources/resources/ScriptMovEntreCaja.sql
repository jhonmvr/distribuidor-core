--24/7/2019
--MOVIMIENTO ENTRE CAJAS, SE AGREGA UNA COLUMNA PARA INDICAR LA CAJA DE DESTINO
--AUTOR: KEVIN CHAMORRO
CREATE TABLE tb_mi_mov_entre_caja(
   id numeric(10) PRIMARY KEY,
   id_agencia_origen numeric(10) null,
   id_agencia_destino numeric(10) null,
   saldo_agencia_origen numeric(16,6) NOT NULL,
   saldo_agencia_destino numeric(16,6) NOT NULL,
   estado VARCHAR (32) NOT NULL,
   fecha_creacion date NOT NULL,
   fecha_actualizacion date,
   usuario_creacion varchar(32) NOT NULL,
   usuario_actualizacion varchar(32)
);

ALTER TABLE tb_mi_mov_entre_caja
    ADD CONSTRAINT fk_MovEntreCaja_AgeOri FOREIGN KEY (id_agencia_origen) REFERENCES tb_mi_agencia (id);
ALTER TABLE tb_mi_mov_entre_caja
    ADD CONSTRAINT fk_MovEntreCaja_AgeDes FOREIGN KEY (id_agencia_destino) REFERENCES tb_mi_agencia (id);

CREATE SEQUENCE remidasoro.seq_mov_entre_caja INCREMENT BY 1 START 1;

INSERT INTO remidasoro.tb_mi_cuenta (id,codigo,nombre,fecha_creacion,fecha_actulizacion,usuario_creacion,usuario_actualizacion,estado)
	VALUES (11,'MOV_ENTRE_CAJA','MOVIMIENTO ENTRE CAJAS','2019-07-25','2019-07-25','admin','admin','ACT');

-- Auto-generated SQL script #201907261741
INSERT INTO public.tb_seg_aplicacion_opcion (id_aplicacion,id_opcion,nombre,descripcion,tipo,estado,usr_ins,fch_ins,usr_upd,fch_upd,id_opcion_padre,url,nivel,icono) VALUES 
(1,15,'Movimientro entre cajas','Movimientro entre cajas','S','A','admin','2019-07-15 00:00:00.000',NULL,NULL,9,'/midas-oro/movimientos/entre-cajas/list',2,'flaticon-menu-button');

-- Auto-generated SQL script #201907261741
INSERT INTO public.tb_seg_rol_aplicacion_opcion (id_secuencia,id_rol,id_aplicacion,id_opcion)
	VALUES (15,1,1,15);


