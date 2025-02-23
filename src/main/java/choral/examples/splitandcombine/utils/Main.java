package choral.examples.splitandcombine.utils;

public class Main {

    public static Tasks split( Task task ){
        int size = task.list.size();
        Task sub1 = new Task( task.list.subList( 0, size/2 ) );
        Task sub2 = new Task( task.list.subList( size/2, size ) );
        return new Tasks( sub1, sub2 );
    }

    public static Result combine( Result subTask1, Result subTask2 ){
        return subTask1.combineWith(subTask2);
    }
}
