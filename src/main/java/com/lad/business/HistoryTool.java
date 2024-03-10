package com.lad.business;

/**
 * @author Andy
 * @date 2024-3-10 010 14:08
 */
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HistoryTool {

    private static final String FILE_NAME = "history.txt";
    private static final String DIRECTORY = System.getProperty("user.dir");
    private static final File HISTORY_FILE = new File(DIRECTORY + File.separator + FILE_NAME);

    /**
     * 记录历史
     */
    public static void recordHistory(Timer timer) {

        String record = String.format("%d,%d,%d", timer.getHours(), timer.getMinutes(), timer.getSeconds());
        try (FileWriter writer = new FileWriter(HISTORY_FILE, false)) { // false表示覆盖原文件
            writer.write(record + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取历史记录
     *
     * @return 历史记录
     */
    public static List<Integer> getHistory() {
        if (!HISTORY_FILE.exists()) {
            return Arrays.asList(0, 0, 0);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                String[] components = line.split(",");
                return Arrays.asList(Integer.parseInt(components[0]), Integer.parseInt(components[1]), Integer.parseInt(components[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(0, 0, 0);
    }



}
