package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  private long count;

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
//    if (persons.size() == 0) {
//      return Collections.emptyList();
//    }
//    persons.remove(0);
//    return persons.stream().map(Person::getFirstName).collect(Collectors.toList());

    // isEmpty() более предпочтительный вариант, лучше скипать в потоке
    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
//    return getNames(persons).stream().distinct().collect(Collectors.toSet());

    // был какой-то оверинжениринг, стримы тут не нужны, дистинкт тоже, так как и так к сету приводим
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
//    String result = "";
//    if (person.getSecondName() != null) {
//      result += person.getSecondName();
//    }
//
//    if (person.getFirstName() != null) {
//      result += " " + person.getFirstName();
//    }
//
//    if (person.getSecondName() != null) {
//      result += " " + person.getSecondName();
//    }
//    return result;

    // Было некорретно составлен резултат, не было отчества

    // Классно выглядит!
    return Stream.of(person.getFirstName(), person.getSecondName(), person.getMiddleName())
            .filter(Objects::nonNull).collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
//    Map<Integer, String> map = new HashMap<>(1);
//    for (Person person : persons) {
//      if (!map.containsKey(person.getId())) {
//        map.put(person.getId(), convertPersonToString(person));
//      }
//    }
//    return map;

    // лучше использовать стрим + коллект
    return persons.stream().collect(Collectors.toMap(Person::getId, this::convertPersonToString, (presentName, newName) -> presentName));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
//    boolean has = false;
//    for (Person person1 : persons1) {
//      for (Person person2 : persons2) {
//        if (person1.equals(person2)) {
//          has = true;
//        }
//      }
//    }
//    return has;

    return persons1.stream().anyMatch(new HashSet<>(persons2)::contains);
  }

  //...
  public long countEven(Stream<Integer> numbers) {
//    count = 0;
//    numbers.filter(num -> num % 2 == 0).forEach(num -> count++);
//    return count;

    // лучше использовать count у стрима
    return numbers.filter(num -> num % 2 == 0).count();
  }

  @Override
  public boolean check() {
    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = true;
    boolean reviewerDrunk = false;
    return codeSmellsGood || reviewerDrunk;
  }
}
