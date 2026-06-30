import { useEffect, useState } from 'react'
import './StockHeader.css'

function StockHeader(props) {
  const [profile, setProfile] = useState({});
  const [heatmap, setHeatmap] = useState({});

  useEffect(() => {
    
    fetch("http://localhost:8102/getProfile/" + props.symbol)
      .then(response => response.json())
      .then(data => setProfile(data))
      .catch(error => console.log("Error fetching Profile: ", error));
    
    fetch("http://localhost:8102/getHeatMapCell/" + props.symbol)
      .then(response => response.json())
      .then(data => setHeatmap(data))
      .catch(error => console.log("Error fetching HeatMap: ", error));

  }, [props.symbol]);

  return (
    <div className="stock-header">
      <div className="stock-logo">
        <img src={profile.logo} alt={profile.symbol} />
      </div>
      <div className="stock-info">
        <div className="stock-symbol">{profile.name}</div>
        <br />
        <div className="stock-price-container">
          <span className="stock-price">${heatmap.price}</span>
          {heatmap.pcChange !== undefined && (
            <span className={`stock-change ${heatmap.pcChange >= 0 ? 'positive' : 'negative'}`}>
              {heatmap.pcChange >= 0 ? '+' : ''}{(heatmap.pcChange).toFixed(2)}%
            </span>
          )}
        </div>
      </div>
    </div>
  )
}

export default StockHeader
