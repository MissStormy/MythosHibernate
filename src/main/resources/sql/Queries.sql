-- Esta BBDD va a estar basada en el trabajo de H.P.Lovecraft
-- Call of Cthulhu
DROP DATABASE IF EXISTS Cthulhu;
CREATE DATABASE Cthulhu;
USE Cthulhu;

-- Esta tabla contiene a todos los mitos conocidos
CREATE TABLE Mythos (
                        Id int unsigned auto_increment primary key,
                        Nombre varchar(50),
                        Tipo enum('Primigenio', 'Dios Exterior', 'Monstruo'),
                        Genero enum('Masculino', 'Femenino', 'Otro'),
                        Origen varchar(50),
                        Bio varchar(1000)
);
INSERT INTO Mythos VALUES
                       (1, "Cthulhu", "Primigenio","Masculino","Vhoorl","Aquel que duerme, reside en R'lyeh y su despertar traera el fin del mundo"),
                       (2, "Nyarlathotep", "Dios Exterior","Otro","Desconocido","Dios del caos, quiere destronar a Cthulhu"),
                       (3, "Azathoth", "Dios Exterior","Otro","Corte de Azathoth","Hermano de Cthulhu"),
                       (4, "Yog-Shothot", "Primigenio", "Femenino", "Bosque de Esporas", "Diosa de la naturaleza, busca la destruccion del mundo"),
                       (5, "Nodens", "Dios Exterior", "Masculino", "Atlantida", "Dios del agua, eterno enemigo de Nyarlathotep"),
                       (6, "Perros de Tindalos", "Monstruo", "Otro", "Sin determinar", "Sabuesos cuanticos al servicio de Nodens y Nyarlathotep");

