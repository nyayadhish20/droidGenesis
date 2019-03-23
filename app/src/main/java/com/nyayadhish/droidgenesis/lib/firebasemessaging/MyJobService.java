package com.nyayadhish.droidgenesis.lib.firebasemessaging;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;


public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";

    @Override
    public boolean onStartJob(JobParameters job) {
        Log.d(TAG, "Performing long running task in scheduled job");
        // TODO(developer): add long running task here.
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}
