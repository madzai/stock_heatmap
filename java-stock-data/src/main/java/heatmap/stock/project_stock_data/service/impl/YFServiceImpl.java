package heatmap.stock.project_stock_data.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import heatmap.stock.project_stock_data.entity.ChartEntity;
import heatmap.stock.project_stock_data.entity.HeatMapEntity;
import heatmap.stock.project_stock_data.entity.ProfileEntity;
import heatmap.stock.project_stock_data.entity.StockEntity;
import heatmap.stock.project_stock_data.repository.ChartRepository;
import heatmap.stock.project_stock_data.repository.HeatMapRepository;
import heatmap.stock.project_stock_data.repository.ProfileRepository;
import heatmap.stock.project_stock_data.repository.StockRepository;
import heatmap.stock.project_stock_data.service.YFService;

@Service
@RequiredArgsConstructor
public class YFServiceImpl implements YFService {
  private final StockRepository stockRepository;

  private final ProfileRepository profileRepository;

  private final ChartRepository chartRepository;

  private final HeatMapRepository heatMapRepository;

  @Override
  public StockEntity getStockBySymbol(String symbol) {
    return stockRepository.findBySymbol(symbol);
  }

  @Override
  public ProfileEntity getProfileBySymbol(String symbol) {
    return profileRepository.findBySymbol(symbol);
  }

  @Override
  public List<ChartEntity> getChartBySymbol(String symbol) {
    return chartRepository.findBySymbol(symbol);
  }

  @Override
  public ChartEntity getChartTdBySymbol(String symbol) {
    return chartRepository.findFirstBySymbolOrderByDateDesc(symbol);
  }

  @Override
  public ChartEntity getChartYtdBySymbol(String symbol) {
    return chartRepository.findSecondLatestBySymbol(symbol);
  }

  @Override
  public List<HeatMapEntity> getWholeHeatMap() {
    return heatMapRepository.findAll();
  }

  @Override
  public HeatMapEntity getHeatMapCell(String symbol) {
    return heatMapRepository.findBySymbol(symbol);
  }
}
