package cz.roman.spanek.anv.y2026.pr09.behavioral.mediator.after;

class Button extends Component {
    public void click() { mediator.notify(this, "click"); }
}
