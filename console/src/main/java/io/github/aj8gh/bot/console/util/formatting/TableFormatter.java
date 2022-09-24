package io.github.aj8gh.bot.console.util.formatting;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;

public class TableFormatter {

  private final TableHeaders tableHeaders;
  private int shellWidth;

  public TableFormatter(TableHeaders tableHeaders, int shellWidth) {
    this.tableHeaders = tableHeaders;
    this.shellWidth = shellWidth;
  }

  public <T> String format(List<T> entities) {
    var headers = new LinkedHashMap<>(getHeaders(entities));
    var table = new BeanListTableModel<>(entities, headers);
    var tableBuilder = new TableBuilder(table);
    tableBuilder.addInnerBorder(BorderStyle.fancy_light);
    tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
    return tableBuilder.build().render(shellWidth);
  }

  public void setShellWidth(int shellWidth) {
    this.shellWidth = shellWidth;
  }

  private <T> Map<String, Object> getHeaders(List<T> entities) {
    if (entities.isEmpty()) {
      throw new IllegalArgumentException("Empty list");
    }
    var headers = tableHeaders.get(entities.get(0).getClass());
    if (headers == null) {
      throw new NullPointerException("Header not found");
    }
    return headers;
  }
}
