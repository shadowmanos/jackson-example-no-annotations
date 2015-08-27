import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
class Person {
    private final String name;
    private final String surname;
    private final int age;
}
