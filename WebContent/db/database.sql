-- Creando la base de datos
DROP DATABASE IF EXISTS db_cinema;
CREATE DATABASE db_cinema;
USE db_cinema;

-- Creando la tabla tb_movies
DROP TABLE IF EXISTS tb_movies;
CREATE TABLE tb_movies (
	id INT  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR (255) NOT NULL,
    picture TEXT  NOT NULL,
    url TEXT NOT NULL,
    register_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Creando procedimiento almacenado para registrar una película
DROP PROCEDURE IF EXISTS sp_register_movie;
DELIMITER $$
CREATE PROCEDURE sp_register_movie(
	IN v_name VARCHAR(255),
    IN v_description VARCHAR(255),
    IN v_picture TEXT,
    IN v_url TEXT
)
BEGIN
	INSERT INTO tb_movies (name,description,picture,url) VALUES (v_name,v_description,v_picture,v_url);
END $$
DELIMITER ;

-- Creando procedimieto almacenado para obtener la lista de películas
DROP PROCEDURE IF EXISTS sp_list_movies;
DELIMITER $$
CREATE PROCEDURE sp_list_movies()
BEGIN
	SELECT id,name,description,picture,url,register_date,update_date FROM tb_movies;
END $$
DELIMITER ;

-- Procedimiento almacenado para obtener una películas por id
DROP PROCEDURE IF EXISTS sp_movie_by_id;
DELIMITER $$
CREATE PROCEDURE sp_movie_by_id(
	IN v_id INT
)
BEGIN
	SELECT id,name,description,picture,url,register_date,update_date FROM tb_movies
    WHERE id = v_id;
END $$
DELIMITER ;

-- Procedimiento almacenado para actualizar una película
DROP PROCEDURE IF EXISTS sp_update_movie;
DELIMITER $$
CREATE PROCEDURE sp_update_movie(
	IN v_id INT,
	IN v_name VARCHAR(255),
    IN v_description VARCHAR(255),
    IN v_picture TEXT,
    IN v_url TEXT
)
BEGIN
	UPDATE tb_movies
    SET name = v_name,
		description = v_description,
        picture = v_picture,
        url = v_url,
        update_date = CURRENT_TIMESTAMP
    WHERE id = v_id;
END $$
DELIMITER ;

-- Procedimiento almacenado para eliminar una película
DROP PROCEDURE IF EXISTS sp_delete_movie;
DELIMITER $$
CREATE PROCEDURE sp_delete_movie(
	IN v_id INT
)
BEGIN
	DELETE FROM tb_movies WHERE id = v_id;
END $$
DELIMITER ;

CALL sp_delete_movie(1);