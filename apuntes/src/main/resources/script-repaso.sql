DROP TABLE IF EXISTS personas;

CREATE TABLE personas (
    dni TEXT PRIMARY KEY,
    fechaNacimiento TEXT,
    zonaString TEXT
);


INSERT INTO personas (dni, fechaNacimiento, zonaString) VALUES 
('12345454J', '23/06/1999', 'Europe/Madrid');
