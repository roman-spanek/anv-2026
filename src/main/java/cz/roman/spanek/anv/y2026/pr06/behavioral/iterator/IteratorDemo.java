package cz.roman.spanek.anv.y2026.pr06.behavioral.iterator;

public class IteratorDemo {
    public static void main(String[] args) {
        BookCollection collection = new BookCollection();
        collection.add(new Book("Clean Code", "Robert C. Martin"));
        collection.add(new Book("Effective Java", "Joshua Bloch"));
        collection.add(new Book("Design Patterns", "GoF"));

        MyIterator<Book> it = collection.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
