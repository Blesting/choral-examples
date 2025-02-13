package choral.examples.vitalsstreaming.utils;

public class Vitals {

    String id;
    String heartRate;
    String temperature;
    String motion; 

	public Vitals ( String id, String heartRate, String temperature, String motion ){
        this.id = id;
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.motion = motion;
    }

	public String id(){
        return id;
    }

	public String heartRate(){
        return heartRate;
    }

	public String temperature(){
        return temperature;
    }

	public String motion(){
        return motion;
    }

}
