-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 09. Jan 2020 um 18:34
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
  `location` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `event`
--

INSERT INTO `event` (`ID`, `owner`, `name`, `descr`, `location`) VALUES
(7, 4, 'te', 'teDes', 4);

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
(13, 7, NULL, 4, 5, 1);

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
(4, 'tl', 'tl', '0,0', 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ticket`
--

CREATE TABLE `ticket` (
  `ID` int(10) UNSIGNED NOT NULL,
  `owner` int(10) UNSIGNED NOT NULL,
  `event` int(10) UNSIGNED NOT NULL,
  `name` varchar(64) NOT NULL,
  `qrdata` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(4, 'ta', NULL, 'ta', 'ta', 'd4ebfb3c5313c8fa330d114e563519aa2c83b17a10d6ccec3eba652b4c58145e'),
(5, 'tb', NULL, 'tb', 'tb', '0348c8effd0882e2929e788198ca31ff204be287dd419f81d30159ad928ebbc0');

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
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT für Tabelle `invitation`
--
ALTER TABLE `invitation`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT für Tabelle `location`
--
ALTER TABLE `location`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
