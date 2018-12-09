-- getBasicSalary FUNCTION

DROP FUNCTION IF EXISTS getBasicSalary;
DELIMITER $$
CREATE FUNCTION getBasicSalary(desDetailId INT) RETURNS DECIMAL(10,2)
BEGIN
DECLARE designationId INT;
DECLARE basicSal DECIMAL(10,2);

SELECT desId INTO designationId FROM DesignationDetails WHERE desDetId = desDetailId;

SELECT desBasicSalary INTO basicSal FROM Designation WHERE desId = designationId;

RETURN basicSal;
END$$
DELIMITER ;


-- getEPF FUNCTION

DROP FUNCTION IF EXISTS getEPF;
DELIMITER $$
CREATE FUNCTION getEPF(basicSal DECIMAL(10,2)) RETURNS DECIMAL(10,2)
BEGIN
DECLARE epfRate INT;
DECLARE epfAmount DECIMAL(10,2);

SELECT configRate INTO epfRate FROM ConfigTable WHERE configDescription = 'EPF';

SET epfAmount = (basicSal/100) * epfRate;

RETURN epfAmount;
END$$
DELIMITER ;


-- getETF FUNCTION

DROP FUNCTION IF EXISTS getETF;
DELIMITER $$
CREATE FUNCTION getETF(basicSal DECIMAL(10,2)) RETURNS DECIMAL(10,2)
BEGIN
DECLARE etfRate INT;
DECLARE etfAmount DECIMAL(10,2);

SELECT configRate INTO etfRate FROM ConfigTable WHERE configDescription = 'ETF';

SET etfAmount = (basicSal/100) * etfRate;

RETURN etfAmount;
END$$
DELIMITER ;

-- getTotalAllowencesRate FUNCTION

DROP FUNCTION IF EXISTS getTotalAllowencesRate;
DELIMITER $$
CREATE FUNCTION getTotalAllowencesRate(basicSal DECIMAL(10,2)) RETURNS DECIMAL(10,2)
BEGIN
DECLARE totalRate INT;
DECLARE allowencesPay DECIMAL(10,2);

SELECT SUM(rate) INTO totalRate FROM Allowences;

SET allowencesPay = (basicSal/100) * totalRate;

RETURN allowencesPay;
END$$
DELIMITER ;

-- getOTPay FUNCTION

DROP FUNCTION IF EXISTS getOTPay;
DELIMITER $$
CREATE FUNCTION getOTPay(desDetailId INT) RETURNS DECIMAL(10,2)
BEGIN
DECLARE totalOTHours INT;
DECLARE totalOTPay DECIMAL(10,2);

SELECT SUM(HOUR(otHours)) INTO totalOTHours FROM Attendance WHERE desDetId = desDetailId;

SET totalOTPay = totalOTHours * (SELECT configRate FROM ConfigTable WHERE configDescription = 'OTAmount');

RETURN totalOTPay;
END$$
DELIMITER ;

-- getSalaryAdvanced FUNCTION

DROP FUNCTION IF EXISTS getSalaryAdvanced;
DELIMITER $$
CREATE FUNCTION getSalaryAdvanced(desDetailId INT) RETURNS DECIMAL(10,2)
BEGIN
DECLARE salaryAdvancedAmount DECIMAL(10,2);

SELECT SUM(amount) INTO salaryAdvancedAmount FROM SalaryAdvanced WHERE desDetId = desDetailId AND MONTHNAME(getDate) = MONTHNAME(curDate());

RETURN salaryAdvancedAmount;
END$$
DELIMITER ;

-- getNoPay FUNCTION

DROP FUNCTION IF EXISTS getNoPay;
DELIMITER $$
CREATE FUNCTION getNoPay(desDetailId INT , basicSal DECIMAL(10,2) , dayMustWork INT) RETURNS DECIMAL(10,2)
BEGIN
DECLARE noPayAmount DECIMAL(10,2);
DECLARE workDaysCount INT;

SELECT COUNT(desDetID) INTO workDaysCount FROM Attendance WHERE desDetID = desDetailId AND MONTHNAME(attDate) = MONTHNAME(curDate());

IF (dayMustWork > workDaysCount) THEN
	SET noPayAmount = basicSal/workDaysCount;
ELSE
	SET noPayAmount = 0;
END IF;

RETURN noPayAmount;
END$$
DELIMITER ;
