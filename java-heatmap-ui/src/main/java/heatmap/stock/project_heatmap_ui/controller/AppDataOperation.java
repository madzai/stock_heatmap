package heatmap.stock.project_heatmap_ui.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import heatmap.stock.project_heatmap_ui.dto.ChartDTO;
import heatmap.stock.project_heatmap_ui.dto.HeatMapDTO;
import heatmap.stock.project_heatmap_ui.dto.ProfileDTO;

public interface AppDataOperation {
  @CrossOrigin
  @GetMapping(value = "getProfile/{symbol}")
  ProfileDTO getProfile(@PathVariable String symbol);

  @CrossOrigin
  @GetMapping(value = "getChart/{symbol}")
  List<ChartDTO> getChart(@PathVariable String symbol);

  @CrossOrigin
  @GetMapping(value = "getHeatMap")
  List<HeatMapDTO> getHeatMap();

  @CrossOrigin
  @GetMapping(value = "getHeatMapCell/{symbol}")
  HeatMapDTO getHeatMapCell(@PathVariable String symbol);

}
