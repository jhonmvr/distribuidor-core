ALTER TABLE remidasoro.tb_mi_agente ADD nacionalidad varchar(50) NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD edad int NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD COLUMN tipo_identificacion VARCHAR(50) NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD estado_civil varchar(25) NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD fecha_de_nacimiento date NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD lugar_de_nacimineto varchar(50) NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD nivel_de_estudios varchar(50) NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD pais varchar(50) NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD sector varchar(50) NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD calle_principal varchar(50) NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD interseccion varchar(50) NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD referencia_ubicacion varchar(50) NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD numero_domicilio varchar(25) NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD id_ciudad bpchar(3) NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD id_provincia bpchar(2) NULL;
ALTER TABLE remidasoro.tb_mi_agente ADD CONSTRAINT fk_age_canton_id FOREIGN KEY (id_provincia,id_ciudad)
REFERENCES remidasoro.canton(provinciaid,cantonid);
