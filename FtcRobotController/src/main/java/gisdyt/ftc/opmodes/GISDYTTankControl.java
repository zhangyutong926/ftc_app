package gisdyt.ftc.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Administrator on 2015/11/13.
 */
public class GISDYTTankControl extends OpMode {

    private DcMotor motor1, motor2, motor3, motor4;

    @Override
    public void init() {
        motor1=hardwareMap.dcMotor.get("m1");
        motor2=hardwareMap.dcMotor.get("m2");
        motor3=hardwareMap.dcMotor.get("m3");
        motor4=hardwareMap.dcMotor.get("m4");
    }

    @Override
    public void loop() {
        float tank_left=gamepad1.left_stick_y;
        float tank_right=gamepad1.right_stick_y;
        motor1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor3.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor4.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor1.setPower(tank_left);
        motor3.setPower(tank_left);
        motor2.setPower(-tank_right);
        motor4.setPower(-tank_right);
        telemetry.addData("Motor1 Encoder:", motor1.getCurrentPosition());
        telemetry.addData("Motor2 Encoder:", motor2.getCurrentPosition());
        telemetry.addData("Motor3 Encoder:", motor3.getCurrentPosition());
        telemetry.addData("Motor4 Encoder:", motor4.getCurrentPosition());
    }
}
