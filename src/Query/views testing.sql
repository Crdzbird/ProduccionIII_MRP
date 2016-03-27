select * from Materiales_Dependencias;

create VIEW VistaComposicion AS (
SELECT M2.nombre_material AS 'Principal', M.nombre_material AS 'Dependencia', 
M2.id_material AS 'id_principal', M.id_material AS 'id_dependencia', MD.cantidad_material_dependencia
FROM Materiales_Dependencias MD
INNER JOIN Materiales M
ON M.id_material = MD.id_material_dependencia
INNER JOIN Materiales M2
ON M2.id_material = MD.id_material_principal
);

select * from VistaComposicion where id_principal = 13;