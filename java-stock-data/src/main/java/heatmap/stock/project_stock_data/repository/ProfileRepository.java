package heatmap.stock.project_stock_data.repository;

import heatmap.stock.project_stock_data.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
  ProfileEntity findBySymbol(String symbol);
}
