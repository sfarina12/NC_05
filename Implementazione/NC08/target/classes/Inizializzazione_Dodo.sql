
drop database if exists dodo;
create database dodo;
use dodo;


CREATE TABLE IF NOT EXISTS `dodo`.`utente` (
  `Mail` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(40) NOT NULL,
  `Nickname` VARCHAR(50) NOT NULL,
  `is_Admin` TINYINT NOT NULL,
  PRIMARY KEY (`Mail`));


CREATE TABLE IF NOT EXISTS `dodo`.`prodotto` (
  `ISBN` VARCHAR(13) NOT NULL,
  `Titolo` VARCHAR(100) NOT NULL,
  `Autore` VARCHAR(100) NOT NULL,
  `Prezzo` FLOAT NOT NULL,
  `Copertina` LONGBLOB NULL,
  `Descrizione` VARCHAR(500) NOT NULL,
  `Nome_Categoria` VARCHAR(50) NOT NULL,
  `Quantita_Stock` INT NOT NULL,
  PRIMARY KEY (`ISBN`));


CREATE TABLE IF NOT EXISTS `dodo`.`ordine` (
  `ID_Ordine` INT NOT NULL AUTO_INCREMENT,
  `Data` DATE NOT NULL,
  `Totale` FLOAT NOT NULL,
  `Metodo_Pagamento` TINYINT NOT NULL,
  `Indirizzo` VARCHAR(50) NOT NULL,
  `utente_Mail` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`ID_Ordine`),
  INDEX `fk_ordine_utente_idx` (`utente_Mail` ASC),
  CONSTRAINT `fk_ordine_utente`
    FOREIGN KEY (`utente_Mail`)
    REFERENCES `dodo`.`utente` (`Mail`));


CREATE TABLE IF NOT EXISTS `dodo`.`composizione` (
  `ordine_ID_Ordine` INT NOT NULL,
  `prodotto_ISBN` VARCHAR(13) NOT NULL,
  `quantita` INT NOT NULL,
  PRIMARY KEY (`ordine_ID_Ordine`, `prodotto_ISBN`),
  INDEX `fk_ordine_has_prodotto_prodotto1_idx` (`prodotto_ISBN` ASC),
  INDEX `fk_ordine_has_prodotto_ordine1_idx` (`ordine_ID_Ordine` ASC) VISIBLE,
  CONSTRAINT `fk_ordine_has_prodotto_ordine1`
    FOREIGN KEY (`ordine_ID_Ordine`)
    REFERENCES `dodo`.`ordine` (`ID_Ordine`),
  CONSTRAINT `fk_ordine_has_prodotto_prodotto1`
    FOREIGN KEY (`prodotto_ISBN`)
    REFERENCES `dodo`.`prodotto` (`ISBN`));
#Inserimento Prodotti

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804612841, "Il mondo segreto di Babbo Natale. Edizione Illustrata" ,"Alan Snow" , 20, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 9);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788845293894, "Lettere da Babbo Natale" ,"J.R.R. Tolkien" , 14, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 10);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788867225088, "La Bella e la Bestia. Edizione integrale" ,"Gabrielle-Suzanna Barbot de Villenueve" , 30, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788867224555, "Alice nel paese delle meraviglia - Al di là dello specchio. Edizione integrale" ,"Lewis Carroll" , 30, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788867224937, "Le avventure di Pinocchio. Edizione integrale" ,"Carlo Collodi" , 30, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788857016610, "Mano Manina. Edizione a colori" ,"Fomentini / Crovara" , 9, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788865932773, "Rosso, blu e... Tocco, sento e imparo i colori" ,"Jenny Cooper" , 10, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 6);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788858027561, "Il libro della calma di Lupetto. Amico Lupo" ,"Orianne Lallemand" , 10, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788807923333, "Opinioni di un gatto" ,"Jutta Bauer" , 13, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788898947577, "Il cuoco delle emozioni" ,"Daniele Bergesio" , 17, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 6);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788856674347, "Il sigillo del gatto. Le avventure di Sherlocco" ,"Geronimo Stilton" , 10.5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804730422, "Storie della buonanotte per bambine ribelli. 100 donne migranti che hanno cambiato il mondo" ,"Favilli" , 20, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804676379, "Storie della buonanotte per bambine ribelli. 100 vite di donne straordinarie" ,"Favilli/Cavallo" , 20, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804688846, "Storie della buonanotte per bambine ribelli 2" ,"Favilli/Cavallo" , 20, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 8);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788817149228, "La più grande" ,"Davide Morosinotto" , 17, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788851180751, "101 cose da scrivere per fare il fenomeno e lasciare tutti di stucco con le parole anche alle medie!" ,"Annalisa Strada" , 15.5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 6);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788880334392, "Diario di una schiappa." ,"Jeff Kinney" , 13, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788869662584, "Diario di una schiappa. Avanti tutta!" ,"Jeff Kinney" , 13, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788856656572, "Nel regno della Fantasia" ,"Geronimo Stilton" , 25, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788856634631, "Lo strano caso dei formaggi strapuzzoni" ,"Geronimo Stilton" , 9.2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Bambini e ragazzi", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788874526758, "Ragazze elettriche" ,"Naomi Alderman" , 18, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 2);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804666851, "Guida galattica per gli autostoppisti" ,"Douglas Adams" , 16.5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788806219369, "La strada" ,"Cormac McCarty" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804707035, "Io, robot" ,"Isaac Asimov" , 14, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 1);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804665298, "Fahrenheit 451" ,"Ray Bradbury" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804668169, "Cronache marziane" ,"Ray Bradbury" , 13, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 2);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788838929106, "Solaris" ,"Stanislaw Lem" , 14, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804664994, "Ciclo delle Fondazioni. Prima Fondazione-Fondazione e impero-Seconda Fondazione" ,"Isaac Asimov" , 16, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788834738955, "La svastica sul sole" ,"Philip K. Dick" , 16, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788834738887, "Ma gli androidi sognano pecore elettriche?" ,"Philip K. Dick" , 16, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 6);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804670520, "Il mondo nuovo" ,"Aldous Huxley" , 14, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788806240363, "La macchina del tempo" ,"G.W. Wells" , 10, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788822719713, "La guerra dei mondi" ,"G.W. Wells" , 4.9, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788834739679, "Dune" ,"Frank Herbert" , 20, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 6);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788807884894, "Il condominio" ,"J. G. Ballard" , 9.5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788831003384, "Harry Potter e la pietra filosofale" ,"J. K. Rowling" , 10, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788893814515, "Harry Potter e la camera dei segreti" ,"J. K. Rowling" , 11, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 13);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788893814522, "Harry Potter e il prigioniero di Azkaban" ,"J. K. Rowling" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 21);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788893814539, "Harry Potter e il calice di fuoco" ,"J. K. Rowling" , 15, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788893814546, "Harry Potter e l ordine della fenice" ,"J. K. Rowling" , 16, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 2);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788893814553, "Harry Potter e il principe mezzosangue" ,"J. K. Rowling" , 14, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788893814560, "Harry Potter e i doni della morte" ,"J. K. Rowling" , 15, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 2);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788830104716, "Il Signore degli Anelli" ,"J.R.R. Tolkien" , 50, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 2);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788845268342, "Lo Hobbit" ,"J.R.R. Tolkien" , 11, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788845272400, "Il Silmarillion" ,"J.R.R. Tolkien" , 13.5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fantasy e Fantascienza", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788845296499, "Sapiens" ,"Yuval Noah Harari" , 16, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Saggistica", 6);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788845298752, "Homo Deus" ,"Yuval Noah Harari" , 16, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Saggistica", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788830100824, "21 lezioni per il XXI secolo" ,"Yuval Noah Harari" , 16, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Saggistica", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804727880, "Illuminismo adesso" ,"Steven Pinker" , 18, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Saggistica", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788850254811, "Breve storia di (quasi) tutto" ,"Bill Bryson" , 16, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Saggistica", 8);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788823521216, "Possiamo salvare il mondo, prima di cena. Perchè il clima siamo noi" ,"Jonathan Safran Foer" , 18, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Saggistica", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788845932984, "Essere una macchina." ,"Mark O Connell" , 19, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Saggistica", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788867224012, "Infografica della Seconda Guerra Mondiale" ,"AAVV" , 25, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Saggistica", 8);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804637493, "Il declino della violenza" ,"Steven Pinker" , 21, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Saggistica", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788806219222, "Armi, acciaio, malattie" ,"Jared Diamond" , 14, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Saggistica", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788807883774, "Sud e magia" ,"Ernesto De Martino" , 10, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Saggistica", 8);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804668237, "1984" ,"George Orwell" , 14, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa straniera", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788807900716, "Frankenstein" ,"Mary Shelley" , 9.5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa straniera", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804721871, "La nona casa" ,"Leigh Bardugo" , 20, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa straniera", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804645731, "Giappone" ,"AAVV" , 20, "Itinerari e segreti del Giappone", "Viaggi", 8);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804612331, "Francia" ,"AAVV" , 20, "Itinerari e segreti della Francia", "Viaggi", 9);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788345645731, "Spagna" ,"AAVV" , 20, "Itinerari e segreti della Spagna", "Viaggi", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788674345731, "India" ,"AAVV" , 20, "Itinerari e segreti dell India", "Viaggi", 6);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788523645731, "Corea" ,"AAVV" , 20, "Itinerari e segreti della Corea", "Viaggi", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804325645, "Roma" ,"AAVV" , 20, "Itinerari e segreti di Roma", "Viaggi", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788822345623, "Sicilia" ,"AAVV" , 20, "Itinerari e segreti della Sicilia", "Viaggi", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788423454331, "Venezia e il Veneto" ,"AAVV" , 20, "Itinerari e segreti di Venezia e il Veneto", "Viaggi", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788243433531, "Orgoglio e pregiudizio" ,"Jane Austen" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa straniera", 8);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788123433531, "Cime tempestose" ,"Emily Bronte" , 10, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa straniera", 9);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9781245562344, "Alla ricerca del tempo perduto" ,"Marcel Proust" , 30, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa straniera", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788243234546, "L amico ritrovato" ,"Fred Uhlman" , 8, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa straniera", 6);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788243234562, "Tenera è la notte" ,"Francis Scott Fitzgerald" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa straniera", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788243243566, "Il grande Gatsby" ,"Francis Scott Fitzgerald" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa straniera", 8);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788242345432, "La peste" ,"Albert Camus" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa straniera", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788243245621, "Uno, nessuno e centomila" ,"Luigi Pirandello" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa italiana", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788243432345, "Novelle per un anno" ,"Luigi Pirandello" , 8, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa italiana", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788243324531, "Il fu Mattia Pascal" ,"Luigi Pirandello" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa italiana", 6);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788123124131, "I Malavoglia" ,"Giovanni Verga" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa italiana", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788242141213, "Mastro Don Gesualdo" ,"Giovanni Verga" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa italiana", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788124453223, "La coscienza di Zeno" ,"Italo Svevo" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa italiana", 9);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788881232342, "Divina Commedia" ,"Dante Alighieri" , 5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa italiana", 6);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788232452222, "Se una notte d inverno un viaggiatore" ,"Italo Calvino" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa italiana", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788243214531, "Il barone rampante" ,"Italo Calvino" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa italiana", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788342553531, "Il nome della rosa" ,"Umberto Eco" , 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa italiana", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788245335872, "L isola di Arturo" ,"Elsa Morante" , 13, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Narrativa italiana", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788864200330, "Le bizzarre avventure di JOJO - PHANTOM BLOOD 1" ,"Hirohiko Araki" , 7, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fumetti e manga", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788864200415, "Le bizzarre avventure di JOJO - PHANTOM BLOOD 2" ,"Hirohiko Araki" , 7, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fumetti e manga", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788864200439, "Le bizzarre avventure di JOJO - PHANTOM BLOOD 3 (M3)" ,"Hirohiko Araki" , 7, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fumetti e manga", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788891286840, "Beastars Vol 1" ,"Paru Itagaki" , 5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fumetti e manga", 3);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788891282124, "Beastars Vol 8" ,"Paru Itagaki" , 5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fumetti e manga", 2);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788891286324, "Beastars Vol 12" ,"Paru Itagaki" , 5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fumetti e manga", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788864678415, "Demon Slayer Vol 1" ,"Koyoharu Gotoge" , 8, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fumetti e manga", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788864200243, "Demon Slayer Vol 2" ,"Koyoharu Gotoge" , 8, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fumetti e manga", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788864200343, "Demon Slayer Vol 3" ,"Koyoharu Gotoge" , 8, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fumetti e manga", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788893517140, "V per Vendetta" ,"Alan Moore" , 25.5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Fumetti e manga", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804726685, "Il tempo della clemenza" ,"John Grisham" , 22, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Gialli Thriller e Horror", 8);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804717638, "Gli invisibili" ,"Valerio Varesi" , 16, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Gialli Thriller e Horror", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788842819813, "Psyco" ,"Robert Bloch" , 15, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Gialli Thriller e Horror", 6);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804710318, "Dieci piccoli indiani" ,"Agatha Cristie" , 13, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Gialli Thriller e Horror", 9);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804679387, "Assassinio sul Nilo" ,"Agatha Cristie" , 13, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Gialli Thriller e Horror", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788868360818, "Le notti di Salem" ,"Stephen King" , 20, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Gialli Thriller e Horror", 5);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788820067748, "Pet Sematary" ,"Stephen King" , 20, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Gialli Thriller e Horror", 4);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788820068288, "Se scorre il sangue" ,"Stephen King" , 20, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Gialli Thriller e Horror", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788804671619, "Dracula" ,"Bram Stoker" , 13, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Gialli Thriller e Horror", 7);

INSERT INTO `Prodotto` (`ISBN`, `Titolo`, `Autore`, `Prezzo`, `Descrizione`, `Nome_Categoria`, `Quantita_Stock`) VALUE (9788868365622, "It" ,"Stephen King" , 20, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "Gialli Thriller e Horror", 7);

#Admin
INSERT INTO `Utente` (`Mail`, `Password`,`Nickname`,`is_Admin`) VALUE ("admin@dodo.it", "Admin","Admin", 1);
#Utenti
INSERT INTO `Utente` (`Mail`, `Password`, `Nickname`, `is_Admin`) VALUE ("sara@dodo.it", "password", "Saretta", 0);
INSERT INTO `Utente` (`Mail`, `Password`, `Nickname`, `is_Admin`) VALUE ("dario@dodo.it", "password", "Dariuccio", 0);
INSERT INTO `Utente` (`Mail`, `Password`, `Nickname`, `is_Admin`) VALUE ("maria@dodo.it", "password", "Viola", 0);
INSERT INTO `Utente` (`Mail`, `Password`, `Nickname`, `is_Admin`) VALUE ("quirinio@dodo.it", "password", "Il_Cantante", 0);
INSERT INTO `Utente` (`Mail`, `Password`, `Nickname`, `is_Admin`) VALUE ("asia@dodo.it", "password", "Asietta", 0);
#Ordini
INSERT INTO `Ordine` VALUES 
(1, '2021-01-12', 30.00, 1, 'Via infinita%24%70425%Firenze', "sara@dodo.it"),
(2, '2021-01-11', 40.00, 1, 'Viale Romano%28%80058%Scafati', "quirinio@dodo.it"),
(3, '2020-12-23', 10.00, 1, 'Via delle Grazie%5%73904%Messina', "maria@dodo.it"),
(4, '2020-12-23', 100.00, 1, 'Corso dei Giovani%17%39746%Roma', "dario@dodo.it"),
(5, '2020-12-12', 20.00, 1, 'Via delle Rose%8%74967%Potenza', "asia@dodo.it"),
(6, '2020-12-12', 10.00, 1, 'Via Bella%77%67676%Sassari', "sara@dodo.it");
#Composizione carrelli
INSERT INTO `Composizione` VALUES 
(1,9788807883774,1),
(1,9788806240363,1),
(1,9788804668169,1),
(2,9788898947577,1),
(2,9788807923333,2),
(3,9788820068288,1),
(4,9788804710318,1),
(4,9788804726685,1),
(4,9788124453223,1),
(4,9788243245621,1),
(4,9788243234546,1),
(5,9788523645731,1),
(6,9788806219222,1);