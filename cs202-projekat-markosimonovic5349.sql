-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2024 at 01:09 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cs202-projekat-markosimonovic5349`
--

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `userName` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `brojTelefona` varchar(30) NOT NULL,
  `adresa` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`userName`, `password`, `brojTelefona`, `adresa`) VALUES
('Marko03', 'marecar', '063581628', 'Branka Miljkovica 39'),
('Veljko01', 'vexcar', '0691102591', 'Branka Miljkovica 39');

-- --------------------------------------------------------

--
-- Table structure for table `korpa`
--

CREATE TABLE `korpa` (
  `porudzbina_FK` int(11) NOT NULL,
  `putanjaSLike_FK` varchar(30) NOT NULL,
  `kolicina` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

--
-- Dumping data for table `korpa`
--

INSERT INTO `korpa` (`porudzbina_FK`, `putanjaSLike_FK`, `kolicina`) VALUES
(30, 'duks', 1),
(30, 'majica', 5),
(30, 'kapa', 2),
(31, 'duks', 1),
(32, 'duks', 1),
(33, 'majica', 1),
(34, 'kapa', 6),
(34, 'majica', 66),
(34, 'duks', 6),
(35, 'duks', 1),
(36, 'duks', 1),
(37, 'duks', 3),
(37, 'kapa', 3),
(38, 'duks', 1),
(38, 'kapa', 2);

-- --------------------------------------------------------

--
-- Table structure for table `porudzbina`
--

CREATE TABLE `porudzbina` (
  `porudzbina_ID` int(11) NOT NULL,
  `userName_FK` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

--
-- Dumping data for table `porudzbina`
--

INSERT INTO `porudzbina` (`porudzbina_ID`, `userName_FK`) VALUES
(32, 'Marko03'),
(33, 'Marko03'),
(34, 'Marko03'),
(35, 'Marko03'),
(36, 'Marko03'),
(37, 'Marko03'),
(38, 'Marko03'),
(30, 'Veljko01'),
(31, 'Veljko01');

-- --------------------------------------------------------

--
-- Table structure for table `proizvod`
--

CREATE TABLE `proizvod` (
  `putanjaSlike` varchar(30) NOT NULL,
  `naziv` varchar(30) NOT NULL,
  `cena` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

--
-- Dumping data for table `proizvod`
--

INSERT INTO `proizvod` (`putanjaSlike`, `naziv`, `cena`) VALUES
('duks', 'Duks', 500),
('kapa', 'Kapa', 150),
('majica', 'Majica', 200);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`userName`);

--
-- Indexes for table `korpa`
--
ALTER TABLE `korpa`
  ADD KEY `porudzbina_FK` (`porudzbina_FK`),
  ADD KEY `putanjaSLike_FK` (`putanjaSLike_FK`);

--
-- Indexes for table `porudzbina`
--
ALTER TABLE `porudzbina`
  ADD PRIMARY KEY (`porudzbina_ID`),
  ADD KEY `userName_FK` (`userName_FK`);

--
-- Indexes for table `proizvod`
--
ALTER TABLE `proizvod`
  ADD PRIMARY KEY (`putanjaSlike`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `porudzbina`
--
ALTER TABLE `porudzbina`
  MODIFY `porudzbina_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `porudzbina`
--
ALTER TABLE `porudzbina`
  ADD CONSTRAINT `userName_FK` FOREIGN KEY (`userName_FK`) REFERENCES `korisnik` (`userName`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
