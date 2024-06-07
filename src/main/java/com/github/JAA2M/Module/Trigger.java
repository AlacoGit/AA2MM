package com.github.JAA2M.Module;

import com.github.JAA2M.wString;

import java.util.List;
import java.util.Objects;

public class Trigger {
    private static final int INSERT_START = -1;
    private static final int INSERT_END = -2;
    private final Value.strValue name;
    private final List<Event.ParameterisedEvent> events;
    private final List<Variable> vars;
    private final List<GUIAction> guiActions;
    private List<Action.ParameterisedAction> actions;
    private List<GlobalVariable> globalVars;

    public Trigger(Value.strValue name, List<Event.ParameterisedEvent> events, List<Variable> vars, List<GUIAction> guiActions) {
        this.name = name;
        this.events = events;
        this.vars = vars;
        this.guiActions = guiActions;
    }

    public String toPrintable(){
        StringBuilder sb = new StringBuilder();
        sb.append("Trigger:").append(this.name).append("\n");
        sb.append("Events:\n");
        for(Event.ParameterisedEvent e : events){
            sb.append(" ").append(e.toPrintable()).append("\n");
        }
        sb.append("Variables:\n");
        for(Variable v : vars){
            sb.append(" ").append(v.name()).append(" = ").append(v.defaultValue()).append("\n");
        }
        sb.append("Actions:\n");
        for(GUIAction action : guiActions){
            action.appendPrintable(sb);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trigger trigger)) return false;
        return Objects.equals(name, trigger.name) && Objects.equals(events, trigger.events) && Objects.equals(vars, trigger.vars) && Objects.equals(guiActions, trigger.guiActions) && Objects.equals(actions, trigger.actions) && Objects.equals(globalVars, trigger.globalVars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, events, vars, guiActions, actions, globalVars);
    }
}
