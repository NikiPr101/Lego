package test;

import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;


public class ZerstoerungPt1 {
	static NXTRegulatedMotor L = Motor.A;
	static NXTRegulatedMotor R = Motor.D;
	static NXTRegulatedMotor Z = Motor.C;
	static EV3IRSensor ir = new EV3IRSensor(SensorPort.S1);
	static EV3ColorSensor c = new EV3ColorSensor(SensorPort.S3);
	

	public static void main(String[] args) throws InterruptedException {
		
		 Button.ENTER.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(Key k) {
		        LCD.clear();
				System.exit(0);
			}
			
			@Override
			public void keyPressed(Key k) {
				LCD.drawString("Shutdown when Button is released", 0, 0);
			}
		});
		 
		 Button.UP.addKeyListener(new KeyListener() {
				
			@Override
			public void keyReleased(Key k) {
				
				final SampleProvider sp = ir.getDistanceMode();
				int d;
				final SampleProvider cid = c.getColorIDMode();
				int cd;
				float [] Farben = new float[cid.sampleSize()];
			    sp.fetchSample(Farben, 0);
			    cd = (int)Farben[0];
			    c.open();
				do {
					L.forward();
					L.setSpeed(900);
					R.forward();
					R.setSpeed(900);
					float [] sample = new float[sp.sampleSize()];
				    sp.fetchSample(sample, 0);
				    d = (int)sample[0];
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} while(d>30);
				L.stop();
				R.stop();
				Z.rotate(720);
				Z.setSpeed(900);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(cd<2) {
				L.forward();
				L.setSpeed(900);
				R.forward();
				R.setSpeed(900);}
				L.backward();
				R.backward();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Z.rotate(-720);
				Z.setSpeed(900);
				ir.close();
				
			
			}
			
			@Override
			public void keyPressed(Key k) {}
		});
		 
		 
		 // "Main" Programm
		 Button.ESCAPE.waitForPress();
		 
		
	}
}
