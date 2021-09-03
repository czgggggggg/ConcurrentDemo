package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test18")
public class Test18 {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();

        Thread.sleep(3500);
        tpt.stop();
    }
}

@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination{
    private Thread monitor;

    //启动监控线程
    public void start(){
        monitor = new Thread(() -> {
            while(true){
                Thread current = Thread.currentThread();
                //被打断，则处理后续，接着退出。
                if(current.isInterrupted()){
                    log.debug("处理后续");
                    break;
                }
                //没有被打断，睡眠1秒，判断是否有异常。
                try {
                    Thread.sleep(1000);//情况1：此处被打断，则抛出异常（打断标记被清除），进入异常处理逻辑。
                    log.debug("执行监控记录");//情况2：此处被打断，是正常被打断（已经有打断标记），重新会到循环。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    current.interrupt();//因为是抛出异常的情况，打断标记被清除，则重新生成打断标记。这样重新回到循环后，就可以判断线程被打断，然后进入处理后续的阶段。
                }
            }
        });
        monitor.start();
    }

    //停止监控线程
    public void stop(){
        monitor.interrupt();
    }
}

//有重新生成打断标记这个步骤的测试结果：
//（可以看见抛出sleep阶段被打断且抛出异常后，因为重新生成了打断标记，所以可以继续后续处理后。）
//18:58:40.627 c.TwoPhaseTermination [Thread-0] - 执行监控记录
//18:58:41.638 c.TwoPhaseTermination [Thread-0] - 执行监控记录
//18:58:42.647 c.TwoPhaseTermination [Thread-0] - 执行监控记录
//java.lang.InterruptedException: sleep interrupted
//	at java.lang.Thread.sleep(Native Method)
//	at com.bupt.test.TwoPhaseTermination.lambda$start$0(Test18.java:37)
//	at java.lang.Thread.run(Thread.java:748)
//18:58:43.125 c.TwoPhaseTermination [Thread-0] - 处理后续

//没有重新生成打断标记这个步骤的测试结果：
//因为没有重新生成打断标记（打断标记已经被清除），所以线程无法继续后续处理。
//18:59:02.561 c.TwoPhaseTermination [Thread-0] - 执行监控记录
//18:59:03.577 c.TwoPhaseTermination [Thread-0] - 执行监控记录
//18:59:04.588 c.TwoPhaseTermination [Thread-0] - 执行监控记录
//java.lang.InterruptedException: sleep interrupted
//	at java.lang.Thread.sleep(Native Method)
//	at com.bupt.test.TwoPhaseTermination.lambda$start$0(Test18.java:37)
//	at java.lang.Thread.run(Thread.java:748)
//18:59:06.080 c.TwoPhaseTermination [Thread-0] - 执行监控记录
//18:59:07.091 c.TwoPhaseTermination [Thread-0] - 执行监控记录
//18:59:08.106 c.TwoPhaseTermination [Thread-0] - 执行监控记录
//18:59:09.119 c.TwoPhaseTermination [Thread-0] - 执行监控记录
//18:59:10.134 c.TwoPhaseTermination [Thread-0] - 执行监控记录
//18:59:11.136 c.TwoPhaseTermination [Thread-0] - 执行监控记录