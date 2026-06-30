package heatmap.stock.project_stock_data.controller.impl;

import org.springframework.web.bind.annotation.RestController;
import heatmap.stock.project_stock_data.controller.ProfileOperation;
import heatmap.stock.project_stock_data.dto.ProfileDto;
import heatmap.stock.project_stock_data.entity.ProfileEntity;
import heatmap.stock.project_stock_data.entity.StockEntity;
import heatmap.stock.project_stock_data.mapper.DtoMapper;
import heatmap.stock.project_stock_data.service.YFService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProfileController implements ProfileOperation {
  private final YFService yfService;

  private final DtoMapper dtoMapper;

  @Override
  public ProfileDto getProfile(String symbol) {
    ProfileEntity profileEntity = yfService.getProfileBySymbol(symbol);
    StockEntity stockEntity = yfService.getStockBySymbol(symbol);
    return dtoMapper.map(stockEntity, profileEntity);
  }
}
