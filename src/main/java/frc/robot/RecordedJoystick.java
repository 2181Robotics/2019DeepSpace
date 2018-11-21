package frc.robot;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class RecordedJoystick {
    private Joystick j;
    private SpecialButton jb;
    public boolean replay = false;
    public DriverStation ds = DriverStation.getInstance();
    private Timer clock = new Timer();
    private Saved last;
    public Saved start;
    public boolean recording = false;

    public Joystick getJoystick() {
        return j;
    }

    public void startRecord() {
        if (!recording) {
            recording = true;
            clock.reset();
            clock.start();
            last = new Saved(j, clock.get());
            start = last;
        }
    }

    public void stepRecord() {
        if (recording) {
            last.next = new Saved(j, clock.get());
            last = last.next;
        }
    }

    public void stopRecord() {
        recording = false;
        clock.stop();
    }

    public boolean isDone() {
        return ((clock.get())>15||!recording);
    }

    public double getTimeRemain() {
        if (recording) return 15-(clock.get());
        else return 0;
    }

    public void Save(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(start);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            DriverStation.reportError(e.toString(), false);
        }
    }

    public void Load(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            start = (Saved)ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            DriverStation.reportError(e.toString(), false);
        }
    }


    public RecordedJoystick(int port) {
        j = new Joystick(port);
    }

    public double getRawAxis(int axis) {
        if (!replay) {
            return j.getRawAxis(axis);
        } else {
            while (start.next!=null && start.next.time<ds.getMatchTime()) {
                start = start.next;
            }
            return start.joys[axis];
        }
    }

    public void whenPressed(int button, Command command) {
        jb = new SpecialButton(j, button, jb, this);
        jb.whenPressed(command);
    }

    public void whenReleased(int button, Command command) {
        jb = new SpecialButton(j, button, jb, this);
        jb.whenReleased(command);
    }

    public void whileHeld(int button, Command command) {
        jb = new SpecialButton(j, button, jb, this);
        jb.whileHeld(command); //command is started repeatedly while button is held, possibly interrupting itself
    }

    public void toggleWhenPressed(int button, Command command) {
        jb = new SpecialButton(j, button, jb, this);
        jb.toggleWhenPressed(command);
    }

    public void cancelWhenPressed(int button, Command command) {
        jb = new SpecialButton(j, button, jb, this);
        jb.cancelWhenPressed(command);
    }

    private class SpecialButton extends Button {
        private Joystick j;
        private int button;
        private SpecialButton jb;
        private RecordedJoystick rj;
        
        public SpecialButton(Joystick j, int button, SpecialButton jb, RecordedJoystick rj) {
            this.j = j;
            this.button = button;
            this.jb = jb;
            this.rj = rj;
        }

        public boolean get() {
            // returns true when button is supposed to be active
            if (!rj.replay) {
                return j.getRawButton(button);
            } else {
                while (rj.start.next != null && rj.start.next.time<rj.ds.getMatchTime()) {
                    rj.start = rj.start.next;
                }
                return rj.start.butts[button-1];
            }
        }
    }
}

class Saved implements Serializable {
    public Saved next;
    public double time;
    public double[] joys;
    public boolean[] butts;
    public boolean last=false;

    public Saved(Joystick j, double startTime) {
        time = DriverStation.getInstance().getMatchTime() - startTime;
        joys = new double[j.getAxisCount()];
        for (int i=0; i<j.getAxisCount(); i++) {
            joys[i] = j.getRawAxis(i);
        }
        butts = new boolean[j.getButtonCount()];
        for (int i=1; i<=j.getButtonCount(); i++) {
            butts[i-1] = j.getRawButton(i);
        }
    }

    private void writeObject(ObjectOutputStream oos) 
    throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(time);
        oos.writeObject(joys);
        oos.writeObject(butts);
        if (next!=null) {
            oos.writeObject(true);
        } else {
            oos.writeObject(false);
            oos.writeObject(next);
        };
    }

    private void readObject(ObjectInputStream ois) 
    throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        time = (double)ois.readObject();
        joys = (double[])ois.readObject();
        butts = (boolean[])ois.readObject();
        last = (boolean)ois.readObject();
        if (!last) {
            next = (Saved)ois.readObject();
        }
    }
}