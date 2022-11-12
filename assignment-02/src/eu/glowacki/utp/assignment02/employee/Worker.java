package eu.glowacki.utp.assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Worker extends Employee {

    // attributes
    // * employment date
    // * bonus
    private final LocalDate _employmentDate;
    private BigDecimal _bonus;

    public Worker(String firstName, String lastName, LocalDate birthDate, BigDecimal salary, Manager manager, LocalDate employmentDate, BigDecimal bonus) {
        super(firstName, lastName, birthDate, salary, manager);
        _employmentDate = employmentDate;
        _bonus = bonus;
    }

    @Override
    public int getEmploymentDays() {
        int employmentDays = (int) _employmentDate.until(LocalDate.now(), ChronoUnit.DAYS);
        return employmentDays;
    }

    public void setBonus(BigDecimal bonus) {
        _bonus = bonus;
    }

    public BigDecimal getBonus() {
        return _bonus;
    }

    public LocalDate getEmploymentDate() {
        return _employmentDate;
    }

    public boolean seniorityLongerDays(int days) {
        return (int) _employmentDate.until(LocalDate.now(), ChronoUnit.DAYS) > days;
    }

    public boolean seniorityLongerYears(int year) {
        return (int) _employmentDate.until(LocalDate.now(), ChronoUnit.YEARS) > year;
    }

    public boolean seniorityLongerMonths(int month) {
        return (int) _employmentDate.until(LocalDate.now(), ChronoUnit.MONTHS) > month;
    }

    public boolean greaterBonus(BigDecimal x) {
        return _bonus.compareTo(x) > 0;
    }

    public void giveBonus300() {
        if (!greaterBonus(new BigDecimal(300))) {
            _bonus = _bonus.add(new BigDecimal(300));
        }
    }
}