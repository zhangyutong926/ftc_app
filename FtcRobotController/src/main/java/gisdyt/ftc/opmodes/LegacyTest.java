package gisdyt.ftc.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Administrator on 2015/11/13.
 */
public class LegacyTest extends OpMode {

    private DcMotor lm1;

    @Override
    public void init() {
        telemetry.addData("Program", "Init");
        lm1=hardwareMap.dcMotor.get("lm1");
        lm1.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    @Override
    public void loop() {
        telemetry.addData("Program", "Start");
        float a = gamepad1.left_stick_y;
        lm1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        lm1.setPower(a);
        try {
            telemetry.addData("Encoder:", lm1.getCurrentPosition());
        }catch(IllegalArgumentException e){
            String temp=e.toString();
            for(int i=0;i<e.getStackTrace().length;++i) temp+="\n"+e.getStackTrace()[i];
            telemetry.addData("Exception: ", temp);
        }
    }
}
