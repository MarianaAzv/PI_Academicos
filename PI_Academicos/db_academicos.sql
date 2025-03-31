create database db_academicos;
 
use db_academicos;

CREATE TABLE Usuarios(
idUsuario INT NOT NULL AUTO_INCREMENT,
cpf BIGINT(11) NOT NULL,
nome VARCHAR(50),
apelido INT,
senha VARCHAR(50) NOT NULL,
email VARCHAR(50),
ativa TINYINT(1) NOT NULL,
PRIMARY KEY (idUsuario));

CREATE TABLE Coordenadores(
idUsuario INT NOT NULL AUTO_INCREMENT,
siape INT NOT NULL,
formacao VARCHAR(200),
PRIMARY KEY (idUsuario),
FOREIGN KEY (idUsuario) REFERENCES Usuarios (idUsuario));

CREATE TABLE Administradores(
idUsuario INT NOT NULL AUTO_INCREMENT,
PRIMARY KEY (idUsuario),
FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario));

CREATE TABLE IF NOT EXISTS Bolsistas(
idUsuario INT NOT NULL AUTO_INCREMENT,
matricula BIGINT NOT NULL,
curso VARCHAR(50),
acessoPostagens TINYINT(1) NOT NULL,
acessoArtigos TINYINT(1) NOT NULL,
PRIMARY KEY (idUsuario),
FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario));

CREATE TABLE NoticiasGerais(
idNoticia INT NOT NULL AUTO_INCREMENT,
titulo VARCHAR(100) NOT NULL,
texto VARCHAR(1000),
imagem BLOB,
dataPublicacao DATE NOT NULL,
idAdministrador INT NOT NULL,
PRIMARY KEY (idNoticia),
FOREIGN KEY (idAdministrador) REFERENCES Administradores(idUsuario));

CREATE TABLE Solicitacoes(
idSolicitacao INT NOT NULL AUTO_INCREMENT,
idUsuario INT NOT NULL,
data DATE,
descricao VARCHAR(500),
aceitacao TINYINT(1),
anexo BLOB,
PRIMARY KEY (idSolicitacao),
FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario));

CREATE TABLE IF NOT EXISTS Campus(
idCampus INT NOT NULL AUTO_INCREMENT,
nomeCampus VARCHAR(50) NOT NULL,
localCampus VARCHAR(100),
PRIMARY KEY (idCampus));

CREATE TABLE Projetos(
idProjeto INT NOT NULL AUTO_INCREMENT,
tituloProjeto VARCHAR(100) NOT NULL,
resumo VARCHAR(2000),
idCampus INT NOT NULL,
edital VARCHAR(50),
dataInicio DATE,
dataFim DATE,
prorrogacao DATE,
emAndamento TINYINT(1),
PRIMARY KEY (idProjeto),
FOREIGN KEY (idCampus) REFERENCES Campus(idCampus));

CREATE TABLE Coordenadores_Projetos(
idUsuario INT NOT NULL,
idProjeto INT NOT NULL,
dataInicio DATE,
dataFim DATE,
PRIMARY KEY (idUsuario, idProjeto),
FOREIGN KEY (idUsuario) REFERENCES Coordenadores(idUsuario),
FOREIGN KEY (idProjeto) REFERENCES Projetos(idProjeto));

CREATE TABLE Bolsistas_Projetos(
idUsuario INT NOT NULL,
idProjeto INT NOT NULL,
dataInicio DATE,
dataFim DATE,
PRIMARY KEY (idUsuario, idProjeto),
FOREIGN KEY (idUsuario) REFERENCES Bolsistas(idUsuario),
FOREIGN KEY (idProjeto) REFERENCES Projetos(idProjeto));

CREATE TABLE AreasDeConhecimento(
idArea INT NOT NULL AUTO_INCREMENT,
nomeArea VARCHAR(100) NOT NULL,
PRIMARY KEY(idArea));

CREATE TABLE Areas_Projetos(
idArea INT NOT NULL,
idProjeto INT NOT NULL,
PRIMARY KEY (idArea, idProjeto),
FOREIGN KEY (idArea) REFERENCES AreasDeConhecimento(idArea),
FOREIGN KEY (idProjeto) REFERENCES Projetos(idProjeto));

CREATE TABLE Postagens(
idPostagem INT NOT NULL AUTO_INCREMENT,
idProjeto INT NOT NULL,
legenda VARCHAR(200),
data DATE,
PRIMARY KEY (idPostagem),
FOREIGN KEY (idProjeto) REFERENCES Projetos(idProjeto));

CREATE TABLE Fotos(
idFoto INT NOT NULL AUTO_INCREMENT,
arquivoFoto BLOB NOT NULL,
idPostagem INT NOT NULL,
PRIMARY KEY (idFoto),
FOREIGN KEY (idPostagem) REFERENCES Postagens(idPostagem));

CREATE TABLE Artigos(
idArtigo INT NOT NULL AUTO_INCREMENT,
titulo VARCHAR(50) NOT NULL,
resumo VARCHAR(2500),
autores VARCHAR(500),
arquivo BLOB NOT NULL,
idProjeto INT NOT NULL,
PRIMARY KEY (idArtigo),
FOREIGN KEY (idProjeto)
REFERENCES Projetos(idProjeto));

CREATE TABLE PalavrasChave(
idPalavra INT NOT NULL AUTO_INCREMENT,
palavra VARCHAR(30) NOT NULL,
PRIMARY KEY (idPalavra));

CREATE TABLE Artigos_PalavrasChave(
idArtigo INT NOT NULL,
idPalavra  INT NOT NULL,
PRIMARY KEY (idArtigo, idPalavra),
FOREIGN KEY (idArtigo) REFERENCES Artigos(idArtigo),
FOREIGN KEY (idPalavra) REFERENCES PalavrasChave(idPalavra));
