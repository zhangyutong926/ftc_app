package gisdyt.ftc.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Administrator on 2015/11/14.
 */
public class GISDYTTestArcade4 extends OpMode {

    private DcMotor motor1, motor2, motor3, motor4;

    @Override
    public void init() {
        telemetry.addData("Program:", "init@GISDYTTest1");
        telemetry.addData("Init Progress Status 1:", "Starting...");
        motor1=hardwareMap.dcMotor.get("motor1");
        motor2=hardwareMap.dcMotor.get("motor2");
        motor3=hardwareMap.dcMotor.get("motor3");
        motor4=hardwareMap.dcMotor.get("motor4");
    }

    @Override
    public void loop() {
        GISDYTRoboticOpUtil.joystickArcade4(gamepad1.left_stick_y, gamepad1.right_stick_x, motor1, motor2, motor3, motor4, false, false, false, false);
    }
}
