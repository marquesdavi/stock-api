CREATE TABLE tb_product
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(255) NOT NULL,
    value  DOUBLE       NOT NULL,
    amount INT          NOT NULL
);
