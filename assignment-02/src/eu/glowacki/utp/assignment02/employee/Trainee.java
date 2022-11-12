package eu.glowacki.utp.assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Trainee extends Employee {
    // attributes:
    // * apprenticeship start date
    // * apprenticeship length (in days)
    private final LocalDate _apprenticeshipDate;
    private int _apprenticeShip;

    public Trainee(String firstName, String lastName, LocalDate birthDate, BigDecimal salary, Manager manager, LocalDate apprenticeshipDate) {
        super(firstName, lastName, birthDate, salary, manager);
        _apprenticeshipDate = apprenticeshipDate;
    }

    @Override
    public int getEmploymentDays() {
        int apprenticeShip = (int) _apprenticeshipDate.until(LocalDate.now(), ChronoUnit.DAYS);
        return apprenticeShip;
    }

    public LocalDate getApprenticeshipDate() {
        return _apprenticeshipDate;
    }

    public boolean isShorter(int days){
        return getEmploymentDays() < days;
    }

    public boolean isLonger(int days){
        return getEmploymentDays() > days;
    }
}