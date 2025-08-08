package org.liahnu.bot.util.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkdownTable {

    // 表头数据
    private List<String> header;

    // 表格数据
    private List<List<String>> rows;

    // 行格式
    private Map<Integer, MarkdownTableFormat> rowFormat = new HashMap<>();

    private MarkdownTableFormat defaultFormat = MarkdownTableFormat.CENTER_ALIGN;

    public static MarkdownTable create() {
        return new MarkdownTable();
    }

    /*
     * 设置表头数据
     * @param header 表头数据
     * @return this
     */
    public MarkdownTable setHeader(List<String> header) {
        this.header = header;
        return this;
    }

    /*
     * 添加表头数据
     * @param row 表头数据
     * @return this
     */
    public MarkdownTable addHeader(String row) {
        this.header.add(row);
        return this;
    }

    /*
     * 设置表格数据
     * @param rows 表格数据
     * @return this
     */
    public MarkdownTable setRows(List<List<String>> rows) {
        this.rows = rows;
        return this;
    }

    /*
     * 添加一行数据
     * @param row 行数据
     * @return this
     */
    public MarkdownTable addRow(List<String> row) {
        if (this.rows == null) {
            this.rows = new ArrayList<>();
        }
        this.rows.add(row);
        return this;
    }

    /*
     * 设置行格式
     * @param rowFormat 行格式
     * @return this
     */
    public MarkdownTable setRowFormat(Map<Integer, MarkdownTableFormat> rowFormat) {
        this.rowFormat = rowFormat;
        return this;
    }

    /*
     * 设置行格式
     * @param rowIndex 行索引
     * @param format 行格式
     * @return this
     */
    public MarkdownTable setRowFormat(int rowIndex, MarkdownTableFormat format) {
        this.rowFormat.put(rowIndex, format);
        return this;
    }

    /*
     * 设置默认行格式
     * @param format 行格式
     * @return this
     */
    public MarkdownTable setDefaultFormat(MarkdownTableFormat format) {
        this.defaultFormat = format;
        return this;
    }

    /**
     * 构建markdown表格
     *
     * @return markdown表格字符串
     */
    public String build() {
        StringBuilder sb = new StringBuilder();
        int headerSize = header.size();

        sb.append("|");
        for (String s : header) {
            // 加粗标题
            sb.append(String.format(" **%s** ", s)).append("|");
        }
        sb.append("\n");

        // 行格式
        sb.append("|");
        for (int i = 0; i < headerSize; i++) {
            if (rowFormat.containsKey(i)) {
                sb.append(String.format(" %s ", rowFormat.get(i).getFormat()));
            } else {
                sb.append(String.format(" %s ", defaultFormat.getFormat()));
            }
            sb.append("|");
        }
        sb.append("\n");


        for (List<String> row : rows) {
            if (row.size() != headerSize) {
                throw new IllegalArgumentException("行数据与表头数据不一致");
            }

            sb.append("|");
            for (String s : row) {
                sb.append(String.format(" %s ", s)).append("|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
