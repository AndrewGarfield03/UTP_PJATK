package eu.glowacki.utp.assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Manager extends Worker {
    // attributes
    // * subordinates (a list of immediate subordinates)
    // * all subordinates (derived --- i.e. calculated on the fly --- a list of subordinates in all hierarchy)
    private final List<Employee> _subordinates;

    public Manager(String firstName, String lastName, LocalDate birthDate, BigDecimal salary, Manager manager, LocalDate employmentDate, BigDecimal bonus, List<Employee> subordinates) {
        super(firstName, lastName, birthDate, salary, manager, employmentDate, bonus);
        _subordinates = subordinates;
    }

    public List<Employee> getSubordinates() {
        return _subordinates;
    }

    public List<Employee> getAllSubordinates() {
        ArrayList allSubordinates = new ArrayList<>(_subordinates);
        _subordinates.stream()
                .filter(manager -> manager.getClass() == Manager.class)
                .map(manager -> (Manager) manager)
                .map(Manager::getSubordinates)
                .forEach(allSubordinates::addAll);
        return allSubordinates;
    }

}