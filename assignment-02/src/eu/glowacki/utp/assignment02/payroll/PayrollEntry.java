package eu.glowacki.utp.assignment02.payroll;

import java.math.BigDecimal;

import eu.glowacki.utp.assignment02.employee.Employee;
import eu.glowacki.utp.assignment02.employee.Worker;

public final class PayrollEntry {

    private final Employee _employee;
    private final BigDecimal _salaryPlusBonus;

    public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
        _employee = employee;
        _salaryPlusBonus = salary.add(bonus); // validate whether salary and bonus are not null
    }

    public static PayrollEntry checkIsWorker(Employee employee) {
        PayrollEntry payrollEntry;
        if (employee instanceof Worker) {
            payrollEntry = new PayrollEntry(employee, employee.getSalary(), ((Worker) employee).getBonus());
        } else {
            payrollEntry = new PayrollEntry(employee, employee.getSalary(), BigDecimal.ZERO);
        }
        return payrollEntry;
    }

    public Employee getEmployee() {
        return _employee;
    }

    public BigDecimal getSalaryPlusBonus() {
        return _salaryPlusBonus;
    }
}