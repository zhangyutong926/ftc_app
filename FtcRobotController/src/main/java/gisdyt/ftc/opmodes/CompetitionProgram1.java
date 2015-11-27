package gisdyt.ftc.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by zhangyutong926 on 15/11/27.
 *
 * Gisdyt Ftc Robotic Competition Program for Manual Control Peroid.
 * Chassis Operating by Arcade2/4 or Tank2/4 (use 2 to 1 connection for 4 motor control).
 * Mode switcher: Gamepad button X (default: Arcade)
 * Telemetrier will report operating mode and motor-encoders values.
 *
 * WARNING: SINGLE JOYSTICK CONTROLLER!!!
 */
public class CompetitionProgram1 extends OpMode {

    private DcMotor chassisMotorLeft, chassisMotorRight, upthrowerLeft, upthrowerRight;
    private Servo servoBall, servoPeople;

    /**
     * Hardware Mapping Settings
     */
    private static final String[] hardwareMapping={
            "cml", //chassisMotorLeft
            "cmr", //chassisMotorRight
            "ul", //upthrowerLeft
            "ur", //upthrowerRight
            "sb", //servoPeople
            "sp" //servoBall
    };

    /**
     * false - Normal
     * true - Reversed
     */
    private static final boolean[] dcMotorReverse={
            false, //cml
            false, //cmr
            false, //ul
            false //ur
    };

    /**
     * Motor Domain configs
     */
    private static final double motorDomainMin=-1.0, motorDomainMax=1.0;

    /**
     * Servo Domain configs
     */
    private static final double[] servoDegrees={
            0.0, //ball normal
            1.0, //ball active
            0.0, //people normal
            1.0 //people active
    };

    private enum ChassisOperatingModes{

        ARCADE("arcade"),
        TANK("tank");

        private String mode;


        @Override
        public String toString() {
            return mode;
        }

        ChassisOperatingModes(String s){
            mode=s;
        }
    }

    private ChassisOperatingModes mode=ChassisOperatingModes.ARCADE;

    @Override
    public void init() {
        telemetry.addData("Copyleft-Free GNU_GPL 3.0, Author:", "GISDYT - zhangyutong926");
        telemetry.addData("Process:", "Manual Control Program 1 Initing...");

        chassisMotorLeft=hardwareMap.dcMotor.get("cml");
        chassisMotorRight=hardwareMap.dcMotor.get("cmr");
        upthrowerLeft=hardwareMap.dcMotor.get("ul");
        upthrowerRight=hardwareMap.dcMotor.get("ur");

        telemetry.addData("Process:", "DcMotors, found without exceptions.");

        chassisMotorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        chassisMotorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        upthrowerLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        upthrowerRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        telemetry.addData("Process:", "DcMotors, encoder init without exceptions.");

        servoBall=hardwareMap.servo.get("sb");
        servoPeople=hardwareMap.servo.get("sp");

        telemetry.addData("Process:", "Servos, found without exceptions.");

        servoBall.setDirection(Servo.Direction.FORWARD);
        servoPeople.setDirection(Servo.Direction.FORWARD);

        servoBall.setPosition(0.0);
        servoPeople.setPosition(0.0);

        telemetry.addData("Process:", "Servos, init without exceptions.");
        telemetry.addData("Completed:", "Manual Control Program 1 for Competition, ready to start.");

    }

    private static void joystickTank2(float left_axis, float right_axis, DcMotor motor_left, DcMotor motor_right, boolean inv_left, boolean inv_right){
        if(!inv_left) motor_left.setPower(left_axis);
        else motor_left.setPower(-left_axis);
        if(!inv_right) motor_right.setPower(-right_axis);
        else motor_right.setPower(right_axis);
    }

    private static void joystickArcade2(float ward_axis, float rotate_axis, DcMotor motor_left, DcMotor motor_right, boolean inv_left, boolean inv_right){
        double l=ward_axis+rotate_axis;
        double r=-(ward_axis-rotate_axis);
        if(l<motorDomainMin) l=motorDomainMin;
        else if(l>motorDomainMax) l=motorDomainMax;
        if(r<motorDomainMin) r=motorDomainMin;
        else if(r>motorDomainMax) r=motorDomainMax;
        if(!inv_left) motor_left.setPower(l);
        else motor_left.setPower(-l);
        if(!inv_right) motor_right.setPower(-r);
        else motor_left.setPower(r);
    }

    private static int r2i(boolean r){
        return r?-1:1;
    }

    @Override
    public void loop() {
        telemetry.addData("Copyleft-Free GNU_GPL 3.0, Author:", "GISDYT - zhangyutong926");
        telemetry.addData("Status:", "Competition Manual Control Peroid started, please take an action.");

        telemetry.addData("Operating Mode:", mode.toString());

        telemetry.addData("DcMotor cml Encoder value:", chassisMotorLeft.getCurrentPosition());
        telemetry.addData("DcMotor cmr Encoder value:", chassisMotorRight.getCurrentPosition());
        telemetry.addData("DcMotor ul Encoder value:", upthrowerLeft.getCurrentPosition());
        telemetry.addData("DcMotor ur Encoder value:", upthrowerRight.getCurrentPosition());

        if(gamepad1.x){
            mode=mode==ChassisOperatingModes.ARCADE?ChassisOperatingModes.TANK:ChassisOperatingModes.ARCADE;
        }
        telemetry.addData("Status:", "Mode Switcher, no exception.");

        if(mode==ChassisOperatingModes.ARCADE){
            joystickArcade2(gamepad1.left_stick_y, gamepad1.right_stick_x, chassisMotorLeft, chassisMotorRight, dcMotorReverse[0], dcMotorReverse[1]);
        }else{
            joystickTank2(gamepad1.left_stick_y, gamepad1.right_stick_y, chassisMotorLeft, chassisMotorRight, dcMotorReverse[0], dcMotorReverse[1]);
        }
        telemetry.addData("Status:", "Chassis Control, no exception.");

        if(gamepad1.left_bumper){
            upthrowerLeft.setPower(motorDomainMax*r2i(dcMotorReverse[2]));
            upthrowerRight.setPower(motorDomainMin*r2i(dcMotorReverse[3]));
        }else if(gamepad1.right_bumper){
            upthrowerLeft.setPower(motorDomainMin*r2i(dcMotorReverse[2]));
            upthrowerRight.setPower(motorDomainMax*r2i(dcMotorReverse[3]));
        }else{
            upthrowerLeft.setPower(0.0);
            upthrowerRight.setPower(0.0);
        }
        telemetry.addData("Status:", "Upthrower, no exception.");

        if(!gamepad1.a){
            servoBall.setPosition(servoDegrees[0]);
        }else if(gamepad1.a){
            servoBall.setPosition(servoDegrees[1]);
        }
        if(!gamepad1.b){
            servoPeople.setPosition(servoDegrees[2]);
        }else if(gamepad1.b){
            servoPeople.setPosition(servoDegrees[3]);
        }
        telemetry.addData("Status:", "Servos, no exception.");

        telemetry.addData("Lifecycle:", "Program Loop Lifecycle finished.");
    }
}
