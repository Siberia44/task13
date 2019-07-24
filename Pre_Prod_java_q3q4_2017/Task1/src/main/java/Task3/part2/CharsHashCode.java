package Task3.part2;

import java.util.Objects;

public class CharsHashCode {
    private String string;

    public CharsHashCode(String string) {
        this.string = string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CharsHashCode that = (CharsHashCode) o;
        return Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        if (Objects.isNull(string)) {
            return 0;
        }
        int size = string.length() >= 4 ? 4 : string.length();
        int result = 0;
        for (int i = 0; i < size; i++) {
            result += string.charAt(i);
        }
        return result;
    }
}

