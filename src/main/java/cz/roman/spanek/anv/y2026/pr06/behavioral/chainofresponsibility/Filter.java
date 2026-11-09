package cz.roman.spanek.anv.y2026.pr06.behavioral.chainofresponsibility;

public class Filter implements FilterInterface {
    private Filter next;
    int threshold;
    public Filter(Filter next, int threshold) {
        this.next = next;
        this.threshold = threshold;
    }

    public void process (Request request){
        if (request == null) {
            System.out.println("Filter: Request is null, cannot process.");
            return;
        }

        if (request.getValue() < threshold) {
            System.out.println("Filter: Request value is less than 10, processing...");
        } else {
            if (next == null) {
                System.out.println("Filter: Next is null, cannot process.");
            } else {
                next.process(request);
            }
        }


    }
}
