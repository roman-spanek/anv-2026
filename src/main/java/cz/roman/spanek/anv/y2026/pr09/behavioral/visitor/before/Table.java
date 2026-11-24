package cz.roman.spanek.anv.y2026.pr09.behavioral.visitor.before;

class Table {
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

    public void exportAsPlainText() {
        System.out.println("Table: " + rows + "x" + columns);
    }

    public void exportAsXML() {
        System.out.println("<table rows=\"" + rows + "\" columns=\"" + columns + "\"/>");
    }
}