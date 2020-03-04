package howToKillYourEnemy;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class test {

	public static void main(String[] args) throws InterruptedException {
		NXTRegulatedMotor L = Motor.A;
		NXTRegulatedMotor R = Motor.D;
		NXTRegulatedMotor Z = Motor.C;
		EV3IRSensor ir = new EV3IRSensor(SensorPort.S1);
		final SampleProvider sp = ir.getDistanceMode();
		int d;
	do {
		L.forward();
		L.setSpeed(100);
		R.forward();
		R.setSpeed(100);
		float [] sample = new float[sp.sampleSize()];
	    sp.fetchSample(sample, 0);
	    d = (int)sample[0];
		Thread.sleep(100);
	} while(d>10);
	L.stop();
	R.stop();
	Z.rotate(720);
	Z.setSpeed(100);
	Thread.sleep(1000);
	Z.stop();
	L.forward();
	L.setSpeed(100);
	R.forward();
	R.setSpeed(100);
	Thread.sleep(2000);
	Z.rotate(-720);
	Z.setSpeed(100);
	Thread.sleep(1000);
	Z.stop();
	L.stop();
	R.stop();
	//
	ir.close();
		
	}

}
