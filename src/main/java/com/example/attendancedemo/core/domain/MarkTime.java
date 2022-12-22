package com.example.attendancedemo.core.domain;

public class MarkTime {

    private long workin;
    private long workout;

    @Override
    public String toString() {
        return "MarkTime{" +
                "workin=" + workin +
                ", workout=" + workout +
                '}';
    }

    public long getWorkin() {
        return workin;
    }

    public void setWorkin(long workin) {
        this.workin = workin;
    }

    public long getWorkout() {
        return workout;
    }

    public void setWorkout(long workout) {
        this.workout = workout;
    }
}
