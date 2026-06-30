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
@Table(name = "spx_profile")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProfileEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "index")
  private Long id;
  private String symbol;
  @Column(name = "market capitalization")
  private Double marketCap;
  @Column(name = "shares outstanding")
  private Double shareOS;
  private String logo;
  private String exchange;
  private LocalDate ipo;
  private String url;
}
