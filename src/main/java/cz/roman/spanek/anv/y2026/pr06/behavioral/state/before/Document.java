package cz.roman.spanek.anv.y2026.pr06.behavioral.state.before;


class Document {
    public static final int DRAFT = 0;
    public static final int MODERATION = 1;
    public static final int PUBLISHED = 2;

    private int state = DRAFT;
    private final String currentUserRole;

    public Document(String currentUserRole) {
        this.currentUserRole = currentUserRole;
    }

    public void publish() {
        if(state == DRAFT) {
            state = MODERATION;
            System.out.println("Document moved to moderation.");
        } else if(state == MODERATION) {
            if("admin".equals(currentUserRole)) {
                state = PUBLISHED;
                System.out.println("Document published.");
            } else {
                System.out.println("Only admin can publish.");
            }
        } else if(state == PUBLISHED) {
            System.out.println("Document is already published.");
        }
    }

    public void edit() {
        if(state == PUBLISHED) {
            System.out.println("Cannot edit a published document.");
        } else {
            System.out.println("Document edited.");
        }
    }
}

/*
Problémy tohoto přístupu:
--------------------------------
Obrovské if/else bloky.
Každá nová metoda, která závisí na stavu, musí opět kontrolovat state.
Pokud přidáme nový stav, musíme upravit všechny podmínky.
Kód porušuje SRP a OCP.
 */