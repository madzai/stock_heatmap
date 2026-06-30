package heatmap.stock.project_stock_data.mapper;

import org.springframework.stereotype.Component;
import heatmap.stock.project_stock_data.dto.ChartDto;
import heatmap.stock.project_stock_data.dto.HeatMapDto;
import heatmap.stock.project_stock_data.dto.ProfileDto;
import heatmap.stock.project_stock_data.entity.ChartEntity;
import heatmap.stock.project_stock_data.entity.HeatMapEntity;
import heatmap.stock.project_stock_data.entity.ProfileEntity;
import heatmap.stock.project_stock_data.entity.StockEntity;

@Component
public class DtoMapper {

  public HeatMapDto map(HeatMapEntity heatMapEntity) {
    return HeatMapDto.builder() //
        .symbol(heatMapEntity.getSymbol()) //
        .name(heatMapEntity.getName()) //
        .sector(heatMapEntity.getSector()) //
        .industry(heatMapEntity.getIndustry()) //
        .logo(heatMapEntity.getLogo()) //
        .marketCap(heatMapEntity.getMarketCap()) //
        .date(heatMapEntity.getDate()) //
        .price(heatMapEntity.getPrice()) //
        .pcChange(heatMapEntity.getPcChange()) //
        .build();
  }

  public ChartDto map(ChartEntity chartEntity) {
    return ChartDto.builder() //
        .date(chartEntity.getDate()) //
        .symbol(chartEntity.getSymbol()) //
        .volume(chartEntity.getVolume()) //
        .close(chartEntity.getClose()) //
        .open(chartEntity.getOpen()) //
        .high(chartEntity.getHigh()) //
        .low(chartEntity.getLow()) //
        .build();
  }

  public ProfileDto map(StockEntity stockEntity, ProfileEntity profileEntity) {
    return ProfileDto.builder() //
        .symbol(stockEntity.getSymbol()) //
        .name(stockEntity.getName()) //
        .sector(stockEntity.getSector()) //
        .industry(stockEntity.getIndustry()) //
        .exchange(profileEntity.getExchange()) //
        .ipo(profileEntity.getIpo()) //
        .logo(profileEntity.getLogo()) //
        .url(profileEntity.getUrl()) //
        .marketCap(profileEntity.getMarketCap()) //
        .shareOS(profileEntity.getShareOS()) //
        .build();
  }
}
