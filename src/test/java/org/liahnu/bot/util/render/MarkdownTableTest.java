package org.liahnu.bot.util.render;

import cn.hutool.core.codec.Base64;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MarkdownTableTest {

    private static final Logger log = LoggerFactory.getLogger(MarkdownTableTest.class);

    @Test
    public void buildTest() {
        String build = MarkdownTable.create()
                .setHeader(List.of("姓名", "年龄", "性别"))
                .setDefaultFormat(MarkdownTableFormat.LEFT_ALIGN)
                .addRow(List.of("张三", "18", "男"))
                .addRow(List.of("李四", "20", "女"))
                .addRow(List.of("王五", "22", "男"))
                .build();

        log.info(build);
        assertEquals("""
                | **姓名** | **年龄** | **性别** |
                | ---- | ---- | ---- |
                | 张三 | 18 | 男 |
                | 李四 | 20 | 女 |
                | 王五 | 22 | 男 |
                """, build);
    }

    @Test
    public void buildTableImage() {
        String markdown = MarkdownTable.create()
                .setHeader(List.of("姓名", "年龄", "性别"))
                .setDefaultFormat(MarkdownTableFormat.CENTER_ALIGN)
                .addRow(List.of("张三", "18", "男"))
                .addRow(List.of("李四", "20", "女"))
                .addRow(List.of("王五", "22", "男"))
                .build();
        String image = MarkdownRender.renderMarkdownWithImage(markdown);
        File imageFile = new File("./test.png");
        byte[] bytes = Base64.decode(image);
        try {
            Files.write(imageFile.toPath(), bytes);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        assertNotNull(image);
    }

}