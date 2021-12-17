package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @Author czgggggggg
 * @Date 2021/12/17
 * @Description  售票习题
 */
@Slf4j(topic = "c.Test31")
public class Test31 {
    public static void main(String[] args) throws InterruptedException {
        //模拟多人买票
        TicketWindow ticketWindow = new TicketWindow(1000);
        //存储卖出去了多少票
        List<Integer> amountList = new Vector<>();//Vector、线程安全的实现
        //所有线程的集合。要确保最后计算的时候所有的线程都结束。
        List<Thread> threadList = new ArrayList<>();//无需使用线程安全的Vector，因为都是在主线程中运行。

        for(int i = 0; i < 4000; i++){
            Thread thread = new Thread(() -> {
                //买票
                int amount = ticketWindow.sell(randomAmount());
                amountList.add(amount);
            });
            threadList.add(thread);
            thread.start();
        }

        //等待所有的线程执行结束
        for(Thread thread : threadList){
            thread.join();
        }

        //证明  初始票数=卖出票数+余票数
        log.debug("余票数：{}",ticketWindow.getCount());
        log.debug("卖出票数：{}",amountList.stream().mapToInt(i -> i).sum());

    }

    static Random random = new Random();

    public static int randomAmount(){
        return random.nextInt(5) + 1;//返回[1,5]的随机数
    }
}

//售票窗口
class TicketWindow{
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public synchronized int sell(int amount){
        if(this.count >= amount){
            this.count -= amount;
            return amount;
        }
        return 0;
    }
}

//改进前

//10000张票、2000人

//10:38:14.611 c.Test31 [main] - 余票数：4051
//10:38:14.628 c.Test31 [main] - 卖出票数：5949

//10:38:28.203 c.Test31 [main] - 余票数：4032
//10:38:28.218 c.Test31 [main] - 卖出票数：5973

//10:38:39.755 c.Test31 [main] - 余票数：4056
//10:38:39.772 c.Test31 [main] - 卖出票数：5948

//1000张票、4000人

//10:38:58.403 c.Test31 [main] - 余票数：0
//10:38:58.416 c.Test31 [main] - 卖出票数：1000

//10:39:14.917 c.Test31 [main] - 余票数：0
//10:39:14.930 c.Test31 [main] - 卖出票数：1001

//改进后
//public synchronized int sell(int amount)

//10:48:45.460 c.Test31 [main] - 余票数：0
//10:48:45.489 c.Test31 [main] - 卖出票数：1000

//10:49:03.496 c.Test31 [main] - 余票数：0
//10:49:03.511 c.Test31 [main] - 卖出票数：1000

//10:49:17.480 c.Test31 [main] - 余票数：0
//10:49:17.501 c.Test31 [main] - 卖出票数：1000