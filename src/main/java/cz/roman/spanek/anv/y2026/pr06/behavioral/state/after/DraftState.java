package cz.roman.spanek.anv.y2026.pr06.behavioral.state.after;

class DraftState implements State {

    public void publish(Document context) {
        context.setState(new ModerationState());
        System.out.println("Document moved to moderation.");
    }

    public void edit(Document context) {
        System.out.println("Document edited.");
    }
}