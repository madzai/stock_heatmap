package heatmap.stock.project_stock_data.repository;

import heatmap.stock.project_stock_data.entity.ChartEntity;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository
public interface ChartRepository extends JpaRepository<ChartEntity, Long> {
  List<ChartEntity> findBySymbol(String symbol);

  ChartEntity findFirstBySymbolOrderByDateDesc(String symbol);

  List<ChartEntity> findBySymbolOrderByDateDesc(String symbol, Pageable pageable);

  default ChartEntity findSecondLatestBySymbol(String symbol) {
    List<ChartEntity> page = findBySymbolOrderByDateDesc(symbol, PageRequest.of(1, 1));
    return page.isEmpty() ? null : page.get(0);
  }
}
