CREATE DATABASE  IF NOT EXISTS `db_academicos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_academicos`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: db_academicos
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administradores`
--

DROP TABLE IF EXISTS `administradores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administradores` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idUsuario`),
  CONSTRAINT `administradores_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administradores`
--

LOCK TABLES `administradores` WRITE;
/*!40000 ALTER TABLE `administradores` DISABLE KEYS */;
/*!40000 ALTER TABLE `administradores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `areas_projetos`
--

DROP TABLE IF EXISTS `areas_projetos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `areas_projetos` (
  `idArea` int NOT NULL,
  `idProjeto` int NOT NULL,
  PRIMARY KEY (`idArea`,`idProjeto`),
  KEY `idProjeto` (`idProjeto`),
  CONSTRAINT `areas_projetos_ibfk_1` FOREIGN KEY (`idArea`) REFERENCES `areasdeconhecimento` (`idArea`),
  CONSTRAINT `areas_projetos_ibfk_2` FOREIGN KEY (`idProjeto`) REFERENCES `projetos` (`idProjeto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areas_projetos`
--

LOCK TABLES `areas_projetos` WRITE;
/*!40000 ALTER TABLE `areas_projetos` DISABLE KEYS */;
/*!40000 ALTER TABLE `areas_projetos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `areasdeconhecimento`
--

DROP TABLE IF EXISTS `areasdeconhecimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `areasdeconhecimento` (
  `idArea` int NOT NULL AUTO_INCREMENT,
  `nomeArea` varchar(100) NOT NULL,
  PRIMARY KEY (`idArea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areasdeconhecimento`
--

LOCK TABLES `areasdeconhecimento` WRITE;
/*!40000 ALTER TABLE `areasdeconhecimento` DISABLE KEYS */;
/*!40000 ALTER TABLE `areasdeconhecimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artigos`
--

DROP TABLE IF EXISTS `artigos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artigos` (
  `idArtigo` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) NOT NULL,
  `resumo` varchar(2500) DEFAULT NULL,
  `autores` varchar(500) DEFAULT NULL,
  `arquivo` blob NOT NULL,
  `idProjeto` int NOT NULL,
  PRIMARY KEY (`idArtigo`),
  KEY `idProjeto` (`idProjeto`),
  CONSTRAINT `artigos_ibfk_1` FOREIGN KEY (`idProjeto`) REFERENCES `projetos` (`idProjeto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artigos`
--

LOCK TABLES `artigos` WRITE;
/*!40000 ALTER TABLE `artigos` DISABLE KEYS */;
/*!40000 ALTER TABLE `artigos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artigos_palavraschave`
--

DROP TABLE IF EXISTS `artigos_palavraschave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artigos_palavraschave` (
  `idArtigo` int NOT NULL,
  `idPalavra` int NOT NULL,
  PRIMARY KEY (`idArtigo`,`idPalavra`),
  KEY `idPalavra` (`idPalavra`),
  CONSTRAINT `artigos_palavraschave_ibfk_1` FOREIGN KEY (`idArtigo`) REFERENCES `artigos` (`idArtigo`),
  CONSTRAINT `artigos_palavraschave_ibfk_2` FOREIGN KEY (`idPalavra`) REFERENCES `palavraschave` (`idPalavra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artigos_palavraschave`
--

LOCK TABLES `artigos_palavraschave` WRITE;
/*!40000 ALTER TABLE `artigos_palavraschave` DISABLE KEYS */;
/*!40000 ALTER TABLE `artigos_palavraschave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bolsistas`
--

DROP TABLE IF EXISTS `bolsistas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bolsistas` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `matricula` bigint NOT NULL,
  `curso` varchar(50) DEFAULT NULL,
  `acessoPostagens` tinyint(1) NOT NULL,
  `acessoArtigos` tinyint(1) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  CONSTRAINT `bolsistas_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bolsistas`
--

LOCK TABLES `bolsistas` WRITE;
/*!40000 ALTER TABLE `bolsistas` DISABLE KEYS */;
/*!40000 ALTER TABLE `bolsistas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bolsistas_projetos`
--

DROP TABLE IF EXISTS `bolsistas_projetos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bolsistas_projetos` (
  `idUsuario` int NOT NULL,
  `idProjeto` int NOT NULL,
  `dataInicio` date DEFAULT NULL,
  `dataFim` date DEFAULT NULL,
  PRIMARY KEY (`idUsuario`,`idProjeto`),
  KEY `idProjeto` (`idProjeto`),
  CONSTRAINT `bolsistas_projetos_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `bolsistas` (`idUsuario`),
  CONSTRAINT `bolsistas_projetos_ibfk_2` FOREIGN KEY (`idProjeto`) REFERENCES `projetos` (`idProjeto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bolsistas_projetos`
--

LOCK TABLES `bolsistas_projetos` WRITE;
/*!40000 ALTER TABLE `bolsistas_projetos` DISABLE KEYS */;
/*!40000 ALTER TABLE `bolsistas_projetos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campus`
--

DROP TABLE IF EXISTS `campus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campus` (
  `idCampus` int NOT NULL AUTO_INCREMENT,
  `nomeCampus` varchar(50) NOT NULL,
  `localCampus` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idCampus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campus`
--

LOCK TABLES `campus` WRITE;
/*!40000 ALTER TABLE `campus` DISABLE KEYS */;
/*!40000 ALTER TABLE `campus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coordenadores`
--

DROP TABLE IF EXISTS `coordenadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coordenadores` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `siape` int NOT NULL,
  `formacao` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  CONSTRAINT `coordenadores_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordenadores`
--

LOCK TABLES `coordenadores` WRITE;
/*!40000 ALTER TABLE `coordenadores` DISABLE KEYS */;
/*!40000 ALTER TABLE `coordenadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coordenadores_projetos`
--

DROP TABLE IF EXISTS `coordenadores_projetos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coordenadores_projetos` (
  `idUsuario` int NOT NULL,
  `idProjeto` int NOT NULL,
  `dataInicio` date DEFAULT NULL,
  `dataFim` date DEFAULT NULL,
  PRIMARY KEY (`idUsuario`,`idProjeto`),
  KEY `idProjeto` (`idProjeto`),
  CONSTRAINT `coordenadores_projetos_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `coordenadores` (`idUsuario`),
  CONSTRAINT `coordenadores_projetos_ibfk_2` FOREIGN KEY (`idProjeto`) REFERENCES `projetos` (`idProjeto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordenadores_projetos`
--

LOCK TABLES `coordenadores_projetos` WRITE;
/*!40000 ALTER TABLE `coordenadores_projetos` DISABLE KEYS */;
/*!40000 ALTER TABLE `coordenadores_projetos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fotos`
--

DROP TABLE IF EXISTS `fotos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fotos` (
  `idFoto` int NOT NULL AUTO_INCREMENT,
  `arquivoFoto` blob NOT NULL,
  `idPostagem` int NOT NULL,
  PRIMARY KEY (`idFoto`),
  KEY `idPostagem` (`idPostagem`),
  CONSTRAINT `fotos_ibfk_1` FOREIGN KEY (`idPostagem`) REFERENCES `postagens` (`idPostagem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fotos`
--

LOCK TABLES `fotos` WRITE;
/*!40000 ALTER TABLE `fotos` DISABLE KEYS */;
/*!40000 ALTER TABLE `fotos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noticiasgerais`
--

DROP TABLE IF EXISTS `noticiasgerais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noticiasgerais` (
  `idNoticia` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `texto` varchar(1000) DEFAULT NULL,
  `imagem` blob,
  `dataPublicacao` date NOT NULL,
  `idAdministrador` int NOT NULL,
  PRIMARY KEY (`idNoticia`),
  KEY `idAdministrador` (`idAdministrador`),
  CONSTRAINT `noticiasgerais_ibfk_1` FOREIGN KEY (`idAdministrador`) REFERENCES `administradores` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noticiasgerais`
--

LOCK TABLES `noticiasgerais` WRITE;
/*!40000 ALTER TABLE `noticiasgerais` DISABLE KEYS */;
/*!40000 ALTER TABLE `noticiasgerais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `palavraschave`
--

DROP TABLE IF EXISTS `palavraschave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `palavraschave` (
  `idPalavra` int NOT NULL AUTO_INCREMENT,
  `palavra` varchar(30) NOT NULL,
  PRIMARY KEY (`idPalavra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `palavraschave`
--

LOCK TABLES `palavraschave` WRITE;
/*!40000 ALTER TABLE `palavraschave` DISABLE KEYS */;
/*!40000 ALTER TABLE `palavraschave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postagens`
--

DROP TABLE IF EXISTS `postagens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `postagens` (
  `idPostagem` int NOT NULL AUTO_INCREMENT,
  `idProjeto` int NOT NULL,
  `legenda` varchar(200) DEFAULT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`idPostagem`),
  KEY `idProjeto` (`idProjeto`),
  CONSTRAINT `postagens_ibfk_1` FOREIGN KEY (`idProjeto`) REFERENCES `projetos` (`idProjeto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postagens`
--

LOCK TABLES `postagens` WRITE;
/*!40000 ALTER TABLE `postagens` DISABLE KEYS */;
/*!40000 ALTER TABLE `postagens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projetos`
--

DROP TABLE IF EXISTS `projetos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projetos` (
  `idProjeto` int NOT NULL AUTO_INCREMENT,
  `tituloProjeto` varchar(100) NOT NULL,
  `resumo` varchar(2000) DEFAULT NULL,
  `idCampus` int NOT NULL,
  `edital` varchar(50) DEFAULT NULL,
  `dataInicio` date DEFAULT NULL,
  `dataFim` date DEFAULT NULL,
  `prorrogacao` date DEFAULT NULL,
  `emAndamento` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idProjeto`),
  KEY `idCampus` (`idCampus`),
  CONSTRAINT `projetos_ibfk_1` FOREIGN KEY (`idCampus`) REFERENCES `campus` (`idCampus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projetos`
--

LOCK TABLES `projetos` WRITE;
/*!40000 ALTER TABLE `projetos` DISABLE KEYS */;
/*!40000 ALTER TABLE `projetos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitacoes`
--

DROP TABLE IF EXISTS `solicitacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitacoes` (
  `idSolicitacao` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `data` date DEFAULT NULL,
  `descricao` varchar(500) DEFAULT NULL,
  `aceitacao` tinyint(1) DEFAULT NULL,
  `anexo` blob,
  PRIMARY KEY (`idSolicitacao`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `solicitacoes_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitacoes`
--

LOCK TABLES `solicitacoes` WRITE;
/*!40000 ALTER TABLE `solicitacoes` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitacoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `cpf` bigint NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `apelido` varchar(30) DEFAULT NULL,
  `senha` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `ativa` tinyint(1) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-31 16:41:36
