---------M�TODOS------- SIN ACABAR

--De forma habitual, en programaci�n orientada a objetos, cuando se define una 
--clase tambi�n se establecen los m�todos que modelan su comportamiento. En el 
--contexto que nos ocupa, los m�todos son procedimientos y funciones que se 
--especifican despu�s de los atributos del objeto. Pueden ser de varios tipos: 
--MEMBER, CONSTRUCTOR Y STATIC.

--Primero se crea el tipo de objeto.
CREATE OR REPLACE TYPE RECTANGULO AS OBJECT(
  BASE NUMBER,
  ALTURA NUMBER,
  AREA NUMBER,
  STATIC PROCEDURE PROC1 (ANCHO INTEGER, ALTO INTEGER),
  MEMBER PROCEDURE PROC2 (ANCHO INTEGER, ALTO INTEGER),
  CONSTRUCTOR FUNCTION RECTANGULO (BASE NUMBER, ALTURA NUMBER)
        RETURN SELF AS RESULT
);