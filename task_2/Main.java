package streamAPI_2.task_2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> stream1 = persons.stream();
        Stream<Person> stream2 = persons.stream();
        Stream<Person> stream3 = persons.stream();

        Long count = stream1
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Колличество несовершеннолетних " + count);

        List<String> surnames = stream2
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());

        //surnames.forEach(System.out::println);

        List<Person> peoples = stream3
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> (x.getSex() == Sex.WOMAN && x.getAge() >= 18 && x.getAge() <= 60) || (x.getSex() == Sex.MAN && x.getAge() >= 18 && x.getAge() <= 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        //peoples.forEach(System.out::println);

    }
}
