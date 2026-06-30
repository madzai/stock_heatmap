package heatmap.stock.project_stock_data.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChartDto {
  private LocalDate date;
  private String symbol;
  private Long volume;
  private Double close;
  private Double open;
  private Double high;
  private Double low;
}
