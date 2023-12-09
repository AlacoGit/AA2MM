package com.github.JAA2M.Module;

import java.util.List;

import com.github.JAA2M.wString;

public class Module {
    public wString name;
    public wString desc;
    public final List<GlobalVariable> globals;
    public final List<Trigger> triggerList;
    public final List<wString> dependencies;


    public Module(wString name, wString desc, List<GlobalVariable> globals, List<Trigger> triggerList, List<wString> dependencies){
        this.name = name;
        this.desc = desc;
        this.globals = globals;
        this.triggerList = triggerList;
        this.dependencies = dependencies;
    }

}
