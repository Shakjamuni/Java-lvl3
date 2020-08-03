package misc;

import java.util.*;
import java.util.stream.Collectors;

public class Persons {
    String name;
    String position;
    int age;

    public Persons(String name, String position, int age) {
        this.name = name;
        this.position = position;
        this.age = age;
    }

    public static void main(String[] args) {
        List<Persons> all = new ArrayList<>(Arrays.asList(
                new Persons("Bob1", "engineer", 45),
                new Persons("Bob2", "manager", 33),
                new Persons("Bob3", "engineer", 55),
                new Persons("Bob4", "clearer", 41),
                new Persons("Bob5", "engineer", 65),
                new Persons("Bob6", "worker", 21),
                new Persons("Bob7", "engineer", 15)
        ));

        List<Persons> engies = new ArrayList<>();
        for (Persons p : all) {
            if (p.position.equals("engineer")) {
                engies.add(p);
            }
        }
        Collections.sort(engies, new Comparator<Persons>() {
            @Override
            public int compare(Persons o1, Persons o2) {
                return o1.age - o2.age;
            }
        });
        List<String> names = new ArrayList<>();
        for (Persons p : engies) {
            names.add(p.name);
        }
        System.out.println(names);
        List<String> nms = all.stream()
                .filter(p -> p.position.equals("engineer")).sorted((o1,o2) -> o1.age - o2.age)
                .map(p -> p.name)
                .collect(Collectors.toList());
        System.out.println(nms);
    }
}
