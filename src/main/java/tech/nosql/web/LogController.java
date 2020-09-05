package tech.nosql.web;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 日志写入测试
 *
 * @author faith.huan 2020-09-03 22:44
 */
@Slf4j
@RestController
public class LogController {


    /**
     * 接收一个val,如果val是int,则打印info日志;如果不是int,则打印异常日志
     *
     * @param val 参数
     * @return int or not
     */
    @GetMapping("/log/{val}")
    public String log(@PathVariable("val") String val) {
        // 设置TID,用于追踪一次请求,可以使用拦截器实现,此处仅供演示用
        String tid = UUID.randomUUID().toString();
        MDC.put("TID", tid);
        try {
            int intVal = Integer.parseInt(val);
            log.info("intVal:{}", intVal);
            return "输入的是int值,tid:" + tid;
        } catch (Exception e) {
            log.error("发生异常", e);
            return "输入的不是int值tid:" + tid;
        } finally {
            MDC.clear();
        }
    }

}
