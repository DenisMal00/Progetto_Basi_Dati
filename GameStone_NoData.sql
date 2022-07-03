-- MySQL dump 10.13  Distrib 8.0.28, for macos11 (arm64)
--
-- Host: 127.0.0.1    Database: GameStore
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `amicizia`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amicizia` (
  `Utente1` varchar(30) NOT NULL,
  `Utente2` varchar(30) NOT NULL,
  `data_Amicizia` date NOT NULL,
  PRIMARY KEY (`Utente1`,`Utente2`),
  KEY `FK_UtenteAmicizia2` (`Utente2`),
  CONSTRAINT `FK_UtenteAmicizia1` FOREIGN KEY (`Utente1`) REFERENCES `Utente` (`Nickname`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_UtenteAmicizia2` FOREIGN KEY (`Utente2`) REFERENCES `Utente` (`Nickname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `VincoloAmicizia` BEFORE INSERT ON `amicizia` FOR EACH ROW begin
    DECLARE data date;
    select data_Amicizia into data from amicizia where Utente1=new.Utente2 and Utente2=new.Utente1;
    if data is not null then
        SIGNAL sqlstate '45001' SET message_text = 'esiste già questa amicizia';
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Dato_Confidenziale`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Dato_Confidenziale` (
  `utente` varchar(30) NOT NULL,
  `pwd` varchar(30) NOT NULL,
  `numero_Carta` varchar(16) NOT NULL,
  `scadenza` varchar(5) NOT NULL,
  `cvv` varchar(4) NOT NULL,
  PRIMARY KEY (`utente`),
  CONSTRAINT `FK_UtenteDatoConf` FOREIGN KEY (`utente`) REFERENCES `Utente` (`Nickname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dato_Gioco`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dato_Gioco` (
  `Gioco` int NOT NULL,
  `Utente` varchar(30) NOT NULL,
  `Data_Acquisto` date NOT NULL,
  `Ore_Gioco` double DEFAULT '0',
  PRIMARY KEY (`Gioco`,`Utente`),
  KEY `FK_UtenteAcquista` (`Utente`),
  CONSTRAINT `FK_GiocoAcquista` FOREIGN KEY (`Gioco`) REFERENCES `gioco` (`id_Gioco`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_UtenteAcquista` FOREIGN KEY (`Utente`) REFERENCES `Utente` (`Nickname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `VincoloDatoGioco` BEFORE INSERT ON `dato_gioco` FOR EACH ROW begin
    DECLARE data DATE;
    SELECT Data_Pubb into data from gioco where gioco.id_Gioco=new.Gioco;
    if data > NEW.Data_Acquisto then
        SIGNAL sqlstate '45001' SET message_text = 'Non puoi comprare un gioco che non è ancora uscito';
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `VincoloEta` BEFORE INSERT ON `dato_gioco` FOR EACH ROW begin
    DECLARE eta int;
    DECLARE anno int;
    select Eta_Minima into eta from gioco where id_Gioco=new.Gioco;
    select YEAR(data_Nascita) into anno from Utente where Nickname=new.Utente;
    if (2022-anno) < eta then
        SIGNAL sqlstate '45001' SET message_text = 'Non hai l eta minima per scaricare il gioco';
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `gioco`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gioco` (
  `id_Gioco` int NOT NULL AUTO_INCREMENT,
  `titolo` varchar(50) NOT NULL,
  `Dimensioni` float NOT NULL,
  `Eta_Minima` tinyint NOT NULL,
  `Descrione` varchar(200) NOT NULL,
  `Data_Pubb` date NOT NULL,
  `prezzo` float DEFAULT NULL,
  `Sviluppatore` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id_Gioco`),
  KEY `FK_Sviluppatore` (`Sviluppatore`),
  CONSTRAINT `FK_Sviluppatore` FOREIGN KEY (`Sviluppatore`) REFERENCES `sviluppatore` (`P_Iva`),
  CONSTRAINT `Con_Eta` CHECK ((`Eta_Minima` between 3 and 18))
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `InserimentoVersione` AFTER INSERT ON `gioco` FOR EACH ROW begin
    insert into versione (Gioco, tipo, versione, descrizione,data_Versione) values (new.id_Gioco,'release','1.00','Versione Release',new.Data_Pubb);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `recensione`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensione` (
  `Gioco` int NOT NULL,
  `Utente` varchar(30) NOT NULL,
  `stelle` tinyint(1) NOT NULL,
  `titolo` varchar(30) NOT NULL,
  `data_Recensione` date NOT NULL,
  `testo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Gioco`,`Utente`),
  KEY `FK_UtenteRecensione` (`Utente`),
  CONSTRAINT `FK_GiocoRecensione` FOREIGN KEY (`Gioco`) REFERENCES `gioco` (`id_Gioco`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_UtenteRecensione` FOREIGN KEY (`Utente`) REFERENCES `Utente` (`Nickname`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Con_Stelle` CHECK ((`stelle` between 1 and 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `VincoliRecensioni` BEFORE INSERT ON `recensione` FOR EACH ROW begin
    DECLARE ore double;
    DECLARE data_Pubb DATE;
    DECLARE data_Acq DATE;
    DECLARE num int;
    SELECT Ore_Gioco into ore from dato_Gioco where dato_Gioco.Gioco=new.Gioco and dato_Gioco.Utente=new.Utente;
    SELECT Data_Acquisto into data_Acq from dato_Gioco where dato_Gioco.Gioco=new.Gioco and dato_Gioco.Utente=new.Utente;
    SELECT Data_Pubb into data_Pubb from gioco where gioco.id_Gioco=new.Gioco;
    SELECT count(*) into num from recensione where gioco=new.Gioco and utente=new.utente; -- si può ommettere
    if ore is null then
        SIGNAL sqlstate '45001' SET message_text = 'Non hai ancora scaricato il gioco';
    elseif ore < 1 then
        SIGNAL sqlstate '45001' SET message_text = 'Hai giocato meno di 1h non puoi recensire il gioco';
    elseif new.data_Recensione < data_Acq then
        SIGNAL sqlstate '45001' SET message_text = 'Controlla la data della recensione';
    elseif new.data_Recensione < data_Pubb then
        SIGNAL sqlstate '45001' SET message_text = 'Non puoi recensire un gioco che non è ancora uscito';
    elseif num >=1 then -- si puo ommettere
        SIGNAL sqlstate '45001' SET message_text = 'Non puoi recensire più di una volta lo stesso gioco';
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `sviluppatore`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sviluppatore` (
  `P_Iva` varchar(11) NOT NULL,
  `Nome` varchar(25) NOT NULL,
  `Sito_web` varchar(50) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `Indirizzo` varchar(50) NOT NULL,
  `citta` varchar(30) NOT NULL,
  `cap` varchar(5) NOT NULL,
  `provincia` varchar(2) NOT NULL,
  PRIMARY KEY (`P_Iva`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Utente`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Utente` (
  `Nickname` varchar(30) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `data_Nascita` date NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `indirizzo` varchar(50) NOT NULL,
  `citta` varchar(30) NOT NULL,
  `cap` varchar(5) NOT NULL,
  `provincia` varchar(2) NOT NULL,
  PRIMARY KEY (`Nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `versione`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `versione` (
  `Gioco` int NOT NULL,
  `tipo` varchar(8) NOT NULL,
  `versione` varchar(10) NOT NULL,
  `descrizione` varchar(100) DEFAULT NULL,
  `data_Versione` date NOT NULL,
  PRIMARY KEY (`Gioco`,`tipo`,`versione`),
  CONSTRAINT `FK_GiocoVersione` FOREIGN KEY (`Gioco`) REFERENCES `gioco` (`id_Gioco`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Con_versione` CHECK ((`tipo` in (_utf8mb4'alfa',_utf8mb4'beta',_utf8mb4'release')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `VincoloVersione` BEFORE INSERT ON `versione` FOR EACH ROW begin
    DECLARE dataRelease DATE;
    DECLARE dataReleasePrec DATE;
    DECLARE dataBeta DATE;
    DECLARE dataAlfa DATE;
    DECLARE str varchar(10);
    SELECT tipo  into str from versione where Gioco=new.Gioco order by tipo desc limit 1;
    SELECT data_Versione  into dataRelease from versione where Gioco=new.Gioco and tipo='release' and versione=new.versione;
    SELECT data_Versione  into dataBeta from versione where Gioco=new.Gioco and tipo='beta' and versione=new.versione;
    SELECT data_Versione into dataAlfa from versione where Gioco=new.Gioco and tipo='alfa' and versione=NEW.versione;
    SELECT data_Versione  into dataReleasePrec from versione where Gioco=new.Gioco and tipo='release' and versione=(left(new.versione,1) - 1);

    if new.tipo='beta' then
        if dataRelease is not null and new.data_Versione > dataRelease then
                SIGNAL sqlstate '45001' SET message_text = 'La beta deve essere piu vecchia della realese della stessa versione';
        elseif dataAlfa is not null and NEW.data_Versione < dataAlfa   then
                SIGNAL sqlstate '45001' SET message_text = 'La alfa deve essere piu vecchia della beta della stessa versione';
        elseif dataReleasePrec is not null and new.data_Versione < dataReleasePrec then
                SIGNAL sqlstate '45001' SET message_text = 'La release della versione precedente deve essere piu vecchia della beta';
        end if;
    end if;

    if new.tipo='alfa' then
        if dataBeta is not null and new.data_Versione > dataBeta then
                SIGNAL sqlstate '45001' SET message_text = 'La alfa deve essere piu vecchia della beta della stessa versione';
        elseif dataReleasePrec is not null and new.data_Versione < dataReleasePrec then
                SIGNAL sqlstate '45001' SET message_text = 'La release della versione precedente deve essere piu vecchia della alfa';
        elseif dataRelease is not null and new.data_Versione > dataRelease then
                SIGNAL sqlstate '45001' SET message_text = 'La alfa deve essere piu vecchia della realese della stessa versione';
        end if;
    end if;

    if new.tipo='release' then
        if dataReleasePrec is not null and new.data_Versione < dataReleasePrec then
                SIGNAL sqlstate '45001' SET message_text = 'La release della versione precedente deve essere piu vecchia della release';
        elseif dataAlfa is not null and NEW.data_Versione < dataAlfa   then
            SIGNAL sqlstate '45001' SET message_text = 'La alfa deve essere piu vecchia della release della stessa versione';
        elseif dataBeta is not null and new.data_Versione < dataBeta then
                SIGNAL sqlstate '45001' SET message_text = 'La beta deve essere piu vecchia della release della stessa versione';
        end if;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-03 15:13:39
