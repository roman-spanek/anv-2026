package cz.roman.spanek.anv.y2026.pr09.behavioral.visitor.after;

// Element interface declares accept() for a visitor
interface DocumentElement {
    void accept(DocumentVisitor visitor);
}