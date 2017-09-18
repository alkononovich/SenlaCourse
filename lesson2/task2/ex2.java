public class ex2{
public static class Man{
    public String name;
    public String surname;
    public void setName(String newName){
        name = newName;
    }
    public String getName(){
        return name;
	}
    public void setSurname(String newSurname){
        name = newSurname;
    }
    public String getSurname(){
        return surname;
    }
}

public static class Employee extends Man{
    private String position;
    private Department department;
    private Card idCard;
    private Car car;
    public Employee(String n, String s, String p){
        name = n;
        surname = s;
        position = p;
        System.out.println("Employee");
    }
    public void setPosition(String newProfession){
        position = newProfession;
    }
    public String getPosition(){
        return position;
    }
    public void setIdCard(Card c){
        idCard = c;
    }
    public Card getIdCard(){
        return idCard;
    }
    public void setDepartment(Department d){
	department = d;
    }
    public Department getDepartment(){
	return department;
    }
    public void setCar(Car c){
	car = c;
    }
    public Car getCar(){
	return car;
    }

}
public static class Card{
    private int level;
    private int number;
    public Card(int n){
	number = n;
        System.out.println("Card");
    }
    public void setNumber(int n){
	number = n;
    }
    public int getNumber(){
	return number;
    }
    public void setLevel(int l){
	level = l;
    }
    public int getLevel(){
	return level;
    }
}

public static class Department{
    private String name;
/* тут должно быть множество, но я решил не заморачиваться, 
потому что наверно тут не это главное :) */
    private Employee[] employees = new Employee[10];
    public Department(String n){
        name = n;
	System.out.println("Department");
    }
    public void setName(String n){
        name = n;
    }
    public String getName(){
        return name;
    }
    public void addEmployee(Employee emp){
        employees[0] = emp;
        emp.setDepartment(this);
    }
    public Employee getEmployees(){
        return employees[0];
    }
    public void removeEmployee(Employee e){
        employees[0] = null;
    }
}
private static class Car{
    private String brand;
    private double volEngine;
    public Car(String b){
	brand = b;
	System.out.println("Car");
    }
    public void setBrand(String br){
	brand = br;
    }
    public String getBrand(){
	return brand;
    }
    public void setVolEngine(double vol){
	volEngine = vol;
    }
    public double getVolEngine(){
	return volEngine;
    }
}
public static void main(String[] args){
    Employee ing = new Employee("Ivan", "Ivanov", "engeneer");
    Department it = new Department("IT");
    it.addEmployee(ing);
    Card idCard1 = new Card(1);
    ing.setIdCard(idCard1);
    Car merc = new Car("Mercedess");
    ing.car = merc;
}}