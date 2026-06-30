package heatmap.stock.project_stock_data.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spx_heatmap")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeatMapEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "index")
  private Long id;
  private String symbol;
  private String name;
  private String sector;
  private String industry;
  private String logo;
  @Column(name = "marketcap")
  private Double marketCap;
  private LocalDate date;
  private Double price;
  @Column(name = "pctchange")
  private Double pcChange;
}
