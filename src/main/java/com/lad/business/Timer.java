package com.lad.business;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Data;

/**
 * @author Andy
 * @date 2024-3-10 010 14:02
 */
@Data
public class Timer extends Label {
    private int hours;
    private int minutes;
    private int seconds;
    private boolean isRunning = true;
    private Timeline timeline;

    public Timer(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.setFont(new Font("Arial", 32));
        this.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer(this)));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void updateTimer(Label timeLabel) {
        seconds++;
        if (seconds >= 60) {
            minutes += seconds / 60;
            seconds -= 60*(seconds / 60);
        }
        if (minutes >= 60) {
            hours += minutes / 60;
            minutes -= 60*(minutes / 60);
        }
        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    public void start(Stage stage) {
        stage.setAlwaysOnTop(false);
        this.isRunning = true;
        this.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        timeline.play();
    }

    public void pause(Stage stage) {
        stage.setAlwaysOnTop(true);
        this.isRunning = false;
        timeline.pause();
        HistoryTool.recordHistory(this);
    }

    public void reset(Label timeLabel) {
        this.isRunning = false;
        timeline.stop();
        hours = 0;
        minutes = 0;
        seconds = 0;
        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
}
