package heatmap.stock.project_heatmap_ui.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import heatmap.stock.project_heatmap_ui.dto.ChartDTO;
import heatmap.stock.project_heatmap_ui.dto.HeatMapDTO;
import heatmap.stock.project_heatmap_ui.dto.ProfileDTO;
import heatmap.stock.project_heatmap_ui.service.AppDataService;

@Service
public class AppDataServiceImpl implements AppDataService {
  @Autowired
  private RestTemplate restTemplate;

  @Override
  public ProfileDTO getProfile(String symbol) {
    String url = "http://stock-data-app:8091/getProfile/" + symbol;
    return this.restTemplate.getForObject(url, ProfileDTO.class);
  }

  @Override
  public List<ChartDTO> getChart(String symbol) {
    String url = "http://stock-data-app:8091/getChart/" + symbol;
    return Arrays.asList(this.restTemplate.getForObject(url, ChartDTO[].class));
  }

  @Override
  public List<HeatMapDTO> getHeatMap() {
    String url = "http://stock-data-app:8091/getHeatMap";
    return Arrays.asList(this.restTemplate.getForObject(url, HeatMapDTO[].class));
  }

  @Override
  public HeatMapDTO getHeatMapCell(String symbol) {
    String url = "http://stock-data-app:8091/getHeatMapCell/" + symbol;
    return this.restTemplate.getForObject(url, HeatMapDTO.class);
  }

}
