CREATE TABLE usuario (
    cedula VARCHAR(15) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    fech_nacimiento DATE NOT NULL,
    fech_modificacion TIMESTAMP,
    fech_creacion TIMESTAMP,
    Telefono_1 VARCHAR(25) NOT NULL,
    Telefono_2 VARCHAR(25),
    Activo Boolean NOT NULL default true,
    Rol VARCHAR(10) NOT NULL default 'vendedor'
);

CREATE TABLE cliente (
    cedula VARCHAR(15) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    tel_pers_contac VARCHAR(25) NOT NULL,
    quien_creo VARCHAR(100) NOT NULL,
    nom_pers_contact VARCHAR(100) NOT NULL,
    fech_nacimiento DATE NOT NULL,
    fech_modificacion TIMESTAMP,
    fech_creacion TIMESTAMP,
    cedula_usuario VARCHAR(15),
    Telefono_1 VARCHAR (25) NOT NULL,
    Telefono_2 VARCHAR (25),
    Activo Boolean NOT NULL default true,
    FOREIGN KEY (cedula_usuario) REFERENCES usuario(cedula)
    on update cascade
  
);


CREATE TABLE Compra (
    id_compra SERIAL PRIMARY KEY,
    id_cliente VARCHAR(15) ,
    id_usuario VARCHAR(15) ,
    fech_creacion TIMESTAMP,
    total_planes DECIMAL(10, 2) NOT NULL, 
    total_otros_cargos DECIMAL(10, 2) NOT NULL, 
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY(id_cliente) REFERENCES Cliente(cedula)
    on update cascade,
    FOREIGN KEY(id_usuario) REFERENCES usuario(cedula)
    on update cascade

);

 
CREATE TABLE Plan_Turistico (
    Id_PlanTuristico SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    Descripcion TEXT NOT NULL,
    Fech_Creacion TIMESTAMP,
    Fech_modificacion TIMESTAMP,
    Cant_dias INTEGER NOT NULL,
    Estado BOOLEAN NOT NULL default true,
    Alimentación BOOLEAN NOT NULL,
    Imagen TEXT
);


CREATE TABLE Detalle_Compra (
    id_detalleCompra SERIAL PRIMARY KEY,
    id_compra INTEGER ,
    id_plan INTEGER ,
    valor_plan DECIMAL(10, 2) NOT NULL, 
    valor_AlimentacionPlan DECIMAL(10, 2) NOT NULL default 0, 
    FOREIGN KEY(id_compra) REFERENCES Compra(id_compra)
    on update cascade,
    Cantidad_personas INTEGER NOT NULL default 1,
    FOREIGN KEY(id_plan) REFERENCES Plan_Turistico(Id_PlanTuristico)
    on update cascade,
    total_detalle DECIMAL(10, 2) 
);

CREATE TABLE Tarifa (
    Titulo_Plan INTEGER REFERENCES Plan_Turistico(Id_PlanTuristico), 
    id_Temporada VARCHAR(20) ,
    PRIMARY KEY(id_Temporada,Titulo_Plan),
    Fech_Creacion TIMESTAMP,
    Fech_inicio DATE NOT NULL,
    Fech_Modificacion TIMESTAMP,
    Fecha_Fin DATE NOT NULL,
    Costo DECIMAL(10, 2) NOT NULL, 
    Estado BOOLEAN NOT NULL default true
);



CREATE TABLE departamentos (
    id SERIAL PRIMARY KEY,
    departamento VARCHAR(255) NOT NULL
);

CREATE TABLE ciudades (
  id SERIAL PRIMARY KEY,
  ciudad varchar(255) NOT NULL,
  departamento_id INT NOT NULL,
  FOREIGN KEY (departamento_id) REFERENCES departamentos(id)
);


CREATE TABLE Punto_Visita (
    id_Actividad SERIAL PRIMARY KEY,
    nom_Actividad VARCHAR(100) NOT NULL,
    Descripcion TEXT NOT NULL,
    Estado BOOLEAN NOT NULL default true,
    Fecha_Modificacion TIMESTAMP,
    Fecha_Creacion TIMESTAMP,
    id_Departamento INTEGER,
    id_Ciudad INTEGER ,
    FOREIGN KEY (id_Departamento) REFERENCES departamentos(id)
    on update cascade,
    FOREIGN KEY (id_Ciudad) REFERENCES ciudades(id)
    on update cascade

    
);

CREATE TABLE Plan_Punto (
    id_PlanPuntoVisita SERIAL PRIMARY KEY,
    id_Plan INTEGER ,
    id_Actividad INTEGER ,
    FOREIGN KEY (id_Plan) REFERENCES Plan_Turistico(Id_PlanTuristico)
    on update cascade,
    FOREIGN KEY (id_Actividad) REFERENCES Punto_Visita(id_Actividad)
    on update cascade
);

INSERT INTO usuario (cedula, nombre, correo, username, password, fech_nacimiento, fech_modificacion, fech_creacion, Telefono_1, Telefono_2,rol)
VALUES ('1111111110', 'Admin', 'admin.perez@gmail.com', 'admin', 'admin1234', '1980-05-15', '2023-10-26 12:30:00', '2023-10-26 12:30:00', '555-1111-111', '555-1111-112', 'admin');
