package heatmap.stock.project_stock_data.service;

import java.util.List;
import heatmap.stock.project_stock_data.entity.ChartEntity;
import heatmap.stock.project_stock_data.entity.HeatMapEntity;
import heatmap.stock.project_stock_data.entity.ProfileEntity;
import heatmap.stock.project_stock_data.entity.StockEntity;

public interface YFService {

  StockEntity getStockBySymbol(String symbol);

  ProfileEntity getProfileBySymbol(String symbol);

  List<ChartEntity> getChartBySymbol(String symbol);

  ChartEntity getChartTdBySymbol(String symbol);

  ChartEntity getChartYtdBySymbol(String symbol);

  List<HeatMapEntity> getWholeHeatMap();

  HeatMapEntity getHeatMapCell(String symbol);
}
