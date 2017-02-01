-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bepro
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bepro
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bepro` DEFAULT CHARACTER SET utf8 ;
USE `bepro` ;

-- -----------------------------------------------------
-- Table `bepro`.`adminprofile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`adminprofile` (
  `idadminprofile` INT(11) NOT NULL AUTO_INCREMENT,
  `privileges` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idadminprofile`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`specifications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`specifications` (
  `idspecifications` INT(11) NOT NULL AUTO_INCREMENT,
  `namevalue` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idspecifications`),
  UNIQUE INDEX `namevalue_UNIQUE` (`namevalue` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`tests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`tests` (
  `idtests` INT(11) NOT NULL AUTO_INCREMENT,
  `idspecifications` VARCHAR(45) NULL DEFAULT NULL,
  `specifications_idspecifications` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idtests`),
  INDEX `fk_tests_specifications1_idx` (`specifications_idspecifications` ASC),
  CONSTRAINT `fk_tests_specifications1`
    FOREIGN KEY (`specifications_idspecifications`)
    REFERENCES `bepro`.`specifications` (`idspecifications`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`testquests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`testquests` (
  `idtestquests` INT(11) NOT NULL AUTO_INCREMENT,
  `value` LONGTEXT NULL DEFAULT NULL,
  `idtest` INT(11) NULL DEFAULT NULL,
  `tests_idtests` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idtestquests`),
  INDEX `fk_testquests_tests1_idx` (`tests_idtests` ASC),
  CONSTRAINT `fk_testquests_tests1`
    FOREIGN KEY (`tests_idtests`)
    REFERENCES `bepro`.`tests` (`idtests`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`answer` (
  `idanswer` INT(11) NOT NULL,
  `value` VARCHAR(45) NULL DEFAULT NULL,
  `testquests_idtestquests` INT(11) NOT NULL,
  `rightanswer` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idanswer`),
  INDEX `fk_answer_testquests1_idx` (`testquests_idtestquests` ASC),
  CONSTRAINT `fk_answer_testquests1`
    FOREIGN KEY (`testquests_idtestquests`)
    REFERENCES `bepro`.`testquests` (`idtestquests`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`companyprofile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`companyprofile` (
  `idcompanyprofile` INT(11) NOT NULL AUTO_INCREMENT,
  `infotxt` LONGTEXT NULL DEFAULT NULL,
  `imgurl` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`idcompanyprofile`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`courses` (
  `idcourses` INT(11) NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(255) NULL DEFAULT NULL,
  `specname` LONGTEXT NULL DEFAULT NULL,
  `specifications_idspecifications` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idcourses`),
  UNIQUE INDEX `url_UNIQUE` (`url` ASC),
  INDEX `fk_courses_specification_idx` (`specifications_idspecifications` ASC),
  CONSTRAINT `fk_courses_specification`
    FOREIGN KEY (`specifications_idspecifications`)
    REFERENCES `bepro`.`specifications` (`idspecifications`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 91
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`jobofferinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`jobofferinfo` (
  `idjobofferinfo` INT(11) NOT NULL,
  `fulltextvalue` LONGTEXT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`joboffers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`joboffers` (
  `idjoboffers` INT(11) NOT NULL AUTO_INCREMENT,
  `companyprofile_idcompanyprofile` INT(11) NOT NULL DEFAULT '0',
  `jobofferstext` LONGTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idjoboffers`),
  INDEX `fk_joboffers_companyprofile1_idx` (`companyprofile_idcompanyprofile` ASC),
  CONSTRAINT `fk_joboffers_companyprofile1`
    FOREIGN KEY (`companyprofile_idcompanyprofile`)
    REFERENCES `bepro`.`companyprofile` (`idcompanyprofile`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`joboffers_has_courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`joboffers_has_courses` (
  `joboffers_idjoboffers` INT(11) NOT NULL,
  `courses_idcourses` INT(11) NOT NULL,
  PRIMARY KEY (`joboffers_idjoboffers`, `courses_idcourses`),
  INDEX `fk_joboffers_has_courses_courses1_idx` (`courses_idcourses` ASC),
  INDEX `fk_joboffers_has_courses_joboffers1_idx` (`joboffers_idjoboffers` ASC),
  CONSTRAINT `fk_joboffers_has_courses_courses1`
    FOREIGN KEY (`courses_idcourses`)
    REFERENCES `bepro`.`courses` (`idcourses`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_joboffers_has_courses_joboffers1`
    FOREIGN KEY (`joboffers_idjoboffers`)
    REFERENCES `bepro`.`joboffers` (`idjoboffers`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`joboffers_has_specifications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`joboffers_has_specifications` (
  `joboffers_idjoboffers` INT(11) NOT NULL,
  `specifications_idspecifications` INT(11) NOT NULL,
  PRIMARY KEY (`joboffers_idjoboffers`, `specifications_idspecifications`),
  INDEX `fk_joboffers_has_specifications_specifications1_idx` (`specifications_idspecifications` ASC),
  CONSTRAINT `fk_joboffers_has_specifications_1`
    FOREIGN KEY (`joboffers_idjoboffers`)
    REFERENCES `bepro`.`joboffers` (`idjoboffers`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_joboffers_has_specifications_2`
    FOREIGN KEY (`specifications_idspecifications`)
    REFERENCES `bepro`.`specifications` (`idspecifications`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`profile` (
  `idprofile` INT(11) NOT NULL,
  `studentprofile_idstudentprofile` INT(11) NULL DEFAULT NULL,
  `companyprofile_idcompanyprofile` INT(11) NULL DEFAULT NULL,
  `adminprofile_idadminprofile` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idprofile`),
  INDEX `fk_profile_companyprofile1_idx` (`companyprofile_idcompanyprofile` ASC),
  INDEX `fk_profile_adminprofile1_idx` (`adminprofile_idadminprofile` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`studentprofile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`studentprofile` (
  `idstudentprofile` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL DEFAULT 'NoName',
  `country` VARCHAR(45) NULL DEFAULT NULL,
  `tel` VARCHAR(45) NULL DEFAULT NULL,
  `cvurl` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idstudentprofile`))
ENGINE = InnoDB
AUTO_INCREMENT = 102
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`studentprofile_has_courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`studentprofile_has_courses` (
  `studentprofile_idstudentprofile` INT(11) NOT NULL,
  `courses_idcourses` INT(11) NOT NULL,
  `imgurl` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`studentprofile_idstudentprofile`, `courses_idcourses`),
  INDEX `fk_studentprofile_has_courses_courses1_idx` (`courses_idcourses` ASC),
  INDEX `fk_studentprofile_has_courses_studentprofile1_idx` (`studentprofile_idstudentprofile` ASC),
  CONSTRAINT `fk_studentprofile_has_courses_courses1`
    FOREIGN KEY (`courses_idcourses`)
    REFERENCES `bepro`.`courses` (`idcourses`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_studentprofile_has_courses_studentprofile1`
    FOREIGN KEY (`studentprofile_idstudentprofile`)
    REFERENCES `bepro`.`studentprofile` (`idstudentprofile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`studentprofile_has_joboffers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`studentprofile_has_joboffers` (
  `studentprofile_idstudentprofile` INT(11) NOT NULL,
  `joboffers_idjoboffers` INT(11) NOT NULL,
  PRIMARY KEY (`studentprofile_idstudentprofile`, `joboffers_idjoboffers`),
  INDEX `fk_studentprofile_has_joboffers_joboffers1_idx` (`joboffers_idjoboffers` ASC),
  INDEX `fk_studentprofile_has_joboffers_studentprofile1_idx` (`studentprofile_idstudentprofile` ASC),
  CONSTRAINT `fk_studentprofile_has_joboffers_joboffers1`
    FOREIGN KEY (`joboffers_idjoboffers`)
    REFERENCES `bepro`.`joboffers` (`idjoboffers`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_studentprofile_has_joboffers_studentprofile1`
    FOREIGN KEY (`studentprofile_idstudentprofile`)
    REFERENCES `bepro`.`studentprofile` (`idstudentprofile`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`studentprofile_has_specifications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`studentprofile_has_specifications` (
  `studentprofile_idstudentprofile` INT(11) NOT NULL,
  `specifications_idspecifications` INT(11) NOT NULL,
  PRIMARY KEY (`studentprofile_idstudentprofile`, `specifications_idspecifications`),
  INDEX `fk_studentprofile_has_specifications_specifications1_idx` (`specifications_idspecifications` ASC),
  INDEX `fk_studentprofile_has_specifications_studentprofile1_idx` (`studentprofile_idstudentprofile` ASC),
  CONSTRAINT `fk_studentprofile_has_specifications_specifications1`
    FOREIGN KEY (`specifications_idspecifications`)
    REFERENCES `bepro`.`specifications` (`idspecifications`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_studentprofile_has_specifications_studentprofile1`
    FOREIGN KEY (`studentprofile_idstudentprofile`)
    REFERENCES `bepro`.`studentprofile` (`idstudentprofile`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bepro`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bepro`.`user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `nickname` VARCHAR(45) NULL DEFAULT NULL,
  `entrypassword` VARCHAR(45) NULL DEFAULT NULL,
  `profile_iduser` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC),
  INDEX `fk_user_profile_idx` (`profile_iduser` ASC),
  CONSTRAINT `fk_user_profile`
    FOREIGN KEY (`profile_iduser`)
    REFERENCES `bepro`.`profile` (`idprofile`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 251
DEFAULT CHARACTER SET = utf8;

USE `bepro` ;

-- -----------------------------------------------------
-- procedure addstudentscourse
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addstudentscourse`(idcourse int,studentid int ,img mediumtext)
BEGIN
insert into studentprofile_has_courses (studentprofile_idstudentprofile,courses_idcourses,imgurl) values (studentid,idcourse,img);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure deletecmpanyprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deletecmpanyprofile`(id int)
BEGIN

delete from companyprofile where idcompanyprofile=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure deletecourse
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deletecourse`(id int)
BEGIN
delete from courses where idcourses=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure deletejoboffer
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deletejoboffer`(id int)
BEGIN
delete from joboffers where idjoboffers=id;
delete from joboffers_has_specifications where joboffers_idjoboffers=id;
delete from joboffers_has_courses where joboffers_idjoboffers=id;
delete from jobofferinfo where idjobofferinfo=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure deleteprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteprofile`(id INT)
BEGIN
delete from profile where idprofile=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure deletespecification
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deletespecification`(id INT)
BEGIN
delete from specifications where idspecifications=id;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure deletestudentprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deletestudentprofile`(id INT)
BEGIN
delete from studentprofile where idstudentprofile=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure deletestudentscourse
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deletestudentscourse`(idcourse int,studentid int)
BEGIN
delete from studentprofile_has_courses where courses_idcourses=idcourse and studentprofile_idstudentprofile=studentid;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure deleteuser
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteuser`(thisid INT)
BEGIN
delete from user where iduser=thisid;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure existsstudentjoboffer
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `existsstudentjoboffer`(studid int,joid int)
BEGIN
SELECT * FROM bepro.studentprofile_has_joboffers where studentprofile_idstudentprofile=studid and joboffers_idjoboffers=joid;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getallstudentspassedcourses
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getallstudentspassedcourses`(studid int)
BEGIN
select * from courses join studentprofile_has_courses
on idcourses=courses_idcourses where studentprofile_idstudentprofile =studid;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertcompanyprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertcompanyprofile`(infotxt1 longtext,imgurl1 varchar(100))
BEGIN
insert into companyprofile(infotxt,imgurl) values(infotxt1,imgurl1);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertcourse
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertcourse`(urlt mediumtext,specnamet mediumtext,specid int)
BEGIN

Insert into courses (url,specname,specifications_idspecifications) values(urlt,specnamet,specid);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertjoboffer
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertjoboffer`(compid int,description longtext)
BEGIN
insert into joboffers(companyprofile_idcompanyprofile,jobofferstext) values(compid,description);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertprofile`(userid INT,stud INT,com INT,adm INT)
BEGIN
Insert into profile (idprofile,studentprofile_idstudentprofile,companyprofile_idcompanyprofile,adminprofile_idadminprofile) values(userid,stud,com,adm);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertspecification
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertspecification`(namev varchar(45))
BEGIN
insert into specifications(namevalue) values(namev);

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertstudentprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertstudentprofile`(name1 varchar(100) ,country1 varchar(100),tel1 varchar(100),cvurl1 varchar(100))
BEGIN
insert into studentprofile (username,country,tel,cvurl) values(name1,country1,tel1,cvurl1);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertuser
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertuser`(thisname varchar(100) ,thisemail varchar(40),thispassword varchar(25))
BEGIN
insert into user (email,nickname,entrypassword) values (thisemail,thisname,thispassword);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure jobofferswithCompannyNames
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `jobofferswithCompannyNames`()
BEGIN
select idjoboffers,companyprofile_idcompanyprofile,jobofferstext,infotxt from joboffers join companyprofile on idcompanyprofile=companyprofile_idcompanyprofile;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure newjobofferspecification
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `newjobofferspecification`(jobid int,specid int)
BEGIN
insert into joboffers_has_specifications(joboffers_idjoboffers,specifications_idspecifications) values (jobid,specid);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure newstudentsjoboffer
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `newstudentsjoboffer`(studid int,jobofferid int)
BEGIN
INSERT INTO `bepro`.`studentprofile_has_joboffers` (`studentprofile_idstudentprofile`, `joboffers_idjoboffers`) VALUES (studid, jobofferid);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure newstudentsspecification
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `newstudentsspecification`(studentprofid INT,specid INT)
BEGIN
insert into studentprofile_has_specifications(studentprofile_idstudentprofile,specifications_idspecifications) values(studentprofid,specid);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectAllJobOffersSpecifications
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectAllJobOffersSpecifications`(jobOfferID int)
BEGIN
select idspecifications, namevalue from (specifications join joboffers_has_specifications on idspecifications=specifications_idspecifications)
join joboffers on idjoboffers=joboffers_idjoboffers where idjoboffers=jobOfferID;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectAllStudentsSpecifications
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectAllStudentsSpecifications`(studentId int)
BEGIN
select idspecifications, namevalue from (specifications join studentprofile_has_specifications on idspecifications=specifications_idspecifications)
join studentprofile on idstudentprofile=studentprofile_idstudentprofile where idstudentprofile=studentId;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectallcompanyprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectallcompanyprofile`()
BEGIN
select * from companyprofile ;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectallcourse
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectallcourse`()
BEGIN

select * from courses ;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectalljoboffer
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectalljoboffer`()
BEGIN

select  * from joboffers;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectallprofiles
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectallprofiles`()
BEGIN
select * from profile;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectallspecification
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectallspecification`()
BEGIN
select * from specifications;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectallstudentprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectallstudentprofile`()
BEGIN
select * from studentprofile;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectallstudentsbyjoboffer
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectallstudentsbyjoboffer`(jobid int)
BEGIN
select * from studentprofile_has_joboffers join studentprofile on studentprofile_idstudentprofile=idstudentprofile  where joboffers_idjoboffers=jobid;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectallstudentsjoboffers
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectallstudentsjoboffers`(studid int)
BEGIN
select idjoboffers,companyprofile_idcompanyprofile,jobofferstext
from (joboffers join studentprofile_has_joboffers on idjoboffers=joboffers_idjoboffers)
where studentprofile_idstudentprofile=studid;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectallusers
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectallusers`()
BEGIN
select * from user;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectcompanyprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectcompanyprofile`(id INT)
BEGIN
select * from companyprofile where idcompanyprofile=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectcourse
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectcourse`(id int)
BEGIN

select * from courses where idcourses=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectjoboffer
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectjoboffer`(id int)
BEGIN

select * from joboffers where idjoboffers=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectlastinsertcompanyprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectlastinsertcompanyprofile`()
BEGIN
select * from companyprofile where idcompanyprofile=last_insert_id();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectlastinsertcourse
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectlastinsertcourse`()
BEGIN

select * from courses where idcourses=last_insert_id();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectlastinsertjoboffer
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectlastinsertjoboffer`()
BEGIN

select * from joboffers where idjoboffers=last_insert_id();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectlastinsertprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectlastinsertprofile`()
BEGIN
select * from profile where idprofile=last_insert_id();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectlastinsertspecification
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectlastinsertspecification`()
BEGIN
select * from specifications where idspecifications=last_insert_id();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectlastinsertstudentprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectlastinsertstudentprofile`()
BEGIN
select * from studentprofile where idstudentprofile=last_insert_id();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectlastuserinputid
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectlastuserinputid`()
BEGIN
select * from user where iduser=last_insert_id();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectprofile`(thisid INT)
BEGIN
select * from profile where idprofile=thisid;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectprofilelastinputid
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectprofilelastinputid`()
BEGIN
select * from profile where idprofile=last_insert_id();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectspecification
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectspecification`(id INT)
BEGIN
select * from specifications where idspecifications=id;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectstudentprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectstudentprofile`(id INT)
BEGIN
select * from studentprofile where idstudentprofile=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectuser
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectuser`(thisid INT)
BEGIN
select * from user where iduser=thisid;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure selectuserbynickoremail
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectuserbynickoremail`(str varchar(45))
BEGIN
select * from `bepro`.`user` where nickname=str or email=str;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure studentssavedcourseswithimages
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `studentssavedcourseswithimages`(idspec int , idstudent int)
BEGIN
select idcourses,url,specname,specifications_idspecifications,imgurl 
from courses join studentprofile_has_courses 
on courses_idcourses=idcourses where specifications_idspecifications=idspec and studentprofile_idstudentprofile=idstudent;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updatecompanyprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatecompanyprofile`(id INT,infotxt1 longtext,imgurl1 varchar(100))
BEGIN
update companyprofile set infotxt=infotxt1,imgurl=imgurl1 where idcompanyprofile=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updatecourse
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatecourse`(id int,urlt mediumtext,specnamet varchar(45),specid int)
BEGIN

update courses set url=urlt,specname=specnamet,specifications_idspecifications=specid where idcourses=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updatejoboffer
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatejoboffer`(id int, compid int,description longtext)
BEGIN
update joboffers set companyprofile_idcompanyprofile =compid ,jobofferstext=description where idjoboffers=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updateprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateprofile`(id INT, s INT,c INT, a INT)
BEGIN
update profile set studentprofile_idstudentprofile=s,companyprofile_idcompanyprofile=c,adminprofile_idadminprofile=a where idprofile=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updatespecification
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatespecification`(id INT,namev varchar(45))
BEGIN
update specifications set namevalue=namev where idspecifications=id;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updatestudentprofile
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatestudentprofile`(id INT,name1 varchar(100) ,country1 varchar(100),tel1 varchar(100),cvurl1 varchar(100))
BEGIN
update studentprofile set username=name1,country=country1,tel=tel1,cvurl=cvurl1 where idstudentprofile=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updateuser
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateuser`( thisid INT,thisname varchar(100) ,thisemail varchar(40),thispassword varchar(25))
BEGIN
update user set email=thisemail ,nickname=thisname,password=thispassword where iduser=thisid;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updateuseremail
-- -----------------------------------------------------

DELIMITER $$
USE `bepro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateuseremail`( thisid INT,thisemail varchar(40))
BEGIN
update user set email=thisemail where iduser=thisid;
END$$

DELIMITER ;
USE `bepro`;

DELIMITER $$
USE `bepro`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `bepro`.`joboffers_AFTER_INSERT`
AFTER INSERT ON `bepro`.`joboffers`
FOR EACH ROW
BEGIN
insert into jobofferinfo(idjobofferinfo) values(new.idjoboffers);
END$$

USE `bepro`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `bepro`.`user_AFTER_INSERT`
AFTER INSERT ON `bepro`.`user`
FOR EACH ROW
BEGIN
insert into profile (idprofile) values(new.iduser);
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
