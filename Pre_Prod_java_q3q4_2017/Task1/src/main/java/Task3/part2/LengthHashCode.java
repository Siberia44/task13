package Task3.part2;

import java.util.Objects;

public class LengthHashCode {
    private String string;

    public LengthHashCode(String string) {
        this.string = string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LengthHashCode that = (LengthHashCode) o;
        return Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        if (Objects.isNull(string)) {
            return 0;
        }
        return string.length();
    }
}
