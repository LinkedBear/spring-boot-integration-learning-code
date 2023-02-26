//package com.linkedbear.boot.actuator.health;
//
//import com.linkedbear.boot.xxljob.properties.XxlJobProperties;
//import com.xxl.job.core.biz.AdminBiz;
//import com.xxl.job.core.biz.client.AdminBizClient;
//import com.xxl.job.core.biz.model.RegistryParam;
//import com.xxl.job.core.biz.model.ReturnT;
//import com.xxl.job.core.enums.RegistryConfig;
//import com.xxl.job.core.executor.XxlJobExecutor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.health.AbstractHealthIndicator;
//import org.springframework.boot.actuate.health.Health;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ReflectionUtils;
//
//import java.lang.reflect.Field;
//import java.util.List;
//
//@Component
//public class XxlJobHealthIndicator extends AbstractHealthIndicator {
//
//    @Autowired
//    private XxlJobProperties prop;
//
//    @Override
//    protected void doHealthCheck(Health.Builder builder) throws Exception {
//        RegistryParam registryParam = new RegistryParam(RegistryConfig.RegistType.EXECUTOR.name(), prop.getAppname(),
//                prop.getAddress());
//
//        List<AdminBiz> adminBizList = XxlJobExecutor.getAdminBizList();
//        for (AdminBiz adminBiz : adminBizList) {
//            ReturnT<String> ret = adminBiz.registry(registryParam);
//            if (ret == null || ReturnT.SUCCESS_CODE != ret.getCode()) {
//                builder.down();
//                Field field = ReflectionUtils.findField(AdminBizClient.class, "addressUrl");
//                ReflectionUtils.makeAccessible(field);
//                String addressUrl = (String) ReflectionUtils.getField(field, adminBiz);
//                builder.withDetail("errorAdminServer", addressUrl);
//                return;
//            }
//        }
//        builder.up();
//    }
//}
