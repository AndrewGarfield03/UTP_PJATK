package eu.glowacki.utp.assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Employee extends Person {
	// attributes:
	// * salary (use BigDecimal type for representing currency values)
	// * manager (empty if at top of hierarchy)
	private BigDecimal _salary;
	private final Manager _manager;

	protected Employee(String firstName, String lastName, LocalDate birthDate, BigDecimal salary, Manager manager) {
		super(firstName, lastName, birthDate);
		_salary = salary;
		_manager = manager;
	}

	public void setSalary(BigDecimal salary) {
		_salary = salary;
	}

	public BigDecimal getSalary() {
		return _salary;
	}

	public Manager getManager() {
		return _manager;
	}

	public abstract int getEmploymentDays();

	private int comapreSal(Employee emp) {
		return _salary.compareTo(emp._salary);
	}

	public boolean isGreater(Employee emp) {
		return comapreSal(emp) > 0;
	}

	public boolean isLess(Employee emp) {
		return comapreSal(emp) < 0;
	}
}