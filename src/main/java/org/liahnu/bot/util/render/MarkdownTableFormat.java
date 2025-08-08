package org.liahnu.bot.util.render;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MarkdownTableFormat {

    LEFT_ALIGN("----", "左对齐"),

    CENTER_ALIGN(":--:", "居中对齐"),

    RIGHT_ALIGN("---:", "右对齐");;

    private String format;

    private String description;
}
