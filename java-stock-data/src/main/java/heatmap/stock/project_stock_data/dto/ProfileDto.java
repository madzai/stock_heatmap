package heatmap.stock.project_stock_data.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileDto {
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
