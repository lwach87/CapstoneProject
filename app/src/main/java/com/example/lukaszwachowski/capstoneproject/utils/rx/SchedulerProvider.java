package com.example.lukaszwachowski.capstoneproject.utils.rx;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

  Scheduler io();

  Scheduler ui();
}
