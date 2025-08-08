package org.liahnu.bot.util.render;

import cn.hutool.core.codec.Base64;
import gui.ava.html.image.generator.HtmlImageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class MarkdownRender {

    /*
    渲染markdown为html
    @param markdown markdown字符串
    @return html字符串
     */
    public static String renderMarkdown(String markdown) {
        // 创建解析器，添加表格扩展
        List<Extension> extensions = List.of(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(extensions)
                .build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(extensions)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new TableBorderAttributeProvider();
                    }
                })
                .build();
        return renderer.render(document);
    }

    /*
    渲染markdown表格为图片
    @param markdown markdown字符串
    @return 图片base64字符串
     */
    public static String renderMarkdownWithImage(String markdown) {

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
        return Base64.encode(outputStream.toByteArray());
    }

    // 自定义AttributeProvider，为表格添加边框样式
    static class TableBorderAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if ("table".equals(tagName)) {
                attributes.put("style", "border-collapse: collapse; border-spacing: 0;");
            } else if ("th".equals(tagName) || "td".equals(tagName)) {
                attributes.put("style", "border: 1px solid #000; padding: 8px; margin: 0;");
            }
        }
    }
}
