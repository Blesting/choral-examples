package choral.amend.splitandcombine.utils;

public class Tasks {

    Task task1;
    Task task2;
    
    public Tasks( Task task1, Task task2 ){
        this.task1 = task1;
        this.task2 = task2;
    }
    
    public Task first(){
        return task1;
    }
    public Task second(){
        return task2;
    }
}
