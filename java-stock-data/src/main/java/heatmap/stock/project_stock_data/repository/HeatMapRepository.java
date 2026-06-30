package heatmap.stock.project_stock_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import heatmap.stock.project_stock_data.entity.HeatMapEntity;

public interface HeatMapRepository extends JpaRepository<HeatMapEntity, Long> {
  HeatMapEntity findBySymbol(String symbol);
}
