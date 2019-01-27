-- Por ejemplo
CREATE TYPE TABLA_ANIDADA AS TABLE OF DIRECCION;
-- Creaci�n de tabla con tabla anidada
CREATE TABLE EJEMPLO_TABLA_CONTENEDORA (
ID NUMBER(2),
APELLIDOS VARCHAR2(40),
DIREC TABLA_ANIDADA
)
NESTED TABLE DIREC STORE AS DIREC_ANIDADA; --nombre de la tabla independiente que
                                          --que va a almacenar inforamci�n
                                          
--Insertar datos
INSERT INTO EJEMPLO_TABLA_CONTENEDORA VALUES( 1, 'PEREZ',
TABLA_ANIDADA (
DIRECCION ('Avenida Ciudad de Soria', 'ZARAGOZA', 50016),
DIRECCION ('Calle Asalto', 'ZARAGOZA', 50002),
DIRECCION ('Avenida de Madrid', 'ZARAGOZA', 50010)
));
INSERT INTO EJEMPLO_TABLA_CONTENEDORA VALUES( 2, 'MARTINEZ',
TABLA_ANIDADA (
DIRECCION ('Avenida Catalu�a', 'ZARAGOZA', 50016),
DIRECCION ('Paseo Fernando Cat�lico', 'CALATAYUD', 50300),
DIRECCION ('Avenida de Navarra', 'ZARAGOZA', 50010)
));

-- Tambi�n se puede insertar registros con la tabla anidada vac�a
INSERT INTO EJEMPLO_TABLA_CONTENEDORA VALUES( 3, 'FERNANDEZ',
TABLA_ANIDADA ()
);

-- Recupera todos los registros de la tabla anidada, el id y apellidos de la
--principal.
SELECT ID, APELLIDOS, DIRECCION.* FROM EJEMPLO_TABLA_CONTENEDORA, 
TABLE(DIREC) DIRECCION; --Establece un alias para la tabla

---Ejercicios
--1. Obtener el n�mero de direcciones que tiene cada ciudad en los 
--identificadores 1 y 2.

SELECT ID, APELLIDOS, CIUDAD, COUNT(*) AS NUMERO FROM EJEMPLO_TABLA_CONTENEDORA, TABLE(DIREC) TD
GROUP BY ID, CIUDAD, APELLIDOS ORDER BY ID;

--Obtener la ciudad con m�s direcciones para el identificador 1.
SELECT MAX(D.CIUDAD) FROM EJEMPLO_TABLA_CONTENEDORA, TABLE(DIREC) D WHERE ID = 1;

--Realizar un bloque PL/SQL para mostrar el nombre de las calles asociadas a cada apellido.
CREATE OR REPLACE PROCEDURE MOSTRAR_CALLES (APE VARCHAR2)
SELECT APELLIDOS, CALLE FROM EJEMPLO_TABLA_CONTENEDORA, TABLE(DIREC) TD WHERE APELLIDO = APE; 