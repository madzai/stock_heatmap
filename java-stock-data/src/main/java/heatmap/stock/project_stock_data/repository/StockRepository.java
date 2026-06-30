package heatmap.stock.project_stock_data.repository;

import heatmap.stock.project_stock_data.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
  StockEntity findBySymbol(String symbol);
}
