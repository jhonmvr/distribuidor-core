ALTER TABLE tb_mi_folleto_liquidacion 
ALTER COLUMN nombre_folleto DROP NOT NULL;
ALTER TABLE tb_mi_agente 
ALTER COLUMN segundo_nombre DROP NOT NULL;
ALTER TABLE tb_mi_agente 
ALTER COLUMN segundo_apellido DROP NOT NULL;
