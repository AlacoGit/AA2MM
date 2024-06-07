package com.github.JAA2M.Module;

import java.util.List;

public final class GUIAction {
    private final Action.ParameterisedAction action;
    private final List<GUIAction> subactions;
    private GUIAction parent;

    public GUIAction(Action.ParameterisedAction action, List<GUIAction> subactions) {
        this(action, subactions, null);
    }

    GUIAction(Action.ParameterisedAction action, List<GUIAction> subactions, GUIAction parent) {
        this.action = action;
        this.subactions = subactions;
        this.parent = parent;
    }

    public void setParent(GUIAction parent) {
        this.parent = parent;
    }

    public void appendPrintable(StringBuilder sb){
        this.appendPrintable(sb,1);
    }

    private void appendPrintable(StringBuilder sb, int indentLevel){
        sb.append(" ".repeat(indentLevel)).append(action.formatPrintable()).append("\n");
        int nextIndentLevel = indentLevel;
        if(action.action().id() == Action.Actions.IF.id || action.action().id() == Action.Actions.ELSEIF.id
                || action.action().id() == Action.Actions.ELSE.id || action.action().id() == Action.Actions.LOOP.id
                || action.action().id() == Action.Actions.FORLOOP.id){
            nextIndentLevel++;
        }
        for(GUIAction subaction: subactions){
            subaction.appendPrintable(sb,nextIndentLevel);
        }
    }
}
