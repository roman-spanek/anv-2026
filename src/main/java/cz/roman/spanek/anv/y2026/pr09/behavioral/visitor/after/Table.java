package cz.roman.spanek.anv.y2026.pr09.behavioral.visitor.after;

class Table implements DocumentElement {
    private int rows, columns;

    public Table(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}
