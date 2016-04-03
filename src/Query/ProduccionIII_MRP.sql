drop database ProduccionMRP;

Create Database ProduccionMRP;

Use ProduccionMRP;

Create Table Materiales(
	id_material int primary key auto_increment not null,
	nombre_material varchar(250)not null,
	tiempo_espera int not null,
	tipo_espera varchar(20) not null,
	cantidad_lote int not null,
	cantidad_material int not null,
	estado_material boolean default true
);

Create Table Materiales_Dependencias(
	id_materiales_dependencia int primary key auto_increment not null,
	id_material_principal int  not null,
	id_material_dependencia int not null,
	cantidad_material_dependencia int not null,
	estado_uso boolean not null default true,
	foreign key(id_material_principal)references Materiales(id_material),
	foreign key(id_material_dependencia)references Materiales(id_material)
);

create table OrdenProduccion(
	IdOrden int auto_increment primary key not null,
	IdMaterial int not null,
	CantidadSolicitud int not null,
	Fecha date not null,
	Foreign key(IdMaterial) references Materiales(id_material)
);

Create Table Entradas_Programadas(-- Preordenes programadas, no se ubican en el campo de ordenes debido a que fueron pedidas antes de realizar el MRP
	id_entradas_programadas int primary key auto_increment not null,
	id_material int not null,
	fecha_entrega date not null,
	cantidad_solicitada int unsigned not null,
	IdOrden int not null,
	foreign key (id_material)references Materiales(id_material),
	foreign key(IdOrden) references OrdenProduccion(IdOrden)
);


delimiter //
create Procedure Guardar_EntradasProgramadas(
id_material int,fecha_entrega date,cantidad_solicitada int,idOrden int, out salida int)
Begin
	insert into Entradas_Programadas(id_material,fecha_entrega,cantidad_solicitada,IdOrden)
    values(id_material,fecha_entrega,cantidad_solicitada,idOrden);
	select id_entradas_programadas into salida from Entradas_Programadas order by id_entradas_programadas desc limit 1;
end //

delimiter //
create Procedure Guardar_Material(
nombre_material varchar(250),tiempo_espera int,tipo_espera varchar(20),cantidad_lote int,
cantidad_material int,estado_material boolean, out salida int)
Begin

    insert into Materiales(nombre_material,tiempo_espera,tipo_espera,cantidad_lote,cantidad_material,estado_material)
    values(nombre_material,tiempo_espera, tipo_espera,cantidad_lote,cantidad_material,estado_material);
	select id_material into salida from Materiales order by id_material desc limit 1;
end //

delimiter //
create Procedure Guardar_OrdenProduccion(
id_material int,cantidad_solicitada int,fecha_entrega date, out salida int)
Begin
	insert into OrdenProduccion(IdMaterial,CantidadSolicitud,Fecha)
    values(id_material,cantidad_solicitada,fecha_entrega);
	select IdOrden into salida from OrdenProduccion order by IdOrden desc limit 1;
end //

-- procedimientos de actualizacion

delimiter //
create Procedure Actualizar_Material(
nombre_material varchar(250),tiempo_espera int,tipo_espera varchar(20),cantidad_lote int,
cantidad_material int,estado_material boolean, id int)
Begin

	update Materiales m set m.nombre_material = nombre_material, m.tiempo_espera = tiempo_espera,
		m.tipo_espera = tipo_espera, m.cantidad_lote = cantidad_lote, m.estado_material = estado_material,
		m.cantidad_material = cantidad_material
	where m.id_material = id;

end //


delimiter //
create Procedure Actualizar_dependencia(
id_material_principal int, id_material_dependencia int, cantidad int , estado boolean)
Begin
	update Materiales_Dependencias md SET md.cantidad_material_dependencia = cantidad,
	md.estado_uso = estado
	where md.id_material_principal = id_material_principal 
		and md.id_material_dependencia = id_material_dependencia;
	
end //

create VIEW VistaComposicion AS (
SELECT M2.nombre_material AS 'Principal', M.nombre_material AS 'Dependencia', 
M2.id_material AS 'id_principal', M.id_material AS 'id_dependencia', MD.cantidad_material_dependencia,
MD.estado_uso , M.tiempo_espera
FROM Materiales_Dependencias MD
INNER JOIN Materiales M
ON M.id_material = MD.id_material_dependencia
INNER JOIN Materiales M2
ON M2.id_material = MD.id_material_principal
);

select * from VistaComposicion  where id_principal = 7;