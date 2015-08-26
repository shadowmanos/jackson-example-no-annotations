import java.io.File;

class Main {

    public static void main(String[] args) {
        Person person = Person.builder()
                .name("John")
                .surname( "Smith")
                .age(40)
                .build();

        File file = new File("person.json");
        PersonFileIO handler = new PersonFileIO();

        handler.write(file, person);

        Person anotherPerson = handler.read(file);
        if (anotherPerson != null) {
            System.out.println(anotherPerson);
        }
    }
}
