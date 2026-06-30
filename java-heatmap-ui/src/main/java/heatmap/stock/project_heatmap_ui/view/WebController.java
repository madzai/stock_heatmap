package heatmap.stock.project_heatmap_ui.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
  @GetMapping("/")
  public String getHeatMapPage() {
    return "index";
  }

}
