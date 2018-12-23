package recording;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class RecordedJoystick {

    private Joystick j;
    private SpecialButton jb;
    public boolean replay = false;
    private Timer clock = new Timer();
    private Saved last;
    public Saved start;
    public boolean recording = false;
    public String currFile = "";
    public int total = 0;

    public Joystick getJoystick() {
        return j;
    }

    public void startReplay() {
        clock.reset();
        clock.start();
        replay = true;
    }

    public void stopReplay() {
        replay = false;
        clock.stop();
    }

    public void startRecord() {
        if (!recording) {
            total = 1;
            recording = true;
            clock.reset();
            clock.start();
            last = new Saved(j, clock.get());
            start = last;
        }
    }

    public Saved makePlaceHolder() {
        return new Saved(j, 0);
    }

    public void stepRecord() {
        if (recording) {
            last.next = new Saved(j, clock.get());
            last = last.next;
            total++;
        }
    }

    public void stopRecord() {
        recording = false;
        clock.stop();
        //Save(currFile+part, true);
    }

    public boolean isDone() {
        return (start.next==null);
    }

    public double getTime() {
        return clock.get();
    }

    public RecordedJoystick(int port) {
        j = new Joystick(port);
        start = makePlaceHolder();
    }

    public double getRawAxis(int axis) {
        if (!replay) {
            return j.getRawAxis(axis);
        } else {
            while (start.next!=null && start.next.time<clock.get()) {
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
                while (rj.start.next!=null && rj.start.next.time<rj.clock.get()) {
                    rj.start = rj.start.next;
                }
                return (rj.start.butts&(1 << button-1)) != 0;
            }
        }
    }
}