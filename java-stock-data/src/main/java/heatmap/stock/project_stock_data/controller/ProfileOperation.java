package heatmap.stock.project_stock_data.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import heatmap.stock.project_stock_data.dto.ProfileDto;

public interface ProfileOperation {

  @GetMapping(value = "getProfile/{symbol}")
  ProfileDto getProfile(@PathVariable String symbol);

}
