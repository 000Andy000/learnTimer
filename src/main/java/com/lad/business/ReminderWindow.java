package com.lad.business;

/**
 * @author Andy
 * @date 2024-3-10 010 15:18
 */
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class ReminderWindow {
    private final int CONFIRM_TIME = 5;
    private final int REAPPEAR_TIME = 2;

    private Stage stage;
    private Runnable onTimeout;

    Button confirmButton = new Button("我没摸鱼 ("+CONFIRM_TIME+")");
    Timeline countdown = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
        String buttonText = confirmButton.getText();
        int timeLeft = Integer.parseInt(buttonText.replaceAll("[^0-9]", "")) - 1;
        if (timeLeft > 0) {
            confirmButton.setText("我没摸鱼 (" + timeLeft + ")");
        } else {
            onTimeout.run();
            stage.close();
        }
    }));


    Timeline reAppearCountdown = new Timeline(new KeyFrame(Duration.seconds(REAPPEAR_TIME), e -> {
        this.show();
    }));


    public ReminderWindow(Stage stage, Runnable onTimeout) {
        this.stage = stage;
        this.onTimeout = onTimeout;
        initUI();
    }

    private void initUI() {
        Label message = new Label("请在五秒内点击按钮，否则学习计时停止");

        countdown.setCycleCount(CONFIRM_TIME);

        // 确认按钮
        confirmButton.setOnAction(e -> {
            // 确认倒计时停止
            countdown.stop();
            // 关闭窗口
            stage.close();
            // 弹窗出现倒计时开始
            reAppearCountdown.play();
        });


        VBox layout = new VBox(10, message, confirmButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 300, 100);

        stage.setScene(scene);
        stage.setTitle("Reminder");
        // 窗口显示时开始倒计时
        stage.setOnShown(e -> countdown.play());
        // 主动关闭窗口时停止倒计时
        stage.setOnCloseRequest(e -> {
            countdown.stop();
            stage.close();
            reAppearCountdown.play();
        });

    }

    // 显示窗口
    public void show() {
        stage.show();
        confirmButton.setText("我没摸鱼 ("+CONFIRM_TIME+")");
        countdown.play();
    }


}
