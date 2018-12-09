-- addMonthlyFinalSalary TRIGGER

DROP TRIGGER IF EXISTS addMonthlyFinalSalary;
DELIMITER $$
CREATE TRIGGER addMonthlyFinalSalary AFTER INSERT ON MonthlyWorkDetails
FOR EACH ROW
BEGIN

DECLARE epf_trig DECIMAL(10,2);
DECLARE etf_trig DECIMAL(10,2);
DECLARE allowencesRate_trig DECIMAL(10,2);
DECLARE noPay_trig DECIMAL(10,2);
DECLARE otPay_trig DECIMAL(10,2);
DECLARE salaryAdvanced_trig DECIMAL(10,2);

DECLARE netSalary_trig DECIMAL(10,2);
DECLARE basicSalary_trig DECIMAL(10,2);

SELECT getBasicSalary(NEW.desDetId) INTO basicSalary_trig;
SELECT getEPF(getBasicSalary(NEW.desDetId)) INTO epf_trig;
SELECT getETF(getBasicSalary(NEW.desDetId)) INTO etf_trig;
SELECT getTotalAllowencesRate(getBasicSalary(NEW.desDetId)) INTO allowencesRate_trig;
SELECT getOTPay(NEW.desDetId) INTO otPay_trig;
SELECT getSalaryAdvanced(NEW.desDetId) INTO salaryAdvanced_trig;
SELECT getNoPay(NEW.desDetId , getBasicSalary(NEW.desDetId) , NEW.dayMustWork) INTO noPay_trig;
 
SET netSalary_trig = (basicSalary_trig + otPay_trig + allowencesRate_trig) - ( salaryAdvanced_trig + etf_trig + noPay_trig);

INSERT INTO MonthlyFinalSalary SET mFSID = NEW.mSId , mSId = NEW.mSId , totalAllowencesRate = allowencesRate_trig , noPay = noPay_trig , otPay = otPay_trig , SalaryAdvanced = salaryAdvanced_trig , EPFPay = epf_trig , ETFPay = etf_trig , netSalary = netSalary_trig;

END$$
DELIMITER ;

-- updateEmployee TRIGGER

DROP TRIGGER IF EXISTS updateEmployeeDesignation;
DELIMITER $$
CREATE TRIGGER updateEmployeeDesignation AFTER UPDATE ON DesignationDetails
FOR EACH ROW
BEGIN

INSERT INTO BackupEmployeeDesignation SET user = user(), empId = NEW.empId , previous_desId = OLD.desId ,  current_desId = NEW.desId , dateSince = curDate();

END$$
DELIMITER ;
