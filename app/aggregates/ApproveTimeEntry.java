package aggregates;

import java.util.Objects;
import java.util.UUID;

public class ApproveTimeEntry implements Command{
    private final UUID id;

    public ApproveTimeEntry(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApproveTimeEntry that = (ApproveTimeEntry) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ApproveTimeEntry{" +
            "id=" + id +
            '}';
    }
}
