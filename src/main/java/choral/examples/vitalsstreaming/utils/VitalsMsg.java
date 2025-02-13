package choral.examples.vitalsstreaming.utils;

public class VitalsMsg {

    Signature signature;
    Vitals content;

	public VitalsMsg( Signature signature, Vitals content ){
        this.signature = signature;
        this.content = content;
    }

	public Signature signature(){
        return signature;
    }

	public Vitals content(){
        return content;
    }

}
