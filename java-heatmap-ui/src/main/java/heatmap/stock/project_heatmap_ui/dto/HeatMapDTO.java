package heatmap.stock.project_heatmap_ui.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class HeatMapDTO {
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
