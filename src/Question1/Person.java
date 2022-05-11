package Question1;

public class Person implements Comparable<Person> {
    private String idNumber;
    private String firstName;
    private String lastName;
    private int yearOfBirth;


    public Person() {

    }

    public Person(String idNumber, String firstName, String lastName, int yearOfBirth) {
        setIdNumber(idNumber);
        setFirstName(firstName);
        setLastName(lastName);
        setYearOfBirth(yearOfBirth);
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public int compareTo(Person o) {
        return idNumber.compareTo(o.idNumber);
    }

    @Override
    public String toString() {
        return '\n' + firstName + " " + lastName + ", born in " + yearOfBirth + ", id number: " + idNumber;
    }
}
