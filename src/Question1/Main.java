package Question1;

import java.util.Scanner;

public class Main {

    public static void fillSet(Set<Integer> set) {
        for (int i = 0; i < 10; i++) {
            set.insert((int) (Math.random() * 100));
        }
    }

    public static void main(String[] args) {
        question1SectionAAndB();
        question1SectionC();
    }

    private static void question1SectionAAndB() {
        Set<Integer> set1 = new Set<>();
        Set<Integer> set2 = new Set<>();
        Set<Integer> set3 = new Set<>();

        fillSet(set1);
        fillSet(set2);
        fillSet(set3);

        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);
        System.out.println("Set 3: " + set3);

        set1.union(set2);
        System.out.println("Set 1 after a union with Set 2: " + set1);

        set1.intersect(set3);
        System.out.println("Set 1 after an intersection with Set 3: " + set1);

        System.out.print("Insert a number: ");
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Insert a number: ");
        int num2 = scanner.nextInt();
        scanner.nextLine();

        Set<Integer> set4 = new Set<>(new Integer[]{num1, num2});
        System.out.println("Your set: " + set4);
        System.out.println("is" + (set1.isSubset(set4) ? "" : "n't") + " a subset of: " + set1);

        System.out.print("Now Enter a final number: ");
        int num3 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Your number is" + (set1.isMember(num3) ? "" : "n't") + " a member of: " + set1);

        set2.insert(num3);
        System.out.println("Set 2 with your number: " + set2);

        set3.delete(num3);
        System.out.println("Set 3 with your number: " + set3);
    }

    private static void question1SectionC() {
        Person person1 = new Person("123456789", "first1", "last1", 1991);
        Person person2 = new Person("111111111", "first2", "last2", 1992);
        Person person3 = new Person("123456689", "first3", "last3", 1993);
        Person person4 = new Person("111111112", "first4", "last4", 1994);
        Person person5 = new Person("111111114", "first5", "last5", 1995);
        Set<Person> personSet = new Set<>(new Person[]{person1, person2, person3, person4, person5});
        System.out.println(personSet);

        MinValue<Person> minValue= new MinValue<>();
        Person min= minValue.getMinValue(personSet);
        System.out.println("Min value is: "+min);
    }
}
