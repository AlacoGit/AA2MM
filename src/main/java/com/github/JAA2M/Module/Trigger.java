package com.github.JAA2M.Module;

import com.github.JAA2M.wString;

import java.util.List;

public class Trigger {
    private static final int INSERT_START = -1;
    private static final int INSERT_END = -2;
    private final wString name;
    private final List<Event.ParameterisedEvent> events;
    private final List<Variable> vars;
    private final List<GUIAction> guiActions;
    private List<Action.ParameterisedAction> actions;
    private List<GlobalVariable> globalVars;

    public Trigger(wString name, List<Event.ParameterisedEvent> events, List<Variable> vars, List<GUIAction> guiActions) {
        this.name = name;
        this.events = events;
        this.vars = vars;
        this.guiActions = guiActions;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Trigger:").append(this.name).append("\n");
        sb.append("Events:\n");
        for(Event.ParameterisedEvent e : events){
            sb.append(" ").append(e.event().name).append("\n");
        }
        sb.append("Variables:\n");
        for(Variable v : vars){
            sb.append(" ").append(v.name()).append(" = ").append(v.defaultValue()).append("\n");
        }
        sb.append("Actions:\n");
        for(GUIAction action : guiActions){
            sb.append(" ").append(action.)
        }
    }
}
