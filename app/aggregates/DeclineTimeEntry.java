package aggregates;

import java.util.Objects;
import java.util.UUID;

public class DeclineTimeEntry implements Command{
    private final UUID id;

    public DeclineTimeEntry(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeclineTimeEntry that = (DeclineTimeEntry) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DeclineTimeEntry{" +
            "id=" + id +
            '}';
    }
}
