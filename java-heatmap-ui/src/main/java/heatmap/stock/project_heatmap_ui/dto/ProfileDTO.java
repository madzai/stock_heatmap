package heatmap.stock.project_heatmap_ui.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class ProfileDTO {
  private String symbol;
  private String name;
  private String sector;
  private String industry;
  private String exchange;
  private LocalDate ipo;
  private String logo;
  private String url;
  private Double marketCap;
  private Double shareOS;
  private Double price;
  private Double pcChange;
}
