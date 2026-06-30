package heatmap.stock.project_stock_data.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HeatMapDto {
  private String symbol;
  private String name;
  private String sector;
  private String industry;
  private String logo;
  private Double marketCap;
  private LocalDate date;
  private Double price;
  private Double pcChange;
}
