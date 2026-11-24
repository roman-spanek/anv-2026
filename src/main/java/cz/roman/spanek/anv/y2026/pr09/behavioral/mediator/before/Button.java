package cz.roman.spanek.anv.y2026.pr09.behavioral.mediator.before;

class Button {
    private Runnable onClick;
    public void setOnClick(Runnable r) { this.onClick = r; }
    public void click() { if (onClick != null) onClick.run(); }
}