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
@Table(name = "spx_chart")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChartEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "index")
  private Long id;
  private LocalDate date;
  private String symbol;
  private Long volume;
  private Double close;
  private Double open;
  private Double high;
  private Double low;
}
