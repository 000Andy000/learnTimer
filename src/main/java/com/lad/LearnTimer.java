package com.lad;

import com.lad.business.HistoryTool;
import com.lad.business.ReminderWindow;
import com.lad.business.Timer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.*;

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

        initHook();

        // 创建标签和按钮
        Timer timer = new Timer(hours, minutes, seconds);
        Button startButton = new Button("计时");
        Button stopButton = new Button("暂停");

        // 提示窗口
        Stage stage = new Stage();
        ReminderWindow reminderWindow = new ReminderWindow(stage, timer::pause);

        // 将两个按钮放在同一行
        // 水平布局，按钮间隔为10
        HBox buttonsLayout = new HBox(10);
        // 居中对齐按钮
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.getChildren().addAll(startButton, stopButton);
        // 创建一个垂直布局，加入标签和按钮行
        // 增加间隔，使得布局更加宽敞
        VBox layout = new VBox(20);
        // 垂直布局中的元素居中对齐
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        // 添加标签和按钮行
        layout.getChildren().addAll(timer, buttonsLayout);
        // 创建主场景，并设置到舞台上
        // 调整场景大小以适应内容
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("计时器");
        primaryStage.show();
        // 设置关闭事件
        primaryStage.setOnCloseRequest(e -> {
            timer.pause();
            Platform.exit();
        });
        // 设置按钮事件
        startButton.setOnAction(e -> {
            timer.start();
        });
        reminderWindow.show();

        stopButton.setOnAction(e -> timer.pause());




        timer.start();
    }


    /*
     * 程序初始化时调用，用于初始化时间
     */
    public void initHook() {
        // 初始化时间
        List<Integer> history = HistoryTool.getHistory();
        hours = history.get(0);
        minutes = history.get(1);
        seconds = history.get(2);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
