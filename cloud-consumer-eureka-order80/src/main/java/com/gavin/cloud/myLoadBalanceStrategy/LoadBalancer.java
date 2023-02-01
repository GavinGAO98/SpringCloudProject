package com.gavin.cloud.myLoadBalanceStrategy;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 思想：复用接口编程
 * 并不要求仿照IRule接口的所有方法
 */
public interface LoadBalancer {
    ServiceInstance getBalancedInstance(List<ServiceInstance> serviceInstances);


}
