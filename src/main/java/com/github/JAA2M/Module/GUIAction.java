package com.github.JAA2M.Module;

import java.util.List;

public final class GUIAction {
    private final Action.ParameterisedAction action;
    private List<GUIAction> subactions;
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
}
