package com.matecode.focusgarden;

import android.os.CountDownTimer;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimerViewModel extends ViewModel {

    private CountDownTimer timer;
    private long declaredTime;
    private boolean isRunning = false;
    private final MutableLiveData<Long> time = new MutableLiveData<>();
    private final MutableLiveData<Boolean> finished = new MutableLiveData<>();  // finish flag for timer

    public MutableLiveData<Long> getTime() { return time; }
    public void setDeclaredTime(long ms) { this.declaredTime = ms; }
    public long getDeclaredTime() { return this.declaredTime; }
    public boolean getFinished() {  return Boolean.TRUE.equals(finished.getValue()); }

    public void startTimer(){
        if (isRunning) return; // prevent multiple timer threads start
        if (declaredTime <= 0) return; // safety check

        isRunning = true;
        finished.setValue(false);

        timer = new CountDownTimer(this.declaredTime,1000) {
            @Override
            public void onFinish() {
                isRunning = false;
                finished.setValue(true);
                time.setValue(0L);
            }

            @Override
            public void onTick(long ms) {
                time.setValue(ms);
            }
        }.start();
    }

    public void stopTimer(){
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        isRunning = false;
        finished.setValue(true);
    }

    @Override
    protected void onCleared() {
        stopTimer(); // cleanup when ViewModel dies
    }

}
