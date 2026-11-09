package cz.roman.spanek.anv.y2026.pr06.behavioral.chainofresponsibility;

public class Main {
    public static void main(String[] args) {
        Filter filter1 = new Filter(null);
        Request request1 = new Request(15);
        filter1.process(request1);
        filter1.process(null);

    }
}
