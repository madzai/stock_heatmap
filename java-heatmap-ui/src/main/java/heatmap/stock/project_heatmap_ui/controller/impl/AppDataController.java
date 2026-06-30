package heatmap.stock.project_heatmap_ui.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import heatmap.stock.project_heatmap_ui.controller.AppDataOperation;
import heatmap.stock.project_heatmap_ui.dto.ChartDTO;
import heatmap.stock.project_heatmap_ui.dto.HeatMapDTO;
import heatmap.stock.project_heatmap_ui.dto.ProfileDTO;
import heatmap.stock.project_heatmap_ui.service.AppDataService;

@RestController
public class AppDataController implements AppDataOperation {
  @Autowired
  private AppDataService appDataService;

  @Override
  public ProfileDTO getProfile(String symbol) {
    return this.appDataService.getProfile(symbol);
  }

  @Override
  public List<ChartDTO> getChart(String symbol) {
    return this.appDataService.getChart(symbol);
  }

  @Override
  public List<HeatMapDTO> getHeatMap() {
    return this.appDataService.getHeatMap();
  }

  @Override
  public HeatMapDTO getHeatMapCell(String symbol) {
    return this.appDataService.getHeatMapCell(symbol);
  }
}
