package org.lecoder.easyflow.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * js脚本引擎配置
 * @author: lijile
 * @date: 2021/10/25 19:30
 * @version: 1.0
 */
@Configuration
public class ScriptEngineConfig {

    @Bean
    public ScriptEngine scriptEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        return engine;
    }

}
