package cz.roman.spanek.anv.y2026.pr06.behavioral.iterator;

import java.util.List;
import java.util.NoSuchElementException;

public class BookIterator implements MyIterator<Book> {
    private final List<Book> books;
    private int index = 0;

    public BookIterator(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean hasNext() {
        return index < books.size();
    }

    @Override
    public Book next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more books.");
        }
        return books.get(index++);
    }
}