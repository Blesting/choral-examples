package choral.amend.splitandcombine.utils;

public class Result {
    
    Integer value;

    public Result( Integer value ){
        this.value = value;
    }

    public Integer value(){
        return value;
    }
    
    public Result combineWith( Result otherResult ){
        Integer sum = value + otherResult.value;
        return new Result( sum );
    }
}
