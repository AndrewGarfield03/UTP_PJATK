package eu.glowacki.utp.assignment02;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import eu.glowacki.utp.assignment02.employee.*;
import eu.glowacki.utp.assignment02.payroll.PayrollEntry;

public final class HumanResourcesStatistics {

    //assignment 2

    public static List<PayrollEntry> allPayroll(List<Employee> employees) {
        List<PayrollEntry> payrollList = employees.stream()
                .map(PayrollEntry::checkIsWorker)
                .collect(Collectors.toList());
        return payrollList;
    }

    public static List<PayrollEntry> managerPayroll(Manager manager) {
        List<PayrollEntry> payrollManager = allPayroll(manager.getAllSubordinates());
        return payrollManager;
    }

    public static BigDecimal totalCostOfBonuses(List<Employee> employees) {
        BigDecimal totalCost = employees.stream()
                .filter(worker -> worker instanceof Worker)
                .map(worker -> (Worker) worker)
                .map(Worker::getBonus)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalCost;
    }

    public static Employee longestSeniority(List<Employee> employees) {
        Optional<Employee> result = employees.stream()
                .max(Comparator.comparing(Employee::getEmploymentDays));
        return result.orElse(null);
    }

    public static BigDecimal highestSalaryWithoutBonus(List<Employee> employees) {
        BigDecimal maxSalary;
        Optional<Employee> result = employees.stream()
                .max(Comparator.comparing(Employee::getSalary));
        if (result.isPresent()) {
            maxSalary = result.get().getSalary();
        } else {
            maxSalary = BigDecimal.ZERO;
        }
        return maxSalary;
    }

    public static BigDecimal highestSalaryWithBonus(List<Employee> employees) {
        BigDecimal maxSalary;
        Optional<PayrollEntry> result = allPayroll(employees).stream()
                .max(Comparator.comparing(PayrollEntry::getSalaryPlusBonus));
        if (result.isPresent()) {
            maxSalary = result.get().getSalaryPlusBonus();
        } else {
            maxSalary = BigDecimal.ZERO;
        }
        return maxSalary;
    }

    public static List<Employee> employeesSurnameBeginsWithA(Manager manager) {
        List<Employee> employeeList = manager.getSubordinates();
        List<Employee> employeesWithA = employeeList.stream()
                .filter(employee -> employee.getLastName().startsWith("A"))
                .collect(Collectors.toList());
        return employeesWithA;
    }

    public static List<Employee> employeeEarnsMore1000(List<Employee> employees) {
        List<Employee> employeeList = allPayroll(employees).stream()
                .filter(payroll -> {
                    BigDecimal salary = payroll.getSalaryPlusBonus();
                    return salary.compareTo(BigDecimal.valueOf(1000)) >= 0;
                })
                .map(PayrollEntry::getEmployee)
                .collect(Collectors.toList());
        return employeeList;
    }

    //assignment 3
    public static List<Employee> olderThanAndEarnLess(List<Employee> allEmployees, Employee employee) {
        Predicate<Employee> filter = emp -> emp.isOlder(employee) && emp.isLess(employee);
        List<Employee> resultList = allEmployees.stream()
                .filter(filter)
                .collect(Collectors.toList());
        return resultList;
    }

    public static List<Trainee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount) {
        Predicate<Employee> filter = emp -> emp instanceof Trainee && ((Trainee) emp).isLonger(daysCount);
        List<Trainee> resultList = allEmployees.stream()
                .filter(filter)
                .map(e -> {
                    e.setSalary(e.getSalary().multiply(new BigDecimal("1.05")));
                    return (Trainee) e;
                })
                .collect(Collectors.toList());
        return resultList;
    }

    public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, int monthCount) {
        Predicate<Employee> filter = emp -> emp instanceof Worker && ((Worker) emp).seniorityLongerMonths(monthCount);
        List<Worker> resultList = allEmployees.stream()
                .filter(filter)
                .map(e -> {
                    ((Worker) e).giveBonus300();
                    return (Worker) e;
                }).collect(Collectors.toList());
        return resultList;
    }

    public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
        Predicate<Employee> filter = emp -> emp instanceof Worker && ((Worker) emp).seniorityLongerYears(1) && !((Worker) emp).seniorityLongerYears(3);
        List<Worker> resultList = allEmployees.stream()
                .filter(filter)
                .map(e -> {
                    e.setSalary(e.getSalary().multiply(new BigDecimal("1.1")));
                    return (Worker) e;
                }).collect(Collectors.toList());
        return resultList;
    }

    public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, Employee employee) {
        Predicate<Employee> filter = emp -> emp instanceof Worker && ((Worker) emp).seniorityLongerDays(employee.getEmploymentDays()) && emp.isLess(employee);
        List<Worker> resultList = allEmployees.stream()
                .filter(filter)
                .map(e -> {
                    e.setSalary(employee.getSalary());
                    return (Worker) e;
                })
                .collect(Collectors.toList());
        return resultList;
    }

    public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
        Predicate<Employee> filter = emp -> emp instanceof Worker && ((Worker) emp).seniorityLongerYears(2) && !((Worker) emp).seniorityLongerYears(4) && emp.getAge() > age;
        List<Worker> resultList = allEmployees.stream()
                .filter(filter)
                .map(e -> (Worker) e)
                .collect(Collectors.toList());
        return resultList;
    }


    public static List<Integer> getAges(List<Employee> employees) {
        if (employees == null) {
            return null;
        }
        List<Integer> ages = employees //
                .stream() //
                .map(Person::getAge) //
                .collect(Collectors.toList());
        return ages;
    }

    public static void printAges(List<Employee> employees) {
        if (employees == null) {
            return;
        }
        employees //
                .stream() //
                .map(emp -> (int) emp.getAge()) //
                .forEach(age -> System.out.print(age + ", "));
    }

    public static short getAverageAgeInline(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }
        int employeeTotalAge = employees //
                .stream() //
                .filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
                .map(emp -> (int) emp.getAge()) //
                .reduce(0, //
                        (total, age) -> total + age);

        long filteredEmployeesCount = employees //
                .stream() //
                .filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
                .count();

        return (short) (employeeTotalAge / filteredEmployeesCount);
    }

    public static short getAverageAgeMethodReference(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }
        int employeeTotalAge = employees //
                .stream() //
                .map(emp -> (int) emp.getAge()) //
                .reduce(0, HumanResourcesStatistics::totalAge);
        return (short) (employeeTotalAge / employees.size());
    }

    public static Integer getMaxAgeInline(List<Employee> employees) {
        int employeeMaxAge = employees //
                .stream() //
                .map(emp -> emp.getAge()) //
                .reduce(0, //
                        (maxAge, age) -> {
                            if (maxAge < age) {
                                return age;
                            } else {
                                return maxAge;
                            }
                        });
        return employeeMaxAge;
    }

    public static int getMaxAgeMethodReference(List<Employee> employees) {
        int employeeMaxAge = employees //
                .stream() //
                .map(emp -> emp.getAge()) //
                .reduce((int) 0, HumanResourcesStatistics::maxAge);
        return employeeMaxAge;
    }

    private static int totalAge(int totalAge, int age) {
        return totalAge + age;
    }

    private static int maxAge(int maxAge, int age) {
        if (maxAge < age) {
            return age;
        } else {
            return maxAge;
        }
    }
}