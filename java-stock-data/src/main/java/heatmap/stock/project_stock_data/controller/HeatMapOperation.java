package heatmap.stock.project_stock_data.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import heatmap.stock.project_stock_data.dto.ChartDto;
import heatmap.stock.project_stock_data.dto.HeatMapDto;

public interface HeatMapOperation {

  @GetMapping(value = "getHeatMapCell/{symbol}")
  HeatMapDto getHeatMapCell(@PathVariable String symbol);

  @GetMapping(value = "getHeatMap")
  List<HeatMapDto> getHeatMap();

  @GetMapping(value = "getChart/{symbol}")
  List<ChartDto> getChart(@PathVariable String symbol);
}
