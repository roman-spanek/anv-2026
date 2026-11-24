package cz.roman.spanek.anv.y2026.pr09.behavioral.mediator.after;

abstract class Component {
    protected Mediator mediator;
    public void setMediator(Mediator mediator) { this.mediator = mediator; }
}
