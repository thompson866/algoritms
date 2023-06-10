import org.example.StringList;
import org.example.StringListImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.stream.Stream;

public class StringListImplTest {
    private final StringList stringList = new StringListImpl();

    @AfterEach
    public void afterEach() {
        stringList.clear();
    }

    @Test
    void addTest() {
        String[] elements = {"a1", "b2", "c3", "d4"};
        add(elements);

        for (int i = 0; i < elements.length; i++) {
            assertThat(stringList.get(i)).isEqualTo(elements[i]);
            assertThat(stringList.contains(elements[i])).isTrue();
            assertThat(stringList.indexOf(elements[i])).isEqualTo(i);
            assertThat(stringList.lastIndexOf(elements[i])).isEqualTo(i);
        }

        assertThat(stringList.toArray()).hasSize(elements.length);
        assertThat(stringList.toArray()).containsExactly(elements);
    }

    @Test
    void addByIndexTest() {
        String[] elements = {"a1", "b2", "c3", "d4"};
        add(elements);

        stringList.add(0, "e5");
        assertThat(stringList.size()).isEqualTo(elements.length + 1);
        assertThat(stringList.get(0)).isEqualTo("e5");

        stringList.add(3, "a2");
        assertThat(stringList.size()).isEqualTo(elements.length + 2);
        assertThat(stringList.get(3)).isEqualTo("a2");
        assertThat(stringList.lastIndexOf("a2")).isEqualTo(3);
        assertThat(stringList.indexOf("a2")).isEqualTo(3);

        stringList.add(4, "f1");
        assertThat(stringList.size()).isEqualTo(elements.length + 3);
        assertThat(stringList.get(4)).isEqualTo("f1");
        assertThat(stringList.lastIndexOf("f1")).isEqualTo(4);
        assertThat(stringList.indexOf("f1")).isEqualTo(4);
    }

    private void add(String[] elements) {
        assertThat(stringList.isEmpty()).isTrue();
        Stream.of(elements).forEach(stringList::add);
        assertThat(stringList.size()).isEqualTo(elements.length);
    }

}


