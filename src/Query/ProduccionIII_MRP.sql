Drop Database SistemaMRP;

Create Database SistemaMRP;

Use SistemaMRP;

-- mat , cant, tiempo | Mat, cant, tiempo
-- table Mat ( nombre, cantidad inventario, tiempo, tam_lote, )
-- table dependencia(idMatPrincipal, IdMat, Cantidad_M)(ya podes esrc

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
	estado_material_principal boolean not null default true,
	foreign key(id_material_principal)references Materiales(id_material),
	foreign key(id_material_dependencia)references Materiales(id_material)
);

Create Table Proveedor(
	id_proveedor int primary key auto_increment not null,
	nombre_proveedor varchar(250)not null,
	estado_proveedor boolean default true
);


Create Table Estado_Envio(
	id_estado_envio int primary key auto_increment not null,
	id_proveedor int not null,
	id_material int not null,
	fecha_entrega datetime not null,
	fecha_solicitud datetime not null,
	cantidad_solicitada int unsigned not null,
	foreign key (id_proveedor)references Proveedor(id_proveedor),
	foreign key (id_material)references Materiales(id_material)
);

/* DESARROLLO DE FUNCIONES DE VERIFICACION DE MATERIALES Y FECHA */

DELIMITER //
CREATE FUNCTION Campos_Materiales_Vacio(nombre_material varchar(50),tiempo_espera varchar(50),cantidad_lote int,cantidad_material int)RETURNS INT
BEGIN
DECLARE vd int;
IF((tiempo_espera IS NULL or tiempo_espera = '') or (nombre_material IS NULL or nombre_material = '') or (cantidad_lote IS NULL or cantidad_lote = '') 
or (cantidad_material IS NULL or cantidad_material = ''))then
set vd = 0;
else
set vd = 1;
end if;
RETURN vd;
END //

DELIMITER //
CREATE FUNCTION Fecha_Invalida(fecha varchar(1024)) RETURNS INT 
BEGIN 
declare fecha_valida int;
if (select length(date(fecha)) is null )then 
set fecha_valida = 0; 
else 
set fecha_valida = 1; 
end if; 
RETURN tp; 
END //

/* DESARROLLO DE TRIGGERS PARA VALIDAR REGISTRO UNICO DE PRODUCTO, PROVEEDOR Y ESTADO DE ENVIOS */

DELIMITER //
Create Trigger tr_verificar_materiales Before insert on Materiales
For Each Row Begin
IF (EXISTS(SELECT nombre_material,estado_material FROM Materiales WHERE (nombre_material = NEW.nombre_material AND estado_material = true))) THEN
SIGNAL SQLSTATE '45000' 
SET MESSAGE_TEXT = "El material ya ha sido registrado con anterioridad y se encuentra activo";
  END IF;
End //

DELIMITER //
Create Trigger tr_verificar_proveedor Before insert on Proveedor
For Each Row begin
if(EXISTS(Select nombre_proveedor from Proveedor Where nombre_proveedor = NEW.nombre_proveedor)) THEN
SIGNAL SQLSTATE '45000' 
SET MESSAGE_TEXT = "Los datos del proveedor ya han sido registrados con anterioridad";
End if;
End //

DELIMITER //
Create Trigger tr_verificar_fecha_envio_recibo_material Before insert on Estado_Envio
For Each Row Begin
IF (NEW.fecha_entrega <= CURRENT_DATE) THEN
SIGNAL SQLSTATE '45000' 
SET MESSAGE_TEXT = "La fecha de entrega no puede ser menor o igual a la fecha actual";
  END IF;
End //


/* DESARROLLO DE PROCEDIMIENTOS DE ALMACENADO */
/*id_material int primary key auto_increment not null,
-- 	id_material_componente int,
	nombre_material varchar(250)not null,
	tiempo_espera varchar(50)not null,
	cantidad_lote int not null,
-- 	cantidad_componente int,
	cantidad_material int not null,
	estado_material boolean default true*/
drop procedure Registrar_Material;
DELIMITER //

create Procedure Registrar_Material(
nombre_material varchar(250),tiempo_espera int,tipo_espera varchar(20),cantidad_lote int,
cantidad_material int,estado_material boolean)
Begin
DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
DECLARE EXIT HANDLER FOR SQLWARNING ROLLBACK;
Start Transaction;

IF((Campos_Materiales_Vacio(nombre_material,tiempo_espera,cantidad_lote,cantidad_material)=1))
 THEN
	insert into Materiales(nombre_material,tiempo_espera,tipo_espera,cantidad_lote,cantidad_material,estado_material)
    values(nombre_material,tiempo_espera, tipo_espera,cantidad_lote,cantidad_material,estado_material);
	Commit;

else
call raise_error;
rollback;
End If;
End //


delimiter //
create Procedure Guardar_Material(
nombre_material varchar(250),tiempo_espera int,tipo_espera varchar(20),cantidad_lote int,
cantidad_material int,estado_material boolean, out salida int)
Begin

	insert into Materiales(nombre_material,tiempo_espera,tipo_espera,cantidad_lote,cantidad_material,estado_material)
    values(nombre_material,tiempo_espera, tipo_espera,cantidad_lote,cantidad_material,estado_material);
	select id_material into salida from Materiales order by id_material desc limit 1;
end //
/*
id_material int primary key auto_increment not null,
	id_material_componente int,
	nombre_material varchar(250)not null,
	tiempo_espera varchar(50)not null,
	cantidad_lote int not null,
	cantidad_componente int,
	cantidad_material int not null,
	estado_material boolean default true,
*/
DELIMITER //
Create Procedure Alterar_Material(
idMaterialBuscado int,nombre_Material varchar(50),tiempo_espera varchar(50),cantidad_lote int,cantidad_componente int, cantidad_material int,estado boolean)
Begin
IF(EXISTS(SELECT * FROM Material WHERE id_material = idMaterialBuscado)) THEN
Update Materiales set id_material = id_especialidadMedico,nombres = nombreMedico,apellidos = apellidoMedico ,direccion = direccion ,email = email ,telefono = telefono ,estadocivil = estadociviil ,estado = estado ,foto = foto Where id_medico=idMedicoBuscado;
else
SIGNAL SQLSTATE '45000' 
SET MESSAGE_TEXT = "El registro que desea modificar no existe...";
End if;
End //

DELIMITER //
Create Procedure BuscarMedico(
parametro_Buscado varchar(200))
Begin
if(parametro_Buscado IS NULL or parametro_Buscado = '')then
SIGNAL SQLSTATE '45000' 
SET MESSAGE_TEXT = "Campo de Busqueda Vacio";
else
Select m.id_medico,m.fecha_registro,m.nombres,m.apellidos,m.Direccion,m.email,m.telefono,m.genero,m.estadociviil,m.foto,e.nombreEspecialidad from Medico m
Inner Join Especialidad e
on e.id_especialidad = m.id_especialidad
Where id_medico = cast(parametro_Buscado as unsigned) OR
 fecha_registro like CONCAT('%', parametro_Buscado , '%') OR nombres like CONCAT('%', parametro_Buscado , '%') OR apellidos like CONCAT('%', parametro_Buscado , '%') OR Direccion like CONCAT('%', parametro_Buscado , '%') OR
email like CONCAT('%', parametro_Buscado , '%') OR telefono like CONCAT('%', parametro_Buscado , '%') OR genero like CONCAT('%', parametro_Buscado , '%') OR estadociviil like CONCAT('%', parametro_Buscado , '%') OR nombreEspecialidad like CONCAT('%', parametro_Buscado , '%');
end if;
End //

DELIMITER //
Create Event Actualizar_Estado_Envio
End //