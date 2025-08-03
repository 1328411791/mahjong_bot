package org.liahnu.bot.util.render;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class MarkdownRenderTest {

    Logger logger = LoggerFactory.getLogger(MarkdownRenderTest.class);

    @Test
    void renderMarkdown() {
        String markdown = """
                | 姓名 | 年龄 | 性别 |
                | --- | --- | --- |
                | 张三 | 18 | 男 |
                | 李四 | 20 | 女 |
                | 王五 | 22 | 男 |
                """;
        String html = MarkdownRender.renderMarkdown(markdown);
        logger.info(html);
        Assert.isNull(html);
    }

    @Test
    void renderMarkdownWithTablesImage() {
        String markdown = """
                | 姓名 | 年龄 | 性别 |
                | --- | --- | --- |
                | 张三 | 18 | 男 |
                | 李四 | 20 | 女 |
                | 王五 | 22 | 男 |
                """;
        byte[] image = MarkdownRender.renderMarkdownWithTablesImage(markdown);
        // 如果在本地测试运行
//        File file = new File("./img.png");
//        try {
//            Files.write(file.toPath(), html);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
        Assert.isNull(image);
    }


}