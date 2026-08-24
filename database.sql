-- =========================================================
-- PROYECTO PEDIDOS POO
-- Base de datos PostgreSQL
-- =========================================================

-- =========================================================
-- TABLA CLIENTE
-- =========================================================

CREATE TABLE cliente (
    cli_codigo INTEGER NOT NULL,
    cli_cedula VARCHAR NOT NULL,
    cli_nombre VARCHAR NOT NULL,
    cli_direccion VARCHAR NOT NULL,
    cli_telefono VARCHAR NOT NULL,

    CONSTRAINT cliente_pkey
        PRIMARY KEY (cli_codigo)
);


-- =========================================================
-- TABLA PRODUCTO
-- =========================================================

CREATE TABLE producto (
    pro_codigo INTEGER NOT NULL,
    pro_nombre VARCHAR NOT NULL,
    pro_precio NUMERIC NOT NULL,

    CONSTRAINT producto_pkey
        PRIMARY KEY (pro_codigo)
);


-- =========================================================
-- TABLA PEDIDO
-- =========================================================

CREATE TABLE pedido (
    ped_numero INTEGER NOT NULL,
    pedi_cliente INTEGER,
    ped_fecha DATE NOT NULL,
    ped_total NUMERIC NOT NULL,

    CONSTRAINT pedido_pkey
        PRIMARY KEY (ped_numero),

    CONSTRAINT pedido_cliente_fkey
        FOREIGN KEY (pedi_cliente)
        REFERENCES cliente (cli_codigo)
);


-- =========================================================
-- TABLA DETALLE_PEDIDO
-- =========================================================

CREATE TABLE detalle_pedido (
    dep_codigo INTEGER NOT NULL,
    dep_pedido INTEGER,
    dep_producto INTEGER,
    dep_cantidad NUMERIC NOT NULL,
    dep_subtotal NUMERIC NOT NULL,

    CONSTRAINT detalle_pedido_pkey
        PRIMARY KEY (dep_codigo),

    CONSTRAINT detalle_pedido_pedido_fkey
        FOREIGN KEY (dep_pedido)
        REFERENCES pedido (ped_numero),

    CONSTRAINT detalle_pedido_producto_fkey
        FOREIGN KEY (dep_producto)
        REFERENCES producto (pro_codigo)
);
-- =========================================================
-- DATOS DE PRUEBA
-- =========================================================

INSERT INTO cliente
(cli_codigo, cli_cedula, cli_nombre, cli_direccion, cli_telefono)
VALUES
(1, 'XXXXXXXXXX', 'Cliente de Prueba', 'Esmeraldas', '0999999999');


INSERT INTO producto
(pro_codigo, pro_nombre, pro_precio)
VALUES
(1, 'Producto de Prueba', 10.00);


INSERT INTO pedido
(ped_numero, pedi_cliente, ped_fecha, ped_total)
VALUES
(1, 1, '2026-08-23', 20.00);


INSERT INTO detalle_pedido
(dep_codigo, dep_pedido, dep_producto, dep_cantidad, dep_subtotal)
VALUES
(1, 1, 1, 2, 20.00);