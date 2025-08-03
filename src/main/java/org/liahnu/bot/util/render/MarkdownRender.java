package org.liahnu.bot.util.render;

import gui.ava.html.image.generator.HtmlImageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class MarkdownRender {

    public static String renderMarkdown(String markdown) {
        // 创建解析器，添加表格扩展
        List<Extension> extensions = List.of(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(extensions)
                .build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(extensions)
                .build();
        return renderer.render(document);
    }

    public static byte[] renderMarkdownWithTablesImage(String markdown) {
        // 校验是否包含表格
        if (!markdown.contains("|")) {
            throw new IllegalArgumentException("markdown 不包含表格");
        }

        // 创建解析器，添加表格扩展
        String html = renderMarkdown(markdown);
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        imageGenerator.loadHtml(html);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedImage image = imageGenerator.getBufferedImage();
        try {
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return outputStream.toByteArray();
    }
}
