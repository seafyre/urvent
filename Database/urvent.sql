-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 11. Jan 2020 um 12:26
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
(7, 4, 'te', 'teDes', 4),
(8, 4, 'te2', 'te2Des', 4),
(9, 4, 'te3', 'te3Des', 4),
(10, 5, 'tb\'s houseparty', '420 blaez it faget c:', 5),
(11, 4, 'ta\'s rudelbumsen', 'ta\'s rudelbumsen is a fuckfest', 6);

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
(19, 11, NULL, 4, 5, 1);

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
(6, 'ta\'s pornokeller', 'ta\'s pornokeller offers drinks drugs and fucks', '51.514244,7.468429', 4);

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
(7, 5, 11, 19, '39ad42d3889f54b2b0e20ad1cfcb52988089569193345c278111dcce3e74178c', 0);

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
(4, 'ta', NULL, 'ta', 'ta', '33d4af4d581ad374a3c7083618f8f6cec8fb7561fce193118eacf3e189ce290b'),
(5, 'tb', NULL, 'tb', 'tb', '1aad0c9ccc7cefb5e4b2ac7e09372d1f6ec2e2349982b046b63eab7ff7ee108f');

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
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT für Tabelle `invitation`
--
ALTER TABLE `invitation`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT für Tabelle `location`
--
ALTER TABLE `location`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT für Tabelle `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
