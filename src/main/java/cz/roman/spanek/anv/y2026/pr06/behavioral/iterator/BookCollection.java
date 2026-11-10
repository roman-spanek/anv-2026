package cz.roman.spanek.anv.y2026.pr06.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class BookCollection {
    private final List<Book> items = new ArrayList<>();

    public void add(Book book) {
        items.add(book);
    }

    public MyIterator<Book> iterator() {
        return new BookIterator(items);
    }
}