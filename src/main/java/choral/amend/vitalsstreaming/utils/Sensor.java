package choral.amend.vitalsstreaming.utils;

import java.util.Stack;

public class Sensor {

    Stack<VitalsMsg> stack;
    
    public Sensor( Stack<VitalsMsg> stack ){
        this.stack = stack;
    }

    public Stack<VitalsMsg> stack(){
        return stack;
    }

	public Boolean isOn(){
        return !stack.empty();
    }

	public VitalsMsg next(){
        return stack.pop();
    }
    
}
