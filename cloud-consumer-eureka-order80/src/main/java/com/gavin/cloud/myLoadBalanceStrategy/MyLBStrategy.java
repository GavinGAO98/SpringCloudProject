package com.gavin.cloud.myLoadBalanceStrategy;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component //将该负载均衡策略交给Spring容器管理
public class MyLBStrategy implements LoadBalancer{

    private AtomicInteger at = new AtomicInteger(0);

    /**
     * 计算同一个服务调用的次数，没有调用时次数为0。服务重启后次数归零。
     * 并发时为了避免加锁带来的性能下降，使用原子类AtomicInteger提供的CAS方法【仿照自旋锁思路】
     * @return
     */
    public int getAndIncrement(){

        //Q：如何模拟并发下的情景？
        //A：【明晰哪个是共享变量，那个是局部变量，并假设不同的线程同时开始执行，但实际上一定会执行到程序的不同位置】
        // 并发下某一线程继续执行do循环体，此时将线程内的局部变量current赋值为at当前值，但是如果已经有其他线程执行CAS，则CAS过程必须等待其他线程执行完毕
        int current;
        int next;

        //for( ; ;) {
        //    current = at.get(); //获取原子变量的值
        //    next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        //    if(at.compareAndSet(current, next)){
        //      return next;
        //    }
        //    break这样写结构太丑
        //}
        do{
            current = this.at.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        }while(!at.compareAndSet(current, next)); //如果at值等于期望值current，则将next赋值给at，并返回true；否则返回false
        System.out.println("--------------第" + next + "次访问");
        return next;
    }


    /**
     * 获取当前调用服务的具体实例
     * @param serviceInstances 注册在服务中心的同一个服务的所有实例列表
     * @return 根据该服务调用次数负载均衡确定的调用实例。 负载均衡规则： 调用次数 % 服务实例数量
     */
    @Override
    public ServiceInstance getBalancedInstance(List<ServiceInstance> serviceInstances){
        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }


}
