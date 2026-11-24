package cz.roman.spanek.anv.y2026.pr09.behavioral.visitor.after;

interface DocumentVisitor {
    void visit(Text text);
    void visit(Image image);
    void visit(Table table);
}