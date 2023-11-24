CREATE TABLE Familias (
    id_fam VARCHAR2(255) PRIMARY KEY,
    nom_fam VARCHAR2(255) NOT NULL,
    des_fam VARCHAR2(255),
    fecha_creacion DATE
);

CREATE TABLE Articulos (
    id_art VARCHAR2(255) PRIMARY KEY,
    nom_art VARCHAR2(255) NOT NULL,
    des_art VARCHAR2(255),
    id_fam VARCHAR2(255),
    stock int,
    FOREIGN KEY (id_fam) REFERENCES Familias(id_fam)
);

CREATE TABLE Clientes (
    id_cli VARCHAR2(255) PRIMARY KEY,
    nom_cli VARCHAR2(255) NOT NULL,
    correo_cli VARCHAR2(255),
    tel_cli VARCHAR2(15),
    direc_cli VARCHAR2(255)
);

CREATE TABLE Facturas (
    id_fac VARCHAR2(255) PRIMARY KEY,
    fecha_fac DATE NOT NULL,
    total_fac DECIMAL(10, 2),
    id_cli VARCHAR2(255),
    metodo_pago VARCHAR(50),
    FOREIGN KEY (id_cli) REFERENCES Clientes(id_cli)
);

CREATE TABLE LineaFactura (
    id_fac VARCHAR2(255),
    id_art VARCHAR2(255),
    PRIMARY KEY (id_fac, id_art),
    FOREIGN KEY (id_fac) REFERENCES Facturas(id_fac),
    FOREIGN KEY (id_art) REFERENCES Articulos(id_art)
);




INSERT INTO Familias (id_fam, nom_fam, des_fam, fecha_creacion) VALUES ('FAM001', 'Electrónicos', 'Productos electrónicos de consumo', TO_DATE('2023-11-16', 'YYYY-MM-DD'));
INSERT INTO Familias (id_fam, nom_fam, des_fam, fecha_creacion) VALUES ('FAM002', 'Ropa', 'Ropa de moda', TO_DATE('2023-11-16', 'YYYY-MM-DD'));
INSERT INTO Familias (id_fam, nom_fam, des_fam, fecha_creacion) VALUES ('FAM003', 'Hogar', 'Artículos para el hogar', TO_DATE('2023-11-16', 'YYYY-MM-DD'));

INSERT INTO Clientes (id_cli, nom_cli, correo_cli, tel_cli, direc_cli) VALUES ('CLI001', 'Juan Pérez', 'juan.perez@example.com', '123456789', 'Calle Principal 123');
INSERT INTO Clientes (id_cli, nom_cli, correo_cli, tel_cli, direc_cli) VALUES ('CLI002', 'María Rodríguez', 'maria.rodriguez@example.com', '987654321', 'Avenida Secundaria 456');
INSERT INTO Clientes (id_cli, nom_cli, correo_cli, tel_cli, direc_cli) VALUES ('CLI003', 'Carlos Gómez', 'carlos.gomez@example.com', '111223344', 'Plaza Central 789');

INSERT INTO Articulos (id_art, nom_art, des_art, id_fam, stock) VALUES ('ART001', 'Smartphone', 'Teléfono inteligente de última generación', 'FAM001', 100);
INSERT INTO Articulos (id_art, nom_art, des_art, id_fam, stock) VALUES ('ART002', 'Camiseta', 'Camiseta de algodón con diseño', 'FAM002', 200);
INSERT INTO Articulos (id_art, nom_art, des_art, id_fam, stock) VALUES ('ART003', 'Olla eléctrica', 'Olla eléctrica programable para cocina', 'FAM003', 50);

INSERT INTO Facturas (id_fac, fecha_fac, total_fac, id_cli, metodo_pago) VALUES ('FAC001', TO_DATE('2023-11-16', 'YYYY-MM-DD'), 1200.50, 'CLI001', 'Tarjeta de crédito');
INSERT INTO Facturas (id_fac, fecha_fac, total_fac, id_cli, metodo_pago) VALUES ('FAC002', TO_DATE('2023-11-16', 'YYYY-MM-DD'), 350.75, 'CLI002', 'Transferencia bancaria');
INSERT INTO Facturas (id_fac, fecha_fac, total_fac, id_cli, metodo_pago) VALUES ('FAC003', TO_DATE('2023-11-16', 'YYYY-MM-DD'), 75.20, 'CLI003', 'Efectivo');

INSERT INTO LineaFactura (id_fac, id_art) VALUES ('FAC001', 'ART001');
INSERT INTO LineaFactura (id_fac, id_art) VALUES ('FAC001', 'ART002');
INSERT INTO LineaFactura (id_fac, id_art) VALUES ('FAC002', 'ART003');

commit;




drop table LineaFactura;
drop table Facturas;
drop table Articulos;
drop table Clientes;
drop table Familias;

select * from LineaFactura;
select * from Facturas;
select * from clientes;
select * from articulos;
select * from familias;

delete lineafactura;
delete facturas;
delete articulos;
delete clientes;
delete familias;