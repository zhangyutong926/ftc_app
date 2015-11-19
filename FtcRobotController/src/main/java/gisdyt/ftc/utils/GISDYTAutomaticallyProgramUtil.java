package gisdyt.ftc.utils;

import java.util.ArrayList;

/**
 * Created by GISDYT@SS on 2015/11/19.
 * By using this utility, you can write automatically period programes simplely.
 * The full specification documentation of this utility is in the <GISDYT Ftc New Android Platform Programming Guide(in Android Studio by Java) for Beginners>.
 */
public class GISDYTAutomaticallyProgramUtil {

    /**
     * Statement Item.
     */
    public interface AutomaticallyStatement{

        /**
         * Do something when Action started, such as set the power of motor.
         */
        public void statementStart();

        /**
         * Do something when Action stoped by time-out or expression, such as set 0 to the power of motor.
         */
        public void statementStop();

        /**
         * Do something try to make sure if the action can start.
         * @return true means start now and false means block and wait
         */
        public boolean canStart();

        /**
         * Do something try to make sure if the action finished.
         * You can use like return System.currentTimeMillis()-actionStartMilliTime==<Millis time you'd like> to judge a simple delay.
         * @param actionStartMilliTime when this action started
         * @return true means stop and false means continue
         */
        public boolean finished(long actionStartMilliTime);
    }

    /**
     * Functional Interface for Lambda Expression.
     */
    public interface AutomaticallyStatementStart{
        public void start();
    }

    /**
     * Functional Interface for Lambda Expression.
     */
    public interface AutomaticallyStatementStop{
        public void stop();
    }

    /**
     * Functional Interface for Lambda Expression.
     */
    public interface AutomaticallyStatementFinished{
        public boolean finished(long actionStartMilliTime);
    }

    /**
     * Functional Interface for Lambda Expression.
     */
    public interface AutomaticallyStatementCanStart{
        public boolean canStart();
    }

    private ArrayList<AutomaticallyStatement> actionList=new ArrayList<AutomaticallyStatement>();
    private int actionPointer=0;
    private long lastAtionStartMilliTime=System.currentTimeMillis();

    private enum ActionState{
        DidntRun,
        Running,
        Ran
    }
    private ActionState state=ActionState.DidntRun;

    /**
     * Just use created AutomaticallyStatement to register a new Action Item.
     * @param statement Created statement instance
     */
    public void registerStatement(AutomaticallyStatement statement){
        actionList.add(statement);
    }

    /**
     * Register for Lambda Expression by Functional Interface.
     * @param start Like AutomaticallyStatement#start
     * @param stop Like AutomaticallyStatement#stop
     * @param finished Like AutomaticallyStatement@finished
     */
    public void registerStatement(final AutomaticallyStatementStart start, final AutomaticallyStatementStop stop, final AutomaticallyStatementCanStart canStart, final AutomaticallyStatementFinished finished){
        registerStatement(new AutomaticallyStatement() {

            @Override
            public void statementStart() {
                start.start();
            }

            @Override
            public void statementStop() {
                stop.stop();
            }

            @Override
            public boolean canStart() {
                return canStart();
            }

            @Override
            public boolean finished(long actionStartMilliTime) {
                return finished.finished(actionStartMilliTime);
            }
        });
    }

    /**
     * Register a simple timely delay action by Functional Interface.
     * By using this method, you can register a action which will run immediately when it can run and stop immediately when time is out.
     * @param start Like AutomaticallyStatement#start
     * @param stop Like AutomaticallyStatement#stop
     * @param timeDelayMilli Time-out
     */
    public void registerStatement(final AutomaticallyStatementStart start, final AutomaticallyStatementStop stop, final long timeDelayMilli){
        registerStatement(new AutomaticallyStatement() {

            @Override
            public void statementStart() {
                start.start();
            }

            @Override
            public void statementStop() {
                stop.stop();
            }

            @Override
            public boolean canStart(){
                return true;
            }

            @Override
            public boolean finished(long actionStartMilliTime) {
                return System.currentTimeMillis()-actionStartMilliTime>timeDelayMilli;
            }
        });
    }

    /**
     * If this program is use-only for automatically program period, invoke this method in OpMpde#loop and don't do any other things.
     */
    public void loop(){
        if(state==ActionState.DidntRun && actionList.get(actionPointer).canStart()){
            state=ActionState.Running;
            lastAtionStartMilliTime=System.currentTimeMillis();
            actionList.get(actionPointer).statementStart();
        }
        if(state==ActionState.Running && actionList.get(actionPointer).finished(lastAtionStartMilliTime)){
            actionList.get(actionPointer).statementStop();
            state=ActionState.DidntRun;
            actionPointer++;
        }
    }
}
