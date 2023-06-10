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
        Integer[] elements = {5,20,65,48,635};
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
        Integer[] elements = {5,20,65,48,635};
        add(elements);

        stringList.add(0, 5);
        assertThat(stringList.size()).isEqualTo(elements.length + 1);
        assertThat(stringList.get(0)).isEqualTo(5);

        stringList.add(3, 10000);
        assertThat(stringList.size()).isEqualTo(elements.length + 2);
        assertThat(stringList.get(3)).isEqualTo(10000);
        assertThat(stringList.lastIndexOf(10000)).isEqualTo(3);
        assertThat(stringList.indexOf(10000)).isEqualTo(3);

        stringList.add(4, 222);
        assertThat(stringList.size()).isEqualTo(elements.length + 3);
        assertThat(stringList.get(4)).isEqualTo(222);
        assertThat(stringList.lastIndexOf(222)).isEqualTo(4);
        assertThat(stringList.indexOf(222)).isEqualTo(4);
    }

    private void add(Integer[] elements) {
        assertThat(stringList.isEmpty()).isTrue();
        Stream.of(elements).forEach(stringList::add);
        assertThat(stringList.size()).isEqualTo(elements.length);
    }

}


