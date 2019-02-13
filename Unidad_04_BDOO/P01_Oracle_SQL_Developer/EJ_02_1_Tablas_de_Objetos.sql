------------TABLAS DE OBJETOS-----------

--CREACI�N DE LA TABLA ALUMNOS_EJEMPLO DE TIPO PERSONA
CREATE TABLE ALUMNOS_EJEMPLO OF PERSONA (
  CODIGO PRIMARY KEY
);

--INSERCION DE DATOS
INSERT INTO ALUMNOS_EJEMPLO VALUES( 1, 'Juan P�rez ', 
    DIRECCION ('C/Los manantiales 5', 'GUADALAJARA', 19005), '18/12/1991');

INSERT INTO ALUMNOS_EJEMPLO (CODIGO, NOMBRE, DIREC, FECHA_NAC) VALUES ( 
  2, 'Julia Bre�a',  DIRECCION ('C/Los espartales 25', 'GUADALAJARA', 19004),
  '18/12/1987');

--El siguiente bloque PL/SQL inserta una fila en la tabla ALUMNOS_EJEMPLO:
DECLARE
  DIR DIRECCION := DIRECCION('C/Sevilla 20', 'GUADALAJARA', 19004);
  PER PERSONA := PERSONA(5, 'MANUEL',DIR, '20/10/1987');
BEGIN  
  INSERT INTO ALUMNOS_EJEMPLO VALUES(PER); --insertar 
  COMMIT; 
END;

SELECT * FROM ALUMNOS_EJEMPLO;