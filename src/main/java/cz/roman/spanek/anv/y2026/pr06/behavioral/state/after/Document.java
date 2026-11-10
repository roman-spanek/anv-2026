package cz.roman.spanek.anv.y2026.pr06.behavioral.state.after;

class Document {
    private State state;
    private final String currentUserRole;

    public Document(String currentUserRole) {
        this.currentUserRole = currentUserRole;
        this.state = new DraftState(); // počáteční stav
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getCurrentUserRole() {
        return currentUserRole;
    }

    public void publish() {
        state.publish(this);
    }

    public void edit() {
        state.edit(this);
    }
}
