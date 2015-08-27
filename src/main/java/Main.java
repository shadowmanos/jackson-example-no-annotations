import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.io.File;
import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {
        Person person = Person.builder()
                .name("John")
                .surname("Smith")
                .age(40)
                .build();

        File file = new File("person.json");

        ObjectMapper mapper = new ObjectMapper();

        // Avoid having to annotate the Person class
        // Requires Java 8, pass -parameters to javac
        // and jackson-module-parameter-names as a dependency
        mapper.registerModule(new ParameterNamesModule());

        // make private fields of Person visible to Jackson
        mapper.setVisibility(FIELD, ANY);


        mapper.writeValue(file, person);

        Person anotherPerson = mapper.readValue(file, Person.class);
        System.out.println(anotherPerson);
    }
}
