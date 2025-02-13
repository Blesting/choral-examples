package choral.examples.vitalsstreaming.utils;

import java.util.Stack;

public class Sensor {

    Stack<VitalsMsg> stack;
    
    public Sensor( Stack<VitalsMsg> stack ){
        this.stack = stack;
    }

	public Boolean isOn(){
        return !stack.empty();
    }

	public VitalsMsg next(){
        return stack.pop();
    }
    
}
