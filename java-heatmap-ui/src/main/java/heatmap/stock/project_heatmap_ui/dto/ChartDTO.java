package heatmap.stock.project_heatmap_ui.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class ChartDTO {
  private LocalDate date;
  private String symbol;
  private Long volume;
  private Double close;
  private Double open;
  private Double high;
  private Double low;

}
