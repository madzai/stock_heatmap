package heatmap.stock.project_heatmap_ui.service;

import java.util.List;
import heatmap.stock.project_heatmap_ui.dto.ChartDTO;
import heatmap.stock.project_heatmap_ui.dto.HeatMapDTO;
import heatmap.stock.project_heatmap_ui.dto.ProfileDTO;

public interface AppDataService {
  ProfileDTO getProfile(String symbol);

  List<ChartDTO> getChart(String symbol);

  List<HeatMapDTO> getHeatMap();

  HeatMapDTO getHeatMapCell(String symbol);
}
