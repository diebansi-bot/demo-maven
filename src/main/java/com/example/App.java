package com.example;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 18; i++) { // 18 次 * 10 秒 = 180 秒
            System.out.println("Current time: " + LocalDateTime.now());
            Thread.sleep(5000);
        }
    }
}
