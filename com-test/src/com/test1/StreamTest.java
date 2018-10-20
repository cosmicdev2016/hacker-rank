package com.test1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by Gaurav Saini on 1/21/2018.
 */
public class StreamTest {

    public static void main(String[] args) {
        Stream<String> stream1 = Arrays.stream(new String[]{"a", "b"});

        Stream<Character> stream2 = Stream.of(new Character[]{'a', 'b'});

        System.out.println(stream1.distinct().count());

        boolean contains = stream2.anyMatch(element -> (element.equals('b')));
        System.out.println(contains);

        List<String> list = new ArrayList<>();
        list.add("delhi");
        list.add("pune");
        list.add("mumbai");
        list.add("pune");
        list.add("pune1");
        list.add("mumbai2");
        list.add("mumbai");

        Stream<String> stream3 = list.stream().filter(element -> (element.contains("m")));

        String str = list.stream().reduce("Hi ! ", (e1, e2) -> e1.concat(",").concat(e2));
        System.out.println(str);

        List<String> upperCase = list.stream().map(element -> element.toUpperCase()).collect(toList());
        System.out.println(upperCase);

        List<Integer> listInt = Stream.iterate(1, e -> e + 1).limit(10).collect(toList());
        System.out.println(listInt);

        List<Map<String, Integer>> list2 = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        map.put("foo", 1);
        map.put("bar", 2);
        list2.add(map);
        map = new HashMap<>();
        map.put("foo", 3);
        map.put("bar", 4);
        list2.add(map);

        Map<String, Integer> map2 = list2.stream().
                flatMap(e -> e.entrySet().stream()).
                filter(e -> !e.getKey().equalsIgnoreCase("foo")).
                filter(e -> !e.getKey().equalsIgnoreCase("bar")).
                collect(groupingBy(Map.Entry::getKey, summingInt(Map.Entry::getValue)));
        System.out.println(map2);


        List<Map<String, Integer>> list3 = new ArrayList<>();
        Map<String, Integer> map3 = new HashMap<>();
        map3.put("foo", 1);
        map3.put("bar", 2);
        list3.add(map3);
        map3 = new HashMap<>();
        map3.put("foo", 3);
        map3.put("bar", 4);
        list3.add(map3);

        //change the map with values multiply by 2; null values give NPE
        List<Map<String, Integer>> list4 = list3.stream()
                .map(hashmap -> {
                    Map<String, Integer> newMap = hashmap.entrySet().stream()
                            .collect(toMap(Map.Entry::getKey, e -> {
                                if (e.getKey().equals("bar")) {
                                    return e.getValue() * 2;
                                }
                                return e.getValue();
                            }));
                    return newMap;
                })
                .collect(toList());
        System.out.println(list4);

        List<Map<String, Integer>> list5 = new ArrayList<>();
        map3 = new HashMap<>();
        map3.put("foo", 1);
        map3.put("bar", 2);
        map3.put("zzz", 6);
        list5.add(map3);
        map3 = new HashMap<>();
        map3.put("foo", 3);
        map3.put("bar", 4);
        map3.put("zzz", null);
        list5.add(map3);

        //handle null values in MAP
        //Fix: toMap() fails to handle if map has null values.
        //Reason: HashMap.merge() has a check for null values and throws NPE.
        //Solution: Found below approach without toMap() to fix it.
        //collect(HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue()), HashMap::putAll)
        List<Map<String, Integer>> list6 = processMapData(list5, accumulator());
        System.out.println(list6);

        //another way of doing same thing using lambdas --> NOT efficient, 2x operations
        /*List<Map<String, Integer>> newList = new ArrayList<>(list5);
        newList.replaceAll(eachMap -> {
            Map<String, Integer> newMap = new HashMap<>(eachMap);
            newMap.computeIfPresent("bar", (k,v) -> v * 2);
            return newMap;
        });
        System.out.println(newList);*/

        Map<String, Object> testMap = list3.stream().
                flatMap(mapElement -> mapElement.entrySet().stream()).
                filter(entry -> !entry.getKey().equals("payer")).
                collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (d, e) -> {
                    if (d instanceof Long) {
                        return (Long) d + (Long) e;
                    } else {
                        return (Integer) d + (Integer) e;
                    }

                }));

        System.out.println(testMap);


        //persons
        List<Person> persons = createPeople();

        List<String> names = persons.stream()
                .filter(person -> person.getAge() > 18)
                .map(person -> person.getName())
                .map(person -> person.toUpperCase())
                .collect(toList());
        System.out.println(names);
        List<String> names2 = persons.stream()
                .filter(person -> person.getAge() > 18)
                .map(Person::getName)
                .map(String::toUpperCase)
                .collect(toList());
        System.out.println(names2);

        persons.stream()
                .filter(person -> person.getGender() == Gender.MALE)
                .forEach(person -> System.out.println(person));
        persons.stream()
                .filter(person -> person.getGender() == Gender.MALE)
                .forEach(System.out::println);

        System.out.println(persons.stream()
                .map(Person::getAge)
                .reduce(0, (carry, age) -> carry + age));
        System.out.println(persons.stream()
                .map(Person::getAge)
                .reduce(0, Integer::sum));
        System.out.println(persons.stream()
                .mapToInt(Person::getAge)
                .sum());
        System.out.println(persons.stream()
                .max((e1, e2) -> e1.getAge() > e2.getAge() ? 1 : -1));
        System.out.println(persons.stream()
                .min((e1, e2) -> e1.getAge() > e2.getAge() ? 1 : -1));
        System.out.println(persons.stream()
                .map(Person::getAge)
                .min((e1, e2) -> e1 > e2 ? 1 : -1));
        System.out.println(persons.stream()
                .count());

        System.out.println(persons.stream()
                .filter(person -> person.getAge() > 18)
                .map(person -> person.getName().toUpperCase())
                .collect(toList()));
        System.out.println(persons.stream()
                .filter(person -> person.getAge() > 18)
                .map(person -> person.getName().toUpperCase())
                .collect(() -> new ArrayList<String>(),
                        (adultlist, name) -> adultlist.add(name),
                        (adultlist1, adultlist2) -> adultlist1.addAll(adultlist2)));

        System.out.println(persons.stream()
                .filter(StreamTest.GT17())
                .map(person -> person.getName().toUpperCase())
                .collect(toSet()));

        Map<String, Person> mapPerson = persons.stream()
                .collect(toMap(person -> person.getName() + ":" + person.getAge(),
                        person -> person));
        System.out.println(mapPerson);

        Map<String, List<Person>> mapPerson2 = persons.stream()
                .collect(groupingBy(Person::getName));
        System.out.println(mapPerson2);

        Stream.iterate(1, e -> e + 1)
                .filter(e -> e % 2 == 0)
                .limit(10)
                .forEach(System.out::println);


        List<Map<String, Integer>> list7 = new ArrayList<>();
        map3 = new HashMap<>();
        map3.put("grp_id", 1);
        map3.put("foo", 2);
        map3.put("bar", 6);
        list7.add(map3);
        map3 = new HashMap<>();
        map3.put("grp_id", 1);
        map3.put("xxx", 4);
        map3.put("yyy", null);
        list7.add(map3);
        map3 = new HashMap<>();
        map3.put("grp_id", 2);
        map3.put("ran1", 111);
        map3.put("ran2", 222);
        list7.add(map3);
        map3 = new HashMap<>();
        map3.put("grp_id", 2);
        map3.put("zzz", 444);
        map3.put("zzzaaa", null);
        list7.add(map3);
        map3 = new HashMap<>();
        map3.put("grp_id", 3);
        map3.put(null, 555);
        list7.add(map3);
        //null group by key in HM
        map3 = new HashMap<>();
        map3.put(null, 3);
        list7.add(map3);
        //add more group by keys
        map3 = new HashMap<>();
        map3.put("grp_id", 15);
        map3.put("ran3", 105);
        map3.put("ran4", 1005);
        list7.add(map3);
        map3 = new HashMap<>();
        map3.put("grp_id", 9);
        map3.put("ran31", 109);
        map3.put("ran41", 1009);
        list7.add(map3);
        map3 = new HashMap<>();
        map3.put("grp_id", 11);
        map3.put("ran31", 1011);
        map3.put("ran41", 10011);
        list7.add(map3);

        /*Map<Integer, List<Map<String, Integer>>> map4 = list7.stream()
                .collect(groupingBy(e -> e.get("grp_id")));*/
        //result error: Caused by: java.lang.NullPointerException: element cannot be mapped to a null key

        //fix
        Map<Optional<Integer>, List<Map<String, Integer>>> map4 = list7.stream()
                .collect(groupingBy(e -> Optional.ofNullable(e.get("grp_id"))));

        System.out.println(map4);
        System.out.println(map4.get(Optional.of(1)));

        System.out.println("--" + map4.keySet().size());
        System.out.println("--" + map4.keySet());
        System.out.println("--" + map4.keySet().stream().filter(e -> e.isPresent() && (e.get() <= 7 || e.get() >= 15)).count());
    }

    private static List<Map<String, Integer>> processMapData(List<Map<String, Integer>> list5, BiConsumer<HashMap<String, Integer>, Map.Entry<String, Integer>> a) {
        return list5.stream()
                    .map(hashmap -> {
                        Map<String, Integer> newMap = hashmap.entrySet().stream()
                                .collect(HashMap::new, a, HashMap::putAll);
                        return newMap;
                    })
                    .collect(toList());
    }

    private static BiConsumer<HashMap<String, Integer>, Map.Entry<String, Integer>> accumulator() {
        return (m, v) -> {
            //m.computeIfPresent("bar", (k1, v1) -> v1 * 2);

            /*if (v.getKey().equals("bar")) {
                m.put(v.getKey(), v.getValue() * 2);
            } else {
                m.put(v.getKey(), v.getValue());
            }*/
            m.put(v.getKey(), v.getKey().equals("bar") ? v.getValue() * 2 : v.getValue());
        };
    }

    public static Predicate<Person> GT17() {
        return person -> person.getAge() > 18;
    }

    public static List<Person> createPeople() {
        return Arrays.asList(
                new Person("Sara", Gender.FEMALE, 20),
                new Person("Sara", Gender.FEMALE, 22),
                new Person("Bob", Gender.MALE, 20),
                new Person("Paula", Gender.FEMALE, 32),
                new Person("Paul", Gender.MALE, 32),
                new Person("Jack", Gender.MALE, 2),
                new Person("Jack", Gender.MALE, 72),
                new Person("Jill", Gender.FEMALE, 12)
        );
    }

}

class Person {
    private final String name;
    private final Gender gender;
    private final int age;

    public Person(String theName, Gender theGender, int theAge) {
        name = theName;
        gender = theGender;
        age = theAge;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String toString() {
        return String.format("%s -- %s -- %d", name, gender, age);
    }
}

enum Gender {MALE, FEMALE}