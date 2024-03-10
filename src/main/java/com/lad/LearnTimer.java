package com.lad;

import com.lad.business.HistoryTool;
import com.lad.business.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.control.*;
import javafx.util.Duration;

import java.util.List;

/**
 * Hello world!
 *
 * @author AD
 */
public class LearnTimer extends Application {

    private int hours = 0, minutes = 0, seconds = 0;


    @Override
    public void start(Stage primaryStage) {
        List<Integer> history = HistoryTool.getHistory();
        hours = history.get(0);
        minutes = history.get(1);
        seconds = history.get(2);

        // 创建标签和按钮
        Timer timer = new Timer(hours, minutes, seconds);

        timer.setFont(new Font("Arial", 32)); // 设置标签的字体和大小
        Button startButton = new Button("计时");
        Button stopButton = new Button("暂停");


        // 将两个按钮放在同一行
        HBox buttonsLayout = new HBox(10); // 水平布局，按钮间隔为10
        buttonsLayout.setAlignment(Pos.CENTER); // 居中对齐按钮
        buttonsLayout.getChildren().addAll(startButton, stopButton);

        // 创建一个垂直布局，加入标签和按钮行
        VBox layout = new VBox(20); // 增加间隔，使得布局更加宽敞
        layout.setAlignment(Pos.CENTER); // 垂直布局中的元素居中对齐
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(timer, buttonsLayout); // 添加标签和按钮行

        // 创建场景，并设置到舞台上
        Scene scene = new Scene(layout, 300, 200); // 调整场景大小以适应内容
        primaryStage.setScene(scene);
        primaryStage.setTitle("计时器");
        primaryStage.show();

        // 设置关闭事件
        primaryStage.setOnCloseRequest(e -> {
            timer.pause();
            Platform.exit();
        });

        // 按钮事件
        startButton.setOnAction(e -> timer.start());
        stopButton.setOnAction(e -> timer.pause());


        timer.start();
    }


    /*
     * 程序启动时调用，用于初始化时间
     */
    public void startHook() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
