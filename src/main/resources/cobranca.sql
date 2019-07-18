DROP TABLE IF EXISTS CLIENTE;
CREATE TABLE CLIENTE (
  CODIGO int(11) NOT NULL AUTO_INCREMENT,
  NOME varchar(100)  DEFAULT NULL,
  TELEFONE varchar(30) DEFAULT NULL,
  PRIMARY KEY (CODIGO)
) ;

DROP TABLE IF EXISTS CARTACOBRANCA;
CREATE TABLE CARTACOBRANCA (
  CODIGO int(11) NOT NULL AUTO_INCREMENT,
  CODCLIENTE int(11) NOT NULL,
  VALORTOTAL double DEFAULT NULL,
  VENCIMENTO date DEFAULT NULL,
  PRIMARY KEY (CODIGO),
  CONSTRAINT FK_CLIENTE_COB FOREIGN KEY (CODCLIENTE) REFERENCES CLIENTE (CODIGO) ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS DEBITO;
CREATE TABLE DEBITO (
  CODIGO int(11) NOT NULL AUTO_INCREMENT,
  CODCLIENTE int(11) NOT NULL,
  VENCIMENTO date NOT NULL,
  VALOR double DEFAULT NULL,
  STATUS varchar(10) DEFAULT NULL,
  PRIMARY KEY (CODIGO),
  CONSTRAINT FK_CLIENTE FOREIGN KEY (CODCLIENTE) REFERENCES CLIENTE (CODIGO) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;
