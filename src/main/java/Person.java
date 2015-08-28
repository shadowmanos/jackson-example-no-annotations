class Person {
    private final String name;
    private final String surname;
    private final int age;

    @Override
    public String toString() {
        return "Person{" +
               "name='" + name + '\'' +
               ", surname='" + surname + '\'' +
               ", age=" + age +
               '}';
    }

    Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
}
