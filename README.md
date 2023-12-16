# Cloud
Cloud

# Estructura DB

create table usuarios(
    dni varchar(9) PRIMARY KEY , 
    tel varchar(12) NOT NULL,
	nombre varchar(100)  NOT NULL,
    contraseña varchar(15) NOT NULL
);