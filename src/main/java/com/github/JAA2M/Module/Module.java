package com.github.JAA2M.Module;

import java.util.List;
import java.util.Objects;

public class Module {
    public Value.strValue name;
    public Value.strValue desc;
    public final List<GlobalVariable> globals;
    public final List<Trigger> triggerList;
    public final List<Value.strValue> dependencies;


    public Module(Value.strValue name, Value.strValue desc, List<GlobalVariable> globals, List<Trigger> triggerList, List<Value.strValue> dependencies){
        this.name = name;
        this.desc = desc;
        this.globals = globals;
        this.triggerList = triggerList;
        this.dependencies = dependencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module module)) return false;
        return Objects.equals(name, module.name) && Objects.equals(desc, module.desc) && Objects.equals(globals, module.globals) && Objects.equals(triggerList, module.triggerList) && Objects.equals(dependencies, module.dependencies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc, globals, triggerList, dependencies);
    }
}
