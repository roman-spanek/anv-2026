package cz.roman.spanek.anv.y2026.pr09.behavioral.mediator.after;

class CheckBox extends Component {
    private boolean checked;
    public void setChecked(boolean checked) {
        this.checked = checked;
        mediator.notify(this, "checkedChanged");
    }
    public boolean isChecked() { return checked; }
}
