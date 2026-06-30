package heatmap.stock.project_stock_data.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RestController;
import heatmap.stock.project_stock_data.controller.HeatMapOperation;
import heatmap.stock.project_stock_data.dto.ChartDto;
import heatmap.stock.project_stock_data.dto.HeatMapDto;
import heatmap.stock.project_stock_data.entity.ChartEntity;
import heatmap.stock.project_stock_data.entity.HeatMapEntity;
import heatmap.stock.project_stock_data.mapper.DtoMapper;
import heatmap.stock.project_stock_data.service.YFService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HeatMapController implements HeatMapOperation {

  private final YFService yfService;

  private final DtoMapper dtoMapper;

  @Override
  public List<HeatMapDto> getHeatMap() {
    List<HeatMapEntity> heatmapEntities = yfService.getWholeHeatMap();
    return heatmapEntities.stream() //
        .map(e -> dtoMapper.map(e)) //
        .collect(Collectors.toList());
  }

  @Override
  public HeatMapDto getHeatMapCell(String symbol) {
    HeatMapEntity heatmapEntity = yfService.getHeatMapCell(symbol);
    return dtoMapper.map(heatmapEntity);
  }

  @Override
  public List<ChartDto> getChart(String symbol) {
    List<ChartEntity> chartEntities = yfService.getChartBySymbol(symbol);
    return chartEntities.stream() //
        .map(e -> dtoMapper.map(e)) //
        .collect(Collectors.toList());
  }

}
