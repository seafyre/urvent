-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 12. Jan 2020 um 23:14
-- Server-Version: 10.1.35-MariaDB
-- PHP-Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `urvent`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `closedevent`
--

CREATE TABLE `closedevent` (
  `ID` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `event`
--

CREATE TABLE `event` (
  `ID` int(10) UNSIGNED NOT NULL,
  `owner` int(10) UNSIGNED NOT NULL,
  `name` varchar(64) NOT NULL,
  `descr` text NOT NULL,
  `location` int(10) UNSIGNED NOT NULL,
  `date` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `event`
--

INSERT INTO `event` (`ID`, `owner`, `name`, `descr`, `location`, `date`) VALUES
(7, 4, 'te', 'teDes', 4, '01.02.2020'),
(8, 4, 'te2', 'te2Des', 4, '01.02.2020'),
(9, 4, 'te3', 'te3Des', 4, '01.02.2020'),
(10, 5, 'tb\'s houseparty', '420 blaez it faget c:', 5, '01.02.2020'),
(11, 4, 'ta\'s brave teeparty', 'hier stand vorher mal etwas anderes', 6, '01.02.2020'),
(12, 4, 'ta\'s bbq', 'ta has birthday and you\'re invited :DD', 7, '01.02.2020'),
(13, 6, 'tc\'s pub party', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.', 8, '01.02.2020'),
(14, 6, 'Anonyme Irgendwas Treffen', 'Was auch immer', 8, NULL),
(15, 7, 'Td\'s houseParty', 'just a houseparty, get drunk!', 9, NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `invitation`
--

CREATE TABLE `invitation` (
  `ID` int(10) UNSIGNED NOT NULL,
  `relatedEvent` int(10) NOT NULL,
  `relatedTicket` int(10) UNSIGNED DEFAULT NULL,
  `host` int(10) UNSIGNED NOT NULL,
  `guest` int(10) UNSIGNED NOT NULL,
  `accepted` int(10) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `invitation`
--

INSERT INTO `invitation` (`ID`, `relatedEvent`, `relatedTicket`, `host`, `guest`, `accepted`) VALUES
(18, 10, NULL, 5, 4, 1),
(19, 11, NULL, 4, 5, 1),
(20, 12, NULL, 4, 5, 1),
(21, 9, NULL, 4, 5, 1),
(22, 13, NULL, 6, 4, 1),
(23, 13, NULL, 6, 5, 1),
(27, 11, NULL, 4, 6, 1),
(28, 14, NULL, 6, 5, 1),
(29, 15, NULL, 7, 4, 1),
(30, 15, NULL, 7, 5, 0),
(31, 11, NULL, 4, 5, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `location`
--

CREATE TABLE `location` (
  `ID` int(10) UNSIGNED NOT NULL,
  `name` varchar(64) NOT NULL,
  `descr` varchar(128) NOT NULL,
  `coordinates` varchar(32) NOT NULL,
  `owner` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `location`
--

INSERT INTO `location` (`ID`, `name`, `descr`, `coordinates`, `owner`) VALUES
(4, 'tl', 'tl', '0,0', 4),
(5, 'tb\'s home', '@ home', '	2.046934,	45.318161', 5),
(6, 'ta\'s pornokeller', 'ta\'s pornokeller offers drinks and porn', '51.514244,7.468429', 4),
(7, 'Candyland', 'A place of magic and wonders', '-19.258965,146.816956', 4),
(8, 'tc\'s Home', 'This is where tc lives ', '53.116688,-2.175320', 6),
(9, 'TD\'s home', 'this is where td lives', '52.520008,13.404954', 7);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ticket`
--

CREATE TABLE `ticket` (
  `ID` int(10) UNSIGNED NOT NULL,
  `owner` int(10) UNSIGNED NOT NULL,
  `event` int(10) UNSIGNED NOT NULL,
  `invitation` int(10) NOT NULL,
  `code` varchar(128) NOT NULL,
  `redeemed` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `ticket`
--

INSERT INTO `ticket` (`ID`, `owner`, `event`, `invitation`, `code`, `redeemed`) VALUES
(6, 4, 10, 18, 'a97d485cc821dd8063d6c915d3f2d194d74368ee1d5e5ec4b08946d751cc53fd', 0),
(7, 5, 11, 19, '39ad42d3889f54b2b0e20ad1cfcb52988089569193345c278111dcce3e74178c', 0),
(8, 5, 12, 20, '864202b545d5e6c46b32ffa143eb3da9d7710c76cbe1c1b189d321a61fa0d7a2', 0),
(9, 5, 9, 21, 'f5f3af89b5e1ab0c740bfeacedcb922765c619d2992c4f2f8b6169ba95c88c69', 0),
(10, 4, 13, 22, '384864e5cd60f52705e71247b76e2d1bac59ca2229c82c8b237c199fcd3c8577', 0),
(11, 6, 11, 27, 'c3d755f29cac645fc44d493b6bc0de13252f8a4009f5d6a5ff2a38d73b7a97b0', 0),
(12, 5, 13, 23, '602fb90726d51b3c5727a31651af0a44e0c109c86e3e9cbb11695bac1511d438', 0),
(13, 5, 14, 28, '6c15b18171af7ccc3fff012568c691447112f14f11c5ddd6fbd9c96d52449ff4', 0),
(14, 4, 15, 29, '7ec9c0e14ae6c2036e0ba59e30c22539e1d9d9be0ad66681cef67a47c8411e25', 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `ID` int(10) UNSIGNED NOT NULL,
  `name` varchar(64) NOT NULL,
  `descr` text,
  `mail` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `loginToken` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`ID`, `name`, `descr`, `mail`, `password`, `loginToken`) VALUES
(4, 'Test User jr.', 'This is a nice little testaccount description for the user :)', 'ta@a.a', 'ta', '0f60d2801649cd21ed56f7cf15e5fa4941122a6c3515d62c0aa8c23fa8eb047e'),
(5, 'TestUser Bravo', 'Bravo Test Description', 'tb', 'tb', 'c4fe57ad315e85c266335f4e42e2c7604749cde2525ac88a09d4ad9c5d541150'),
(6, 'tc', NULL, 'tc', 'tc', 'ca7a3d657b9d5de755d7eeed3c93c6ff7e5c839a1533f72f67fe822ebb3b338f'),
(7, 'TestUser Delta', 'Fourth TestUSer', 'td@d.d', 'td', '0796299245dd462742424c8b89821816908e7d57240b3b60b1287e68a2d3bf94'),
(8, 'te', NULL, 'te', 'te', 'e3b9a3e25463455d1fd6773ab6d9de9495f300615e9c7ade543b0b1af3646d16');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `closedevent`
--
ALTER TABLE `closedevent`
  ADD PRIMARY KEY (`ID`);

--
-- Indizes für die Tabelle `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `location` (`location`),
  ADD KEY `owner` (`owner`);

--
-- Indizes für die Tabelle `invitation`
--
ALTER TABLE `invitation`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `host` (`host`,`guest`),
  ADD KEY `invitation_guest` (`guest`);

--
-- Indizes für die Tabelle `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `owner` (`owner`);

--
-- Indizes für die Tabelle `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `owner` (`owner`,`event`),
  ADD KEY `ticket_event` (`event`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `closedevent`
--
ALTER TABLE `closedevent`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `event`
--
ALTER TABLE `event`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT für Tabelle `invitation`
--
ALTER TABLE `invitation`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT für Tabelle `location`
--
ALTER TABLE `location`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT für Tabelle `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_location` FOREIGN KEY (`location`) REFERENCES `location` (`ID`),
  ADD CONSTRAINT `event_owner` FOREIGN KEY (`owner`) REFERENCES `user` (`ID`);

--
-- Constraints der Tabelle `invitation`
--
ALTER TABLE `invitation`
  ADD CONSTRAINT `invitation_guest` FOREIGN KEY (`guest`) REFERENCES `user` (`ID`),
  ADD CONSTRAINT `invitation_host` FOREIGN KEY (`host`) REFERENCES `user` (`ID`);

--
-- Constraints der Tabelle `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `location_user` FOREIGN KEY (`owner`) REFERENCES `user` (`ID`);

--
-- Constraints der Tabelle `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_event` FOREIGN KEY (`event`) REFERENCES `event` (`ID`),
  ADD CONSTRAINT `ticket_user` FOREIGN KEY (`owner`) REFERENCES `user` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
