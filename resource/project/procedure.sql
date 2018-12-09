-- Add designation PROCEDURE

DROP PROCEDURE IF EXISTS addDesignation;
DELIMITER $$;
CREATE PROCEDURE addDesignation(IN dDescription VARCHAR(30),IN dBasicSalary DECIMAL(10,2))
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

INSERT INTO Designation(desDescription ,desBasicSalary) VALUES(dDescription ,dBasicSalary);

END$$;
DELIMITER ;

-- call addDesignation('Manager' , 20000.00);

-- View designation PROCEDURE

DROP PROCEDURE IF EXISTS viewDesignation;
DELIMITER $$;
CREATE PROCEDURE viewDesignation()
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT * FROM Designation;

END$$;
DELIMITER ;

-- Search designation PROCEDURE

DROP PROCEDURE IF EXISTS searchDesignation;
DELIMITER $$;
CREATE PROCEDURE searchDesignation(IN dDescription VARCHAR(20))
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT * FROM Designation WHERE desDescription = dDescription;

END$$;
DELIMITER ;

-- updateDesignation PROCEDURE

DROP PROCEDURE IF EXISTS updateDesignation;
DELIMITER $$;
CREATE PROCEDURE updateDesignation(IN dDescription VARCHAR(30),IN dBasicSalary DECIMAL(10,2))
BEGIN

DECLARE nowDate DATE DEFAULT now();

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

UPDATE Designation SET desDescription = dDescription , desBasicSalary = dBasicSalary WHERE desDescription = dDescription;

END$$;
DELIMITER ;

-- Add employee PROCEDURE

DROP PROCEDURE IF EXISTS addEmployee;
DELIMITER $$;
CREATE PROCEDURE addEmployee(IN eId INT , IN eName VARCHAR(100) , IN eNIC VARCHAR(20) , IN eAddress VARCHAR(100) , IN dId INT)
BEGIN

DECLARE nowDate DATE DEFAULT now();

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

INSERT INTO Employee( empName , empNIC , empAddress ) VALUES ( eName , eNIC , eAddress );
call addDesignationDetails(eId,dId);

END$$;
DELIMITER ;

-- call addEmployee(1 , 'Dinith sadeepa' , '983331604v' , 'Galle' , 1);

-- addDesignationDetails PROCEDURE

DROP PROCEDURE IF EXISTS addDesignationDetails;
DELIMITER $$;
CREATE PROCEDURE addDesignationDetails(IN eId INT , IN dId INT )
BEGIN

DECLARE nowDate DATE DEFAULT now();

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

INSERT INTO DesignationDetails( empId , desId , dateSince ) VALUES ( eId , dId , nowDate);

END$$;
DELIMITER ;

----------------------

DROP PROCEDURE IF EXISTS updateEmployee;
DELIMITER $$;
CREATE PROCEDURE updateEmployee(IN eId INT , IN eName VARCHAR(100) , IN eNIC VARCHAR(20) , IN eAddress VARCHAR(100) , IN dId INT)
BEGIN

DECLARE nowDate DATE DEFAULT now();

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

UPDATE Employee SET empName = eName , empNIC = eNIC , empAddress = eAddress WHERE empId = eId;

UPDATE DesignationDetails SET empId = eId , desId = dId , dateSince = curDate() WHERE empId = eId;

END$$;
DELIMITER ;

-- call updateEmployee(1 , 'Dinith sadeepa' , '983331604v' , 'Galle' , 2);

-- View employee PROCEDURE

DROP PROCEDURE IF EXISTS getAllEmployee;
DELIMITER $$;
CREATE PROCEDURE getAllEmployee()
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT e.empId , e.empName , e.empNIC , e.empAddress , d.desDescription , d.desBasicSalary FROM Employee e, Designation d , DesignationDetails dd WHERE e.empId = dd.empId AND dd.desID = d.desID;

END$$;
DELIMITER ;


DROP PROCEDURE IF EXISTS getAllEmployeeDetails;
DELIMITER $$;
CREATE PROCEDURE getAllEmployeeDetails()
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT e.empId ,
  e.empName ,
  e.empNIC , 
  e.empAddress , 
  d.desDescription , 
  dd.dateSince , 
  d.desBasicSalary 
  FROM Employee e , DesignationDetails dd , Designation d WHERE e.empId = dd.empId AND dd.desId = d.desId ;

END$$;
DELIMITER ;

-- searchEmployee PROCEDURE

DROP PROCEDURE IF EXISTS searchEmployee;
DELIMITER $$;
CREATE PROCEDURE searchEmployee(IN eId INT)
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT * FROM Employee WHERE empId = eId ;

END$$;
DELIMITER ;

-- searchEmployee PROCEDURE

DROP PROCEDURE IF EXISTS searchEmployeeByName;
DELIMITER $$;
CREATE PROCEDURE searchEmployeeByName(IN eName VARCHAR(100))
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT * FROM Employee WHERE empName = eName ;

END$$;
DELIMITER ;

-- getEmployeeDesignation

DROP PROCEDURE IF EXISTS getEmployeeDesignation;
DELIMITER $$;
CREATE PROCEDURE getEmployeeDesignation(IN eId INT)
BEGIN

DECLARE desigId INT;

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT desId INTO desigId FROM DesignationDetails WHERE empId = eid;

SELECT * FROM Designation WHERE desId = desigId ;

END$$;
DELIMITER ;

-- Add Allowences PROCEDURE

DROP PROCEDURE IF EXISTS addAllowences;
DELIMITER $$;
CREATE PROCEDURE addAllowences(IN aDescription VARCHAR(30),IN aRate int)
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

INSERT INTO Allowences(allowenceDescription ,rate) VALUES(aDescription ,aRate);

END$$;
DELIMITER ;

-- updateAllowences PROCEDURE

DROP PROCEDURE IF EXISTS updateAllowences;
DELIMITER $$;
CREATE PROCEDURE updateAllowences(IN aDescription VARCHAR(30),IN aRate int)
BEGIN

DECLARE nowDate DATE DEFAULT now();

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

UPDATE Allowences SET allowenceDescription = aDescription , rate = aRate WHERE allowenceDescription = aDescription;

END$$;
DELIMITER ;

-- View Allowences PROCEDURE

DROP PROCEDURE IF EXISTS viewAllowences;
DELIMITER $$;
CREATE PROCEDURE viewAllowences()
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT * FROM Allowences;

END$$;
DELIMITER ;

-- add config PROCEDURE

DROP PROCEDURE IF EXISTS addConfig;
DELIMITER $$;
CREATE PROCEDURE addConfig(IN cDescription VARCHAR(30),IN cRate int)
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

INSERT INTO ConfigTable(configDescription ,configRate) VALUES(cDescription ,cRate);

END$$;
DELIMITER ;

-- view config PROCEDURE

DROP PROCEDURE IF EXISTS viewConfig;
DELIMITER $$;
CREATE PROCEDURE viewConfig()
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT * FROM ConfigTable;

END$$;
DELIMITER ;

-- updateConfig PROCEDURE

DROP PROCEDURE IF EXISTS updateConfig;
DELIMITER $$;
CREATE PROCEDURE updateConfig(IN cDescription VARCHAR(30),IN cRate int)
BEGIN

DECLARE nowDate DATE DEFAULT now();

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

UPDATE ConfigTable SET  configDescription = cDescription , configRate = cRate WHERE configDescription = cDescription;

END$$;
DELIMITER ;

-- Add SalaryAdvanced PROCEDURE

DROP PROCEDURE IF EXISTS addSalaryAdvanced;
DELIMITER $$;
CREATE PROCEDURE addSalaryAdvanced(IN ddId INT , IN aAmount DECIMAL(10,2))
BEGIN

DECLARE bSalary DECIMAL(10,2);
DECLARE designationId INT;

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT desId INTO designationId FROM DesignationDetails WHERE desDetID = ddId; 

SELECT desBasicSalary INTO bSalary FROM Designation WHERE desId = designationId;

IF (aAmount < (bSalary/2)) THEN
	INSERT INTO SalaryAdvanced( desDetID , getDate , amount) VALUES ( ddId , curDate() , aAmount);
ELSE 
	SELECT 'Advanced Must be less than BasicSalary';
END IF;

END$$;
DELIMITER ;


-- searchDesignationDetails PROCEDURE

DROP PROCEDURE IF EXISTS searchDesignationDetailsByEmp;
DELIMITER $$;
CREATE PROCEDURE searchDesignationDetailsByEmp(IN eId INT)
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT * FROM DesignationDetails WHERE empId = eId ;

END$$;
DELIMITER ;

DROP PROCEDURE IF EXISTS searchDesignationDetails;
DELIMITER $$;
CREATE PROCEDURE searchDesignationDetails(IN desdetailId INT)
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT * FROM DesignationDetails WHERE desDetID = desDetailId ;

END$$;
DELIMITER ;

-- addAttendance PROCEDURE

DROP PROCEDURE IF EXISTS addAttendanceIN;
DELIMITER $$;
CREATE PROCEDURE addAttendanceIN(IN desDetailId INT , IN aStatus VARCHAR(100))
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

IF (aStatus = 'present') THEN

	INSERT INTO Attendance(desDetID , attDate , status , inTime , outTime , otHours) VALUES (desDetailId , curDate() , aStatus , curTime() , '00:00:00', '00:00:00' );

ELSE

	INSERT INTO Attendance(desDetID , attDate , status , inTime , outTime , otHours) VALUES (desDetailId , curDate() , aStatus , '00:00:00' , '00:00:00' , '00:00:00' );

END IF;

END$$;
DELIMITER ;

DROP PROCEDURE IF EXISTS addAttendanceOUT;
DELIMITER $$;
CREATE PROCEDURE addAttendanceOUT(IN desDetailId INT)
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

UPDATE Attendance SET outTime = curTime() WHERE attDate = curDate() AND desDetID = desDetailId;

IF (curTime() > '17:00:00') THEN

	UPDATE Attendance SET otHours = TIMEDIFF(curTime(),'17:00:00') WHERE desDetID = desDetailId AND attDate = curDate();
END IF;

END$$;
DELIMITER ;

-- searchAttendance PROCEDURE

DROP PROCEDURE IF EXISTS searchAttendance;
DELIMITER $$;
CREATE PROCEDURE searchAttendance(IN desDetailId INT)
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT * FROM Attendance WHERE desDetID = desDetailId AND attDate = curDate();

END$$;
DELIMITER ;

-- getAllAttendance PROCEDURE

DROP PROCEDURE IF EXISTS getAllAttendance;
DELIMITER $$;
CREATE PROCEDURE getAllAttendance()
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT * FROM Attendance ;

END$$;
DELIMITER ;


-- addMonthlyWorkedDetails PROCEDURE

DROP PROCEDURE IF EXISTS addMonthlyWorkedDetails;
DELIMITER $$;
CREATE PROCEDURE addMonthlyWorkedDetails(IN desDetailId INT,IN dayMustWo INT)
BEGIN

DECLARE calOTHours INT;
DECLARE salMonth VARCHAR(100);
DECLARE workedDay INT;

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT HOUR(otHours) INTO calOTHours FROM Attendance WHERE desDetID = desDetailId;
SET salMonth = MONTHNAME(curdate());

SELECT COUNT(desDetID) INTO workedDay FROM Attendance WHERE desDetID = desDetailId AND MONTHNAME(attDate) = MONTHNAME(curDate()) AND status = 'present';

INSERT INTO MonthlyWorkDetails(desDetID , getDate , toMonth , dayMustWork , workedDays , totalOTHours) VALUES (desDetailId , curDate() , salMonth , dayMustWo , workedDay , calOTHours);

END$$;
DELIMITER ;

-- call addMonthlyWorkedDetails(1,1);

-- searchMonthlyFinalSalary PROCEDURE

DROP PROCEDURE IF EXISTS searchMonthlyFinalSalary;
DELIMITER $$;
CREATE PROCEDURE searchMonthlyFinalSalary(IN desDetailId INT)
BEGIN
DECLARE monsId INT;

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT mSID INTO monsId FROM MonthlyWorkDetails WHERE desDetID = desDetailId;

SELECT * FROM MonthlyFinalSalary WHERE mSID = monsId;

END$$;
DELIMITER ;

-- searchMonthlyWorkDetails PROCEDURE

DROP PROCEDURE IF EXISTS searchMonthlyWorkDetails;
DELIMITER $$;
CREATE PROCEDURE searchMonthlyWorkDetails(IN desDetailId INT)
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT * FROM MonthlyWorkDetails WHERE desDetID = desDetailId;

END$$;
DELIMITER ;

-- getAllMonthlyFinalSalary PROCEDURE

DROP PROCEDURE IF EXISTS getAllMonthlyFinalSalary;
DELIMITER $$;
CREATE PROCEDURE getAllMonthlyFinalSalary()
BEGIN

DECLARE EXIT HANDLER FOR 1062
SELECT 'Duplicate key error encountered';

DECLARE EXIT HANDLER FOR SQLEXCEPTION
SELECT 'SQLEXCEPTION encountered';

SELECT * FROM MonthlyFinalSalary;

END$$;
DELIMITER ;