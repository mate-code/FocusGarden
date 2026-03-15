package com.matecode.focusgarden;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

public class TimerViewModel extends ViewModel {

    private CountDownTimer timer;
    private long declaredTime;
    private final MutableLiveData<Long> time = new MutableLiveData<>();
    private final MutableLiveData<Boolean> finished = new MutableLiveData<>();  // finish flag for timer


    public MutableLiveData<Long> getTime() { return time; }
    public void setDeclaredTime(long ms) { this.declaredTime = ms; }
    public long getDeclaredTime() { return this.declaredTime; }
    public boolean getFinished() {  return Boolean.TRUE.equals(finished.getValue()); }

    public void startTimer(){
        finished.setValue(false);

        timer = new CountDownTimer(this.declaredTime,1000) {
            @Override
            public void onFinish() {
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
            finished.setValue(true);
            timer.cancel();
        }
    }

}
