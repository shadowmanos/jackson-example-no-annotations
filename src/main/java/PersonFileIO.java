import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.io.File;
import java.io.IOException;

class PersonFileIO {

    private final ObjectWriter writer;
    private final ObjectReader reader;

    public PersonFileIO() {
        ObjectMapper mapper = new ObjectMapper();

        // Avoid having to annotate the Person class
        // Requires Java 8, pass -parameters to javac
        // and jackson-module-parameter-names as a dependency
        mapper.registerModule(new ParameterNamesModule());

        // Use byte code instead of reflection for accessing fields
        // Requires jackson-module-afterburner as a dependency
        mapper.registerModule(new AfterburnerModule());

        // make private fields of Person visible to Jackson
        mapper.setVisibility(FIELD, ANY);

        // creating custom readers and writers once for the specified class
        // otherwise they would be created at each read or write
        // INDENT_OUTPUT will pretty print the document
        writer = mapper.writerFor(Person.class).with(INDENT_OUTPUT);
        reader = mapper.readerFor(Person.class);
    }

    void write(File file, Person person) {
        try {
            writer.writeValue(file, person);
        } catch (IOException e) {
            System.err.println("Failed to write Person " + person + " to file " + file.getName());
        }
    }

    Person read(File file) {
        try {
            return reader.readValue(file);
        } catch (IOException e) {
            System.err.println("Failed to parse Person from file " + file.getName());
            return null;
        }
    }
}
