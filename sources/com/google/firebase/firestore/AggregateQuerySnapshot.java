package com.google.firebase.firestore;

import com.google.firebase.firestore.util.Preconditions;
import java.util.Objects;

public class AggregateQuerySnapshot {
    private final long count;
    private final AggregateQuery query;

    AggregateQuerySnapshot(AggregateQuery query2, long count2) {
        Preconditions.checkNotNull(query2);
        this.query = query2;
        this.count = count2;
    }

    public AggregateQuery getQuery() {
        return this.query;
    }

    public long getCount() {
        return this.count;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AggregateQuerySnapshot)) {
            return false;
        }
        AggregateQuerySnapshot other = (AggregateQuerySnapshot) object;
        if (this.count != other.count || !this.query.equals(other.query)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Long.valueOf(this.count), this.query});
    }
}
