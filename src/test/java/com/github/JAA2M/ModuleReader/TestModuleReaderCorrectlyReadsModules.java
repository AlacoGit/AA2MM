package com.github.JAA2M.ModuleReader;

import java.util.List;

import com.github.JAA2M.Module.Module;
import com.github.JAA2M.Module.Trigger;
import com.github.JAA2M.Module.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestModuleReaderCorrectlyReadsModules {
    @Test
    void readMinimalModule(){
        Module result = ModuleReader.parse("src/test/modules/minimal module");
        Trigger trigger = new Trigger(Value.of("empty trigger"),List.of(),List.of(),List.of());
        Module golden = new Module(Value.of("minimal module"),Value.of(""),List.of(),List.of(trigger),List.of());
        assertEquals(golden,result);
    }
}
