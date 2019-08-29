CREATE DATABASE juegoMemoria 
GO

DROP DATABASE juegoMemoria

USE master
GO

USE juegoMemoria
GO
DELETE usuario 
CREATE TABLE usuario(
	
	id INTEGER PRIMARY KEY IDENTITY (1,1) NOT NULL,
	posicion INTEGER NOT NULL,
	nombreJugador VARCHAR (100) NOT NULL,
	tiempo TIME NOT NULL
)

INSERT INTO usuario VALUES (0,'Habib','0:1')

CREATE PROC sp_updateUser
AS

DECLARE @table_tiempo TABLE(tiempo TIME)
DECLARE @posicion INTEGER = 1
DECLARE @tiempo TIME


INSERT INTO @table_tiempo(tiempo) SELECT tiempo FROM usuario ORDER BY tiempo ASC
DECLARE @count int = (SELECT COUNT(*)FROM @table_tiempo)

	WHILE @count > 0
	BEGIN
		SET @tiempo = (SELECT TOP(1) tiempo FROM @table_tiempo ORDER BY tiempo ASC)
		UPDATE usuario SET posicion = @posicion WHERE tiempo = @tiempo
		SET @posicion = @posicion + 1
		DELETE @table_tiempo WHERE tiempo = @tiempo 
		SET @count = (SELECT COUNT (*) FROM @table_tiempo)
	END

DROP PROC sp_updateUser
EXECUTE sp_updateUser
SELECT posicion, nombreJugador, tiempo FROM usuario ORDER BY posicion ASC