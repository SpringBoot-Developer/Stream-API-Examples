package stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private int age;
    private String deptName;
    private Double salary;
    private String gender;

    public Employee(int id, String name, int age, String deptName, Double salary, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.deptName = deptName;
        this.salary = salary;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", deptName='" + deptName + '\'' +
                ", salary=" + salary +
                ", gender='" + gender + '\'' +
                '}';
    }
}


public class Main {
    public static void main(String[] args) {

        List<Employee> employeeList = Arrays.asList(
                new Employee(1, "John Doe", 30, "Engineering", 50000.00, "Male"),
                new Employee(2, "Jane Smith", 25, "Marketing", 60000.00, "Female"),
                new Employee(3, "Alice Johnson", 35, "HR", 55000.00, "Female"),
                new Employee(4, "Bob Brown", 28, "Finance", 70000.00, "Male"),
                new Employee(5, "Charlie White", 40, "Engineering", 80000.00, "Male"),
                new Employee(6, "Dana Green", 32, "Marketing", 65000.00, "Female"),
                new Employee(7, "Eve Black", 29, "HR", 72000.00, "Female"),
                new Employee(8, "Frank Blue", 38, "Finance", 75000.00, "Male")
        );

        List<Integer> numberList = Arrays.asList(25, 5, 20, 30, 10, 15);
        Integer[] numberArray = {25, 5, 20, 30, 10, 15};

        List<String> stringList = Arrays.asList("apple", "banana", "cherry", "elderberry", "fig", "grape", "date");
        List<String> stringList1 = Arrays.asList("A", "AAA", "BB", "BBBB", "AAAAA");
        String string = "scabbard";

        // Find greater than using stream
        List<Integer> filteredList = numberList.stream().filter(i -> i > 20).collect(Collectors.toList());
        System.out.println("Filtered List: " + filteredList);

        //Find even number using stream
        List<Integer> evenNumber = numberList.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println("Even Number : " + evenNumber);

        //Filtering out the elements divisible by 6.
        List<Integer> divNum = numberList.stream().filter(i -> i % 6 == 0).collect(Collectors.toList());
        System.out.println("Divisible List :" + divNum);

        //Picking the elements ending with custom alphabetically letter say it be ‘s’
        List<String> str = stringList.stream().filter(s -> s.endsWith("s")).collect(Collectors.toList());
        System.out.println("String ending with -s :" + str);

        //Emp age >= 30
        List<Employee> filteredEmp = employeeList.stream().filter(emp -> emp.getAge() >= 30).collect(Collectors.toList());
        System.out.println("Employee age >30 : " + filteredEmp);

        // Emp age >= 30 count
        long empCount = employeeList.stream().filter(emp -> emp.getAge() >= 30).count();
        System.out.println("Emp age >30 count : " + empCount);

        List<Integer> mulNumbers = numberList.stream().map(i -> i * 2).collect(Collectors.toList());
        System.out.println("Multiply by 2 : " + mulNumbers);

        // 1. Using Lambda
        List<String> empNameList1 = employeeList.stream().map(employee -> employee.getName()).collect(Collectors.toList());
        System.out.println("Emp Names : " + empNameList1);
        //2. using Method Reference
        List<String> empNameList2 = employeeList.stream().map(Employee::getName).collect(Collectors.toList());
        System.out.println("Emp Names : " + empNameList2);

        Map<String, Long> map = Arrays.stream(string.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("String char with count : " + map);

        Map<String, List<String>> employeesByDept = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDeptName,
                        Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println("Emp by dept name : " + employeesByDept);

        // Counting employees by department
        Map<String, Long> employeesCountByDept = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        System.out.println("Emp count by dept name : " + employeesCountByDept);

        //Finding the employee with the minimum age
        Optional<Employee> minAgeEmployee = employeeList.stream().min(Comparator.comparingInt(Employee::getAge));
        minAgeEmployee.ifPresent(System.out::println);

        //Finding the employee with the maximum age
        Optional<Employee> maxAgeEmployee = employeeList.stream().max(Comparator.comparingInt(Employee::getAge));
        maxAgeEmployee.ifPresent(System.out::println);

        //Calculating the total age of all employees
        int totalAge = employeeList.stream().mapToInt(Employee::getAge).sum();
        System.out.println("Total Age : " + totalAge);

        int numSum = numberList.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Num Sum : " + numSum);

        Optional<Integer> maxNum = numberList.stream().max(Comparator.comparingInt(Integer::intValue));
        maxNum.ifPresent(System.out::println);

        Optional<Integer> minNum = numberList.stream().min(Comparator.comparingInt(Integer::intValue));
        //OR
        Optional<Integer> minNum1 = numberList.stream().min(Integer::compare);
        minNum1.ifPresent(System.out::println);
        minNum.ifPresent(System.out::println);

        List<Integer> sortedNumber = numberList.stream().sorted().collect(Collectors.toList());
        System.out.println("Unsorted Num : " + numberList);
        System.out.println("Sorted Num : " + sortedNumber);

        List<Integer> reverseNum = numberList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("Reverse Num using reverse method: " + reverseNum);
        //Can be replaced with 'Comparator.reverseOrder'
        List<Integer> reverseNum1 = numberList.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
        System.out.println("Reverse Num using lambda: " + reverseNum1);

        List<String> reverseStringList = stringList.stream()
                .sorted((s1, s2) -> s2.compareTo(s1))
                .collect(Collectors.toList());
        System.out.println(reverseStringList);

        List<String> reverseStringList1 = stringList.stream()
                .sorted((s1, s2) -> -s1.compareTo(s2))
                .collect(Collectors.toList());
        System.out.println(reverseStringList1);


        List<String> strings = stringList1.stream().sorted().collect(Collectors.toList());
        System.out.println(strings);//[A, AAA, AAAAA, BB, BBBB]

        List<String> strings1 = stringList1.stream().sorted(String::compareTo).collect(Collectors.toList());
        System.out.println(strings1);//[A, AAA, AAAAA, BB, BBBB]

        List<String> strings2 = stringList1.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
        System.out.println(strings2);//[BBBB, BB, AAAAA, AAA, A]

        // List<String> strings3 = stringList1.stream().sorted((a, b) -> Integer.compare(a.length(), b.length())).collect(Collectors.toList());
        //OR
        List<String> strings3 = stringList1.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        System.out.println(strings3);//[A, BB, AAA, BBBB, AAAAA]
        List<String> strings4 = stringList1.stream().sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList());
        System.out.println(strings4);//[AAAAA, BBBB, AAA, BB, A]

        //List convert into array
        Integer[] array = numberList.toArray(new Integer[0]);
        System.out.println(Arrays.toString(array));

        List<Integer> evenNum = Arrays.stream(numberArray).filter(num -> num % 2 == 0).collect(Collectors.toList());
        System.out.println(evenNum);

        //Optional<Employee> maxSalary = employeeList.stream().max((a, b) -> Double.compare(a.getSalary(), b.getSalary()));
        //OR
        Optional<Employee> maxSalary = employeeList.stream().max(Comparator.comparingDouble(Employee::getSalary));
        maxSalary.ifPresent(System.out::println);

        // Optional<Employee> secMaxSalary = employeeList.stream().sorted((a, b) -> Double.compare(b.getSalary(), a.getSalary())).skip(1).findFirst();
        //OR
        Optional<Employee> secMaxSalary = employeeList.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(1)
                .findFirst();
        secMaxSalary.ifPresent(System.out::println);

        Map<String, Long> genderCount = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(genderCount);

        Map<String, List<Employee>> collect = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender));
        System.out.println(collect);

        //To collect male and female employee names grouped by gender,
        Map<String, List<String>> collect1 = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println(collect1);


    }
}