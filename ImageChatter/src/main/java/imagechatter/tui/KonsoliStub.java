/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.tui;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author aatukallio
 */
public class KonsoliStub implements IO {
    
    ArrayList<String> inputs;
    int command;
    ArrayList<String> outputs;

    public KonsoliStub() {
        this.command = 0;
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public void add(String... inputs) {
        ArrayList<String> t = new ArrayList<>(Arrays.asList(inputs));
        this.inputs.addAll(t);
    }

    @Override
    public String nextLine() {
        return this.inputs.get(command++);
    }

    @Override
    public void print(String m) {
        outputs.add(m);
    }
    
    public ArrayList<String> getOutputs() {
        return outputs;
    }
}
