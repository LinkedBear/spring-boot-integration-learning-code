package com.linkedbear.boot.mybatisplus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/sringboot-dao?characterEncoding=utf8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("LinkedBear") // 设置代码的author
                            .outputDir("D://codegenerator"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.linkedbear.boot.mybatisplus") // 设置父包名
                            .moduleName("user") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://codegenerator")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tbl_user") // 设置需要生成的表名
                            .addTablePrefix("tbl_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
