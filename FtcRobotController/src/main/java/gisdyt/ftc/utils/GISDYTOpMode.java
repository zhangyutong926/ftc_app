package gisdyt.ftc.utils;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Administrator on 2015/11/16.
 */
public abstract class GISDYTOpMode extends OpMode {

    private ElapsedTime runtime;

    @Override
    public void init() {
        telemetry.addData("GISDYTOpModeUtility", "Welcome to GISDYTOpMode Utility!\nUtility and your robot are running normally.");
        runtime.reset();
    }

    @Override
    public void loop() {

    }

    public abstract void robotMain();
}
