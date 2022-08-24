package io.rocketbase.mocoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

@Data
public class PaginatedResult<T> implements Iterable<T>, Serializable {

    private int page;
    private int perPage;
    private long total;

    private List<T> content;

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }

}
