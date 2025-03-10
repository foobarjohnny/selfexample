package org.daragon.thread;

public class InterruptTest extends Thread {

    static int result = 0;

    public InterruptTest(String name) {
        super(name);
    }

    public void run() {
        System.out.println(this.getName() + "开始计算...");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println(this.getName() + "被中断,结束");
            return;
        }
        result = (int) (Math.random() * 10000);
        System.out.println(this.getName() + "结束计算");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程执行");
        Thread t = new InterruptTest("计算线程");
        t.start();
        System.out.println("Result:" + result);
        long start = System.nanoTime();
        t.join(2000);
        long end = System.nanoTime();
        t.interrupt();
        System.out.println((end - start) / 1000000 + "毫秒:" + result);
        

    }

}
