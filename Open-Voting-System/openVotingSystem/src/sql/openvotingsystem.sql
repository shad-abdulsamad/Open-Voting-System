-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2022 at 04:47 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `openvotingsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `nationalId` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phoneNumber` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`nationalId`, `name`, `email`, `password`, `phoneNumber`) VALUES
('1999', 'rafaat', 'rafaat@gmail.com', '12345', '4764453'),
('2002', 'milad', 'milad@gmail.com', '54321', '909090');

-- --------------------------------------------------------

--
-- Table structure for table `candidates`
--

CREATE TABLE `candidates` (
  `nationalId` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phoneNumber` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `groupp` varchar(50) NOT NULL,
  `zone` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `candidates`
--

INSERT INTO `candidates` (`nationalId`, `name`, `email`, `phoneNumber`, `password`, `address`, `groupp`, `zone`) VALUES
('2001', 'karwan', 'shad@gmail.com', '55555555', '123', 'gulan', 'parti', 'suli'),
('1002', 'mohammed', 'mohammed@gmail.com', '3333333333', '321', 'hills', 'glopyNwe', 'erbil'),
('1234', 'Rahen', 'rahen@email.com', '123456789', '12345', 'nwe hawler', 'Trump', 'erbil'),
('5555', 'Shad', 'shad@email.com', '66666666', 'shad', 'bahrka', 'erbil', 'erbil');

-- --------------------------------------------------------

--
-- Table structure for table `voter`
--

CREATE TABLE `voter` (
  `nationalId` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phoneNumber` varchar(50) NOT NULL,
  `age` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `zone` varchar(50) NOT NULL,
  `isVoted` varchar(10) NOT NULL DEFAULT 'false',
  `firstVote` int(11) NOT NULL,
  `secondVote` int(11) NOT NULL,
  `thirdVote` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `voter`
--

INSERT INTO `voter` (`nationalId`, `name`, `email`, `password`, `phoneNumber`, `age`, `address`, `zone`, `isVoted`, `firstVote`, `secondVote`, `thirdVote`) VALUES
('1015', 'hawar', 'hawar@email.com', '12345', '123456789', '25', 'saholaka', 'suli', '1', 5555, 1234, 2001),
('1045', 'hana', 'hana@email.com', '12345', '123456789', '25', 'saholaka', 'suli', '1', 5555, 2001, 1234),
('2005', 'ali', 'ali@gmail.com', '54321', '5665656', 'pavilion', 'erbil', '5001', '1', 2001, 5555, 1234),
('2376', 'faradiun', 'faraidun@email.com', '12345', '3847928347', '20', 'mamosta', 'erbil', '1', 1234, 5555, 2001),
('4475', 'bahez', 'bahez@email.com', '12345', '4389723', '37', '120 hasarok', 'erbil', '1', 1234, 5555, 2001),
('5002', 'hiwa', 'hiwa@gmail.com', '12345', '111111', '25', 'iskan', 'erbil', '1', 2001, 5555, 1234),
('8475', 'hedi', 'hedi@email.com', '12345', '4389723', '37', '5 hasarok', 'erbil', 'false', 0, 0, 0),
('9876', 'Aram', 'aram@email.com', '12345', '3847928347', '20', 'mamosta', 'erbil', '1', 1234, 1002, 5555);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `voter`
--
ALTER TABLE `voter`
  ADD PRIMARY KEY (`nationalId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
