package eu.glowacki.utp.assignment02.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import eu.glowacki.utp.assignment02.employee.Trainee;
import eu.glowacki.utp.assignment02.employee.Worker;
import eu.glowacki.utp.assignment02.employee.Employee;
import eu.glowacki.utp.assignment02.employee.Manager;
import org.junit.Test;

import eu.glowacki.utp.assignment02.HumanResourcesStatistics;
import eu.glowacki.utp.assignment02.payroll.PayrollEntry;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.naming.ldap.PagedResultsControl;

import static org.junit.Assert.*;

public class HumanResourcesStatisticsTest {
	private LocalDate _oldestEmplDate = LocalDate.of(2005,4,5);
	private BigDecimal _highestSalaryInitValue = new BigDecimal(9500);
	private BigDecimal _highestBonusInitValue = new BigDecimal(1050);
	private BigDecimal _bonusTotalInitValue = new BigDecimal(4650);
	private int _numOfEmpEarnsMore1000 = 12;
	private int _daysOfLongestSeniority = 6056;
	private int _numOfEmpSurnameBeginsWithA = 1;

	private int _numOfOlderThanAndEarnLess = 12;
	private int _numPracticeLengthLongerThan = 2;
	private int _numOfSeniorityLongerThan = 6;
	private int _numOfLongerThanEmployee = 3;
	private int _numOfTwoAndFour = 2;

	private Manager _manager1;
	private List<Employee> _subordinates1 = new ArrayList<>();

	private Manager _manager2;
	private List<Employee> _subordinates2 = new ArrayList<>();

	private Manager _manager3;
	private List<Employee> _subordinates3 = new ArrayList<>();

	private List<Employee> _allEmployees;

	{
		_subordinates3.add(new Worker("Alex", "Marquizy",LocalDate.of(2002,10,29),
				new BigDecimal(2000), _manager3,
				LocalDate.of(2016,6,22), new BigDecimal(500)));
		_subordinates3.add(new Worker("Vasya", "Pupkin", LocalDate.of(2003,8,15),
				new BigDecimal(1750), _manager3,
				LocalDate.of(2018,1,1), new BigDecimal(400)));
		_subordinates3.add(new Trainee("Andrew", "Super", LocalDate.of(1995,2,2),
				new BigDecimal(2500), _manager3,
				LocalDate.of(2018,2,11)));

		_subordinates2.add(new Worker("Stari", "Bog",LocalDate.of(1992,3,3),
				new BigDecimal(1950), _manager2,
				LocalDate.of(2020,1,1), new BigDecimal(100)));
		_subordinates2.add(new Worker("Ivan", "Telepka",LocalDate.of(1999,2,9),
				new BigDecimal(5000), _manager2,
				LocalDate.of(2020,5,2), new BigDecimal(900)));
		_subordinates2.add(new Trainee("Danylo", "Sifonylo", LocalDate.of(2001,5,4),
				new BigDecimal(4500), _manager2,
				LocalDate.of(2020,12,5)));
		_subordinates2.add(new Trainee("Valya", "Govrylin", LocalDate.of(2000,9,12),
				new BigDecimal(3200), _manager2,
				LocalDate.of(2020,5,15)));
		_subordinates2.add(new Manager("John", "Swin", LocalDate.of(2000,2,2),
				new BigDecimal(7600), _manager2,
				LocalDate.of(2018,8,8), new BigDecimal(600), _subordinates1));

		_subordinates1.add(new Worker("Vitalya", "Marquiz", LocalDate.of(2001,6,6),
				new BigDecimal(900), _manager1,
				LocalDate.of(2019,1,11), new BigDecimal(50)));
		_subordinates1.add(new Worker("Maks", "Afonyasov",LocalDate.of(2000,1,1),
				new BigDecimal(2222), _manager1,
				LocalDate.of(2018,1,1), new BigDecimal(300 )));
		_subordinates1.add(new Trainee("Yasha", "Lava", LocalDate.of(1991,4,4),
				new BigDecimal(3333), _manager1,
				LocalDate.of(2019,3,4)));
		_subordinates1.add(new Manager("Mathe", "Bal", LocalDate.of(1991,1,11),
				new BigDecimal(6150), _manager1,
				LocalDate.of(2021,1,3), new BigDecimal(750), _subordinates2));

		_manager1 = new Manager("Yeva", "Dushnila", LocalDate.of(1960,2,22),
				_highestSalaryInitValue,null, _oldestEmplDate,
				_highestBonusInitValue, _subordinates1);
		_manager2 = (Manager) _subordinates2.get(_subordinates2.size()-1);
		_manager3 = (Manager) _subordinates1.get(_subordinates1.size()-1);


		_allEmployees = new ArrayList<>();
		_allEmployees.add(_manager1);
		_allEmployees.addAll(_subordinates1);
		_allEmployees.addAll(_subordinates2);
		_allEmployees.addAll(_subordinates3);
	}

	//method for comparing salary with bonus and without in Payroll List and original List
	public static void assertAll(List<PayrollEntry> payrollList, List<Employee> original) {
		for (int i = 0; i < original.size(); i++) {
			Employee employee = original.get(i);
			PayrollEntry payrollEntry = payrollList.get(i);
			if (employee instanceof Worker) {
				assertEquals(employee.getSalary().add(((Worker) employee).getBonus()), payrollEntry.getSalaryPlusBonus());
			}
			else {
				assertEquals(employee.getSalary(), payrollEntry.getSalaryPlusBonus());
			}
		}
	}


	@Test
	public void allPayroll() {
		List<PayrollEntry> payrollForAll = HumanResourcesStatistics.allPayroll(_allEmployees);
		assertAll(payrollForAll, _allEmployees);
	}

	@Test
	public void managerPayroll() {
		List<PayrollEntry> payrollOfManager = HumanResourcesStatistics.managerPayroll(_manager1);
		ArrayList<Employee> managerSubordinates = new ArrayList<>(_manager1.getAllSubordinates());
		assertAll(payrollOfManager, managerSubordinates);
	}

	@Test
	public void totalCostOfBonuses() {
		BigDecimal bonusTotal = HumanResourcesStatistics.totalCostOfBonuses(_allEmployees);
		assertEquals(_bonusTotalInitValue, bonusTotal);
	}

	@Test
	public void longestSeniority() {
		Employee employee = HumanResourcesStatistics.longestSeniority(_allEmployees);
		int employeeLongestSeniority = employee.getEmploymentDays();
		assertEquals(_daysOfLongestSeniority, employeeLongestSeniority);
	}

	@Test
	public void highestSalaryWithoutBonus() {
		BigDecimal noBonusMaxSalary = HumanResourcesStatistics.highestSalaryWithoutBonus(_allEmployees);
		assertEquals(_highestSalaryInitValue, noBonusMaxSalary);
	}

	@Test
	public void highestSalaryWithBonus() {
		BigDecimal bonusMaxSalary = HumanResourcesStatistics.highestSalaryWithBonus(_allEmployees);
		assertEquals(_highestSalaryInitValue.add(_highestBonusInitValue), bonusMaxSalary);
	}

	@Test
	public void employeesSurnameBeginsWithA() {
		List<Employee> listOfEmployee = HumanResourcesStatistics.employeesSurnameBeginsWithA(_manager1);
		assertEquals(_subordinates1.get(1), listOfEmployee.get(0));
		assertEquals(_numOfEmpSurnameBeginsWithA, listOfEmployee.size());
	}

	@Test
	public void employeeEarnsMore1000() {
		List<Employee> listOfEmployee = HumanResourcesStatistics.employeeEarnsMore1000(_allEmployees);
		assertEquals(_numOfEmpEarnsMore1000, listOfEmployee.size());
		for (Employee employee : listOfEmployee)
			assertTrue(employee.getSalary().compareTo(BigDecimal.valueOf(1000)) > 0);
	}

	//assignment 03

	@Test
	public void olderThanAndEarnLess(){
		assertEquals(_numOfOlderThanAndEarnLess, HumanResourcesStatistics.olderThanAndEarnLess(_allEmployees, _allEmployees.get(0)).size());
	}

	@Test
	public void practiceLengthLongerThan(){
		BigDecimal initSalary = _allEmployees.get(3).getSalary();
		System.out.println(_allEmployees.size());
		assertEquals(_numPracticeLengthLongerThan, HumanResourcesStatistics.practiceLengthLongerThan(_allEmployees, 750).size());
		assertNotEquals(initSalary, _allEmployees.get(3).getSalary());
	}

	@Test
	public void seniorityLongerThanMonth(){
		assertEquals(_numOfSeniorityLongerThan, HumanResourcesStatistics.seniorityLongerThan(_allEmployees,30).size());
		assertNotEquals(HumanResourcesStatistics.seniorityLongerThan(_allEmployees,30).size(), HumanResourcesStatistics.seniorityLongerThan(_allEmployees, 1).size());
	}

	@Test
	public void seniorityBetweenOneAndThreeYears(){
		BigDecimal initSalary = HumanResourcesStatistics.seniorityBetweenOneAndThreeYears(_allEmployees).get(0).getSalary();
		assertEquals("Vitalya", HumanResourcesStatistics.seniorityBetweenOneAndThreeYears(_allEmployees).get(0).getFirstName());
		assertNotEquals(initSalary, HumanResourcesStatistics.seniorityBetweenOneAndThreeYears(_allEmployees).get(0).getSalary());
	}

	@Test
	public void seniorityLongerThanEmployee(){
		assertEquals(_numOfLongerThanEmployee, HumanResourcesStatistics.seniorityLongerThan(_allEmployees, _manager2).size());
	}

	@Test
	public void seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(){
		assertEquals(_numOfTwoAndFour, HumanResourcesStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(_allEmployees, 20).size());
	}


}