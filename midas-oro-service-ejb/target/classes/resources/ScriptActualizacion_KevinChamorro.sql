--AUTOR: KEVIN CHAMORRO
--FECHA: 08/06/2019
--DESCRIPCION: SE CREA LA TABLA ABONOS
--APROPADO POR: JHON

CREATE TABLE tb_mi_abono (
	id numeric(10) NOT NULL,
	id_contrato numeric(10),
	id_cliente numeric(10) NOT NULL,
	tipo_cuenta character varying(64),
	forma_pago character varying(64),
	nombre_banco character varying(64),
	numero_cuenta character varying(32),
	valor numeric(10,2) NOT NULL,
	estado character varying(32) NOT NULL,
	fecha_creacion date NOT NULL,
	fecha_actualizacion date NOT NULL,
	usuario_creacion character varying(64),
	usuario_actualizacion character varying(64)
);

ALTER TABLE tb_mi_abono ADD CONSTRAINT tb_mi_abono_pk PRIMARY KEY (id);

ALTER TABLE tb_mi_abono
    ADD CONSTRAINT fk_abono_contrato FOREIGN KEY ( id_contrato )
        REFERENCES tb_mi_contrato ( id );
        
ALTER TABLE tb_mi_abono
    ADD CONSTRAINT fk_abono_cliente FOREIGN KEY ( id_cliente )
        REFERENCES tb_mi_cliente ( id );

comment on column tb_mi_abono.id is 'PK';
comment on column tb_mi_abono.id_contrato is 'Referencia con contrato';
comment on column tb_mi_abono.id_cliente is 'Referencia con cliente';
comment on column tb_mi_abono.tipo_cuenta is 'Tipo de cuenta, ahorros, corriente';
comment on column tb_mi_abono.forma_pago is 'Forma de pago, efectivo, cheque, transferencia';
comment on column tb_mi_abono.nombre_banco is 'Nombre del banco';
comment on column tb_mi_abono.numero_cuenta is 'Numero de cuenta del banco';
comment on column tb_mi_abono.valor is 'Estado del abono';
comment on column tb_mi_abono.estado is 'Estado del abono';
comment on column tb_mi_abono.fecha_creacion is 'Fecha de creacion del registro';
comment on column tb_mi_abono.fecha_actualizacion is 'Fecha de actualizacion del registro';
comment on column tb_mi_abono.usuario_creacion is 'Usuario que creacion del registro';
comment on column tb_mi_abono.usuario_actualizacion is 'Usuario que actualiza del registro';

CREATE SEQUENCE seq_abono START WITH 1;

--AUTOR: KEVIN CHAMORRO
--FECHA: 09/06/2019
--DESCRIPCION: SE AGREGA RESTRICCION UNICA A LA COLUMNA IDENTIFICACION DE LA TABLA CLIENTE
--APROPADO POR: 

ALTER TABLE tb_mi_cliente
ADD CONSTRAINT cliente_identificacion UNIQUE (identificacion);

--AUTOR: KEVIN CHAMORRO
--FECHA: 21/06/2019
--DESCRIPCION: SE AGREGA UNA COLUMNA PROCESO A TBMICONTRATO Y CODIGO NOT NULL
--APROPADO POR: Luis Tamayo

ALTER TABLE tb_mi_contrato
ADD column proceso character varying(128);
comment on column tb_mi_contrato.proceso is 'Proceso por el cual llego el contrato a ese estado';

ALTER TABLE tb_mi_contrato ALTER COLUMN codigo SET NOT NULL;

-- Script para actualizar la tb_mi_contrato columna codigo a unica
-- Elaborado por: Kevin Chamorro
-- Fecha Creacion: 05/06/2019
-- Autorizado por: Paola Andrade

ALTER TABLE tb_mi_contrato ADD CONSTRAINT contrato_codigo UNIQUE (codigo);

--AUTOR: KEVIN CHAMORRO
--FECHA: 21/06/2019
--DESCRIPCION: CAMBIO DE NOMBRE FUNDA.UBICACION => FUNDA.IDBODEGA RELACIONADO CON BODEGA
--APROPADO POR: Luis Tamayo

ALTER TABLE tb_mi_funda 
RENAME COLUMN ubicacion TO id_bodega;
comment on column tb_mi_funda.id_bodega is 'Relacion funda con bodega';

ALTER TABLE tb_mi_funda 
	ALTER COLUMN id_bodega TYPE numeric(10)
	USING CAST(id_bodega AS integer);

ALTER TABLE tb_mi_funda
    ADD CONSTRAINT fk_fun_bodega_id FOREIGN KEY ( id_bodega )
        REFERENCES tb_mi_bodega ( id );

--AUTOR: KEVIN CHAMORRO
--FECHA: 21/06/2019
--DESCRIPCION: CODIGO UNICO PARA JOYA Y NOT NULL
--APROPADO POR: 

ALTER TABLE tb_mi_joya
ADD CONSTRAINT codigo_joya_unico UNIQUE (codigo_joya);

ALTER TABLE tb_mi_joya ALTER COLUMN codigo_joya SET NOT NULL;

--AUTOR: KEVIN CHAMORRO
--FECHA: 26/06/2019
--DESCRIPCION: SE CREA LA TABLA JOYA/LOTE
--APROPADO POR: JHON

CREATE TABLE tb_mi_joya_lote (
	id numeric(10) NOT NULL,
	id_joya numeric(10) NOT NULL,
	id_lote numeric(10) NOT NULL,
	estado character varying(32) NOT NULL,
	fecha_creacion date NOT NULL,
	fecha_actualizacion date NOT NULL,
	usuario_creacion character varying(64),
	usuario_actualizacion character varying(64)
);

ALTER TABLE tb_mi_joya_lote ADD CONSTRAINT tb_mi_joya_lote_pk PRIMARY KEY (id);

ALTER TABLE tb_mi_joya_lote
    ADD CONSTRAINT fk_joya_lote_joya_id_joya FOREIGN KEY ( id_joya )
        REFERENCES tb_mi_joya ( id );
        
ALTER TABLE tb_mi_joya_lote
    ADD CONSTRAINT fk_joya_lote_lote_id_lote FOREIGN KEY ( id_lote )
        REFERENCES tb_mi_lote ( id );

comment on column tb_mi_joya_lote.id is 'PK';
comment on column tb_mi_joya_lote.id_joya is 'Referencia con joya';
comment on column tb_mi_joya_lote.id_lote is 'Referencia con lote';
comment on column tb_mi_joya_lote.estado is 'Estado del abono';
comment on column tb_mi_joya_lote.fecha_creacion is 'Fecha de creacion del registro';
comment on column tb_mi_joya_lote.fecha_actualizacion is 'Fecha de actualizacion del registro';
comment on column tb_mi_joya_lote.usuario_creacion is 'Usuario que creacion del registro';
comment on column tb_mi_joya_lote.usuario_actualizacion is 'Usuario que actualiza del registro';

CREATE SEQUENCE seq_joya_lote START WITH 1;

--AUTOR: KEVIN CHAMORRO
--FECHA: 26/06/2019
--DESCRIPCION: REGISTROS PARA RETAZAR JOYA
--APROPADO POR: JHON

INSERT INTO remidasoro.tb_mi_agencia
(id, codigo, direccion, encargado, telefono, estado)
VALUES(100, 'AGENCIA-MATRIZ', 'MIDAS-ORO', 1, '0000000', 'ACT');

INSERT INTO remidasoro.tb_mi_bodega
(id, id_agencia, descripcion, nombre, estado, fecha_creacion, fecha_actualizacion, usuario_creacion, usuario_actualizacion)
VALUES(100, 100, 100, 'VITRINA MATRIZ', 'ACT', current_date, current_date, 'KEVIN', 'KEVIN');

INSERT INTO remidasoro.tb_mi_bodega
(id, id_agencia, descripcion, nombre, estado, fecha_creacion, fecha_actualizacion, usuario_creacion, usuario_actualizacion)
VALUES(101, 100, 101, 'LOTE MATRIZ', 'ACT', current_date, current_date, 'KEVIN', 'KEVIN');

INSERT INTO remidasoro.tb_mi_lote
(id, nombre_lote, fecha_creacion, fecha_cierre, usuario_creacion, usuario_actualizacion, estado, id_tipo_oro, identificador, responsable, id_agencia, fecha_actualizacion)
VALUES(nextval('seq_lote'), 'LOTE 18K', current_date, current_date, 'KEVIN', 'KEVIN', 'ACT', 1, 'LOTE_18K', 'KEVIN', 100, current_date);

INSERT INTO remidasoro.tb_mi_lote
(id, nombre_lote, fecha_creacion, fecha_cierre, usuario_creacion, usuario_actualizacion, estado, id_tipo_oro, identificador, responsable, id_agencia, fecha_actualizacion)
VALUES(nextval('seq_lote'), 'LOTE 16K', current_date, current_date, 'KEVIN', 'KEVIN', 'ACT', 1, 'LOTE_16K', 'KEVIN', 100, current_date);

INSERT INTO remidasoro.tb_mi_lote
(id, nombre_lote, fecha_creacion, fecha_cierre, usuario_creacion, usuario_actualizacion, estado, id_tipo_oro, identificador, responsable, id_agencia, fecha_actualizacion)
VALUES(nextval('seq_lote'), 'LOTE 15K', current_date, current_date, 'KEVIN', 'KEVIN', 'ACT', 1, 'LOTE_15K', 'KEVIN', 100, current_date);

INSERT INTO remidasoro.tb_mi_lote
(id, nombre_lote, fecha_creacion, fecha_cierre, usuario_creacion, usuario_actualizacion, estado, id_tipo_oro, identificador, responsable, id_agencia, fecha_actualizacion)
VALUES(nextval('seq_lote'), 'VITRINA', current_date, current_date, 'KEVIN', 'KEVIN', 'ACT', 1, 'VITRINA', 'KEVIN', 100, current_date);

--AUTOR: KEVIN CHAMORRO
--FECHA: 27/06/2019
--DESCRIPCION: SE AGREGA TIPO A BODEGA Y LOTE
--APROPADO POR: JHON

ALTER TABLE tb_mi_bodega
ADD column tipo_bodega character varying(32);
comment on column tb_mi_bodega.tipo_bodega is 'Tipo de bodega vidrina o lote';

ALTER TABLE tb_mi_lote
ADD column tipo_lote character varying(32);
comment on column tb_mi_lote.tipo_lote is 'Tipo de lote vidrina o lote';

--AUTOR: KEVIN CHAMORRO
--FECHA: 27/06/2019
--DESCRIPCION: SE AGREGA TIPO A BODEGA Y LOTE
--APROPADO POR: JHON

-- Drop table

-- DROP TABLE remidasoro.tb_mi_historico_joya;

CREATE TABLE remidasoro.tb_mi_historico_joya (
	id numeric(10) NOT NULL,
	id_joya numeric(10) NOT NULL,
	comentario varchar(200) NULL,
	id_tipo_oro numeric(10) null,
	peso_neto numeric(10,6) NULL,
	peso_bruto numeric(10,6) NULL,
	descuento numeric(10,6) NULL,
	estado varchar(20) NULL,
	fecha_creacion date not null,
	fecha_actualizacion date not null,
	usuario_creacion varchar(64) null,
	usuario_actualizacion varchar(64) null,
	CONSTRAINT pk_historico_joya PRIMARY KEY (id),
	CONSTRAINT fk_his_joya_id FOREIGN KEY (id_joya) REFERENCES tb_mi_joya(id)
);

--AUTOR: KEVIN CHAMORRO
--FECHA: 28/06/2019
--DESCRIPCION: SE AGREGA CONSTRAIN PK A TbMiEgreso
--APROPADO POR:

ALTER TABLE tb_mi_egreso ADD PRIMARY KEY (id);

--FECHA: 10/07/2019
--DESCRIPCION: SE CREA LA TABLA VENTA-LOTE
--APROPADO POR: JHON

CREATE TABLE tb_mi_venta_lote (
	id numeric(10) NOT NULL,
	id_cliente numeric(10) NOT NULL,
	codigo varchar(16) unique NOT NULL,
	medida_conversion numeric(10,6) NOT NULL,
	precio_onza_troy numeric(10,6) NOT NULL,
	descuento numeric(10,6) NOT NULL,
	precio_gramo numeric(10,6) NOT NULL,
	descripcion_forma_pago varchar(200) NOT NULL,
	estado varchar(32) NOT NULL,
	fecha_creacion date NOT NULL,
	fecha_actualizacion date NOT NULL,
	usuario_creacion varchar(64) NOT NULL,
	usuario_actualizacion varchar(64) NOT NULL
);

ALTER TABLE tb_mi_venta_lote ADD CONSTRAINT tb_mi_venta_lote_pk PRIMARY KEY (id);
        
ALTER TABLE tb_mi_venta_lote
    ADD CONSTRAINT fk_venLot_cliente FOREIGN KEY ( id_cliente )
        REFERENCES tb_mi_cliente ( id );

comment on column tb_mi_venta_lote.id is 'PK';
comment on column tb_mi_venta_lote.id_cliente is 'Referencia con cliente';
comment on column tb_mi_venta_lote.codigo is 'Codigo de venta';
comment on column tb_mi_venta_lote.medida_conversion is 'Constante de conversión gramo de oro';
comment on column tb_mi_venta_lote.precio_onza_troy is 'Precio internacional del oro puro (24k)';
comment on column tb_mi_venta_lote.descuento is 'Porcentaje de descuento pactado con el comprador';
comment on column tb_mi_venta_lote.precio_gramo is 'Valor del gramo de oro que se obtiene a partir del valor de la onza troy';
comment on column tb_mi_venta_lote.descripcon_forma_pago is 'descripcion de la forma de pago';
comment on column tb_mi_venta_lote.estado is 'Estado de venta lote';
comment on column tb_mi_venta_lote.fecha_creacion is 'Fecha de creacion del registro';
comment on column tb_mi_venta_lote.fecha_actualizacion is 'Fecha de actualizacion del registro';
comment on column tb_mi_venta_lote.usuario_creacion is 'Usuario que creacion del registro';
comment on column tb_mi_venta_lote.usuario_actualizacion is 'Usuario que actualiza del registro';

CREATE SEQUENCE seq_venta_lote START WITH 1;

--FECHA: 10/07/2019
--DESCRIPCION: SE AGREGA LA COLUMNA ID VENTA LOTE PARA RELACIONAR CON LOTE
--APROPADO POR: JHON

ALTER TABLE tb_mi_lote
ADD column id_venta_lote numeric(10) null;
comment on column tb_mi_lote.id_venta_lote is 'Relacion con venta lote';

ALTER TABLE tb_mi_lote
    ADD CONSTRAINT fk_lote_venta_lote FOREIGN KEY ( id_venta_lote )
        REFERENCES tb_mi_venta_lote ( id );
       
       
--FECHA: 12/07/2019
--DESCRIPCION: ACTUALIZACION DE TBMIJOYA PARA ADAPTAR A RESUMEN DE VENTA
--APROPADO POR: LUIS TAMAYO - DROP JHON

ALTER TABLE tb_mi_joya
drop column id_perfeccionamiento
-- VENTA JOYA
ALTER TABLE tb_mi_joya
ADD column peso_bruto_retazado numeric(10,6) null;
comment on column tb_mi_joya.peso_bruto_retazado is 'Peso bruto retazado de la joya';

ALTER TABLE tb_mi_joya
ADD column descuento_retazado numeric(10,6) null;
comment on column tb_mi_joya.descuento_retazado is 'Descuento retazado de la joya';

ALTER TABLE tb_mi_joya
ADD column peso_neto_retazado numeric(10,6) null;
comment on column tb_mi_joya.peso_neto_retazado is 'Peso neto retazado de la joya';

-- VENTA LOTE
ALTER TABLE tb_mi_lote
ADD column peso_bruto_compra numeric(10,6) null;
comment on column tb_mi_lote.peso_bruto_compra is 'Sumatoria del peso bruto compra de las joyas contenidas';

ALTER TABLE tb_mi_lote
ADD column descuento_compra numeric(10,6) null;
comment on column tb_mi_lote.descuento_compra is 'Sumatoria del descuento compra de las joyas contenidas';

ALTER TABLE tb_mi_lote
ADD column peso_neto_compra numeric(10,6) null;
comment on column tb_mi_lote.peso_neto_compra is 'Sumatoria del peso neto compras de las joyas contenidas';

ALTER TABLE tb_mi_lote
ADD column peso_bruto_retazado numeric(10,6) null;
comment on column tb_mi_lote.peso_bruto_retazado is 'Sumatoria del peso bruto retazado de las joyas contenidas';

ALTER TABLE tb_mi_lote
ADD column descuento_retazado numeric(10,6) null;
comment on column tb_mi_lote.descuento_retazado is 'Sumatoria del descuento retazado de las joyas contenidas';

ALTER TABLE tb_mi_lote
ADD column peso_neto_retazado numeric(10,6) null;
comment on column tb_mi_lote.peso_neto_retazado is 'Sumatoria del peso neto retazado de las joyas contenidas';

ALTER TABLE tb_mi_lote
ADD column precio_compra numeric(10,6) null;
comment on column tb_mi_lote.precio_compra is 'Sumatoria del prcio de compra de las joyas contenidas';

ALTER TABLE tb_mi_lote
ADD column precio_venta numeric(10,6) null;
comment on column tb_mi_lote.precio_venta is 'Precio al que se vendio el lote';

ALTER TABLE tb_mi_lote
ADD column utilidad numeric(10,6) null;
comment on column tb_mi_lote.utilidad is 'Utilidad del lote';

ALTER TABLE tb_mi_lote
ADD column ley varchar(16) null;
comment on column tb_mi_lote.ley is 'Estandar con el que se cotiza el oro';

ALTER TABLE tb_mi_lote
ADD column costo_por_gramo numeric(10,6) null;
comment on column tb_mi_lote.costo_por_gramo is 'Valor del gramo de oro';

ALTER TABLE tb_mi_lote
ADD column total_gramos numeric(10,6) null;
comment on column tb_mi_lote.total_gramos is 'Total de gramos del lote';

ALTER TABLE tb_mi_lote
ADD column peso_neto_vendido numeric(10,6) null;
comment on column tb_mi_lote.peso_neto_vendido is 'Peso neto vendido';

--FECHA: 12/07/2019
--DESCRIPCION: ACTUALIZACION DE TBMI
--APROPADO POR: LUIS TAMAYO - DROP JHON

ALTER TABLE tb_mi_agencia
ADD column seq_vl varchar(16) null;
comment on column tb_mi_agencia.seq_vl is 'Secuencia para generar codigo de  venta lote';

update tb_mi_agencia
set seq_vl = 'seq_matriz_vl'
where id = 100

CREATE SEQUENCE seq_matriz_vl START WITH 1;

ALTER TABLE tb_mi_lote
ALTER COLUMN ley TYPE varchar(16)

ALTER TABLE tb_mi_venta_lote
ALTER COLUMN precio_gramo TYPE numeric(16,6)

ALTER TABLE tb_mi_lote
ALTER COLUMN precio_venta TYPE numeric(16,6)

--FECHA: 18/07/2019
--DESCRIPCION: ACTUALIZACION DE TBMIJOYA
--APROPADO POR: LUIS TAMAYO

ALTER TABLE tb_mi_joya
ADD column precio_venta numeric(10,6) null;
comment on column tb_mi_joya.precio_venta is 'Precio vendido de la joya';

ALTER TABLE tb_mi_joya
ALTER COLUMN precio_venta TYPE numeric(16,6)


ALTER TABLE remidasoro.tb_mi_lote ALTER COLUMN precio_compra TYPE numeric(16,6) USING precio_compra::numeric;
ALTER TABLE remidasoro.tb_mi_lote ALTER COLUMN peso_neto_retazado TYPE numeric(16,6) USING peso_neto_retazado::numeric;
ALTER TABLE remidasoro.tb_mi_lote ALTER COLUMN descuento_retazado TYPE numeric(16,6) USING descuento_retazado::numeric;
ALTER TABLE remidasoro.tb_mi_lote ALTER COLUMN peso_bruto_retazado TYPE numeric(16,6) USING peso_bruto_retazado::numeric;
ALTER TABLE remidasoro.tb_mi_lote ALTER COLUMN peso_neto_compra TYPE numeric(16,6) USING peso_neto_compra::numeric;
ALTER TABLE remidasoro.tb_mi_lote ALTER COLUMN descuento_compra TYPE numeric(16,6) USING descuento_compra::numeric;
ALTER TABLE remidasoro.tb_mi_lote ALTER COLUMN peso_bruto_compra TYPE numeric(16,6) USING peso_bruto_compra::numeric;
ALTER TABLE remidasoro.tb_mi_lote ALTER COLUMN utilidad TYPE numeric(16,6) USING utilidad::numeric;
ALTER TABLE remidasoro.tb_mi_lote ALTER COLUMN costo_por_gramo TYPE numeric(16,6) USING costo_por_gramo::numeric;
ALTER TABLE remidasoro.tb_mi_lote ALTER COLUMN total_gramos TYPE numeric(16,6) USING total_gramos::numeric;
ALTER TABLE remidasoro.tb_mi_lote ALTER COLUMN peso_neto_vendido TYPE numeric(16,6) USING peso_neto_vendido::numeric;

ALTER TABLE remidasoro.tb_mi_joya ALTER COLUMN peso_neto_retazado TYPE numeric(16,6) USING peso_neto_retazado::numeric;
ALTER TABLE remidasoro.tb_mi_joya ALTER COLUMN descuento_retazado TYPE numeric(16,6) USING descuento_retazado::numeric;
ALTER TABLE remidasoro.tb_mi_joya ALTER COLUMN peso_bruto_retazado TYPE numeric(16,6) USING peso_bruto_retazado::numeric;
ALTER TABLE remidasoro.tb_mi_joya ALTER COLUMN descuento TYPE numeric(16,6) USING descuento::numeric;
ALTER TABLE remidasoro.tb_mi_joya ALTER COLUMN peso_bruto TYPE numeric(16,6) USING peso_bruto::numeric;
ALTER TABLE remidasoro.tb_mi_joya ALTER COLUMN peso TYPE numeric(16,6) USING peso::numeric;
ALTER TABLE remidasoro.tb_mi_joya ALTER COLUMN valor TYPE numeric(16,6) USING valor::numeric;