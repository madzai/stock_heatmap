import './AGCandleVol.css';

import { useEffect, useState } from "react";
import { FinancialChartModule, ModuleRegistry } from "ag-charts-enterprise";
import { AgFinancialCharts } from "ag-charts-react";

ModuleRegistry.registerModules([FinancialChartModule]);

const formatDateOnly = (value) => {
  const date = value instanceof Date ? value : new Date(value);
  if (Number.isNaN(date.getTime())) {
    return "";
  }
  return date
    .toLocaleDateString("en-GB", {
      day: "2-digit",
      month: "short",
      year: "numeric",
    })
    .replace(",", "");
};

function AGCandleVol(props) {
    const [isFullscreen, setIsFullscreen] = useState(false);
    const toggleFullscreen = () => setIsFullscreen(prev => !prev);

    useEffect(() => {
      const style = document.createElement('style');
      if (isFullscreen) {
        style.innerHTML = `html, body, #container { width: 100%; height: 100%; margin: 0; padding: 0; }`;
      } else {
        style.innerHTML = `html, body, #container { width: 100%; height: 800px; margin: 0; padding: 0; }`;
      }
      document.head.appendChild(style);
      return () => document.head.removeChild(style); // Cleanup
    }, [isFullscreen]);
    
    const url = "http://localhost:8102/getChart/" + props.symbol;
    const [backendData, setBackendData] = useState([]);
    const [options, setOptions] = useState({
      data: [],
      title: { text: props.symbol },
    });

    function dataConvertor(data) {
      return {
        "close": data.close,
        "date": new Date(data.date),
        "high": data.high,
        "low": data.low,
        "open": data.open,
        "symbol": data.symbol,
        "volume": data.volume,
      }
    };
    
    useEffect(() => {
        fetch(url)
          .then(response => response.json())
          .then((data) => setBackendData(data))
          .catch(error => console.log("Error fetching Chart: ", error));
    }, [url, props.symbol]);


    useEffect(() => {
        const frontendData = [];
        backendData.forEach((data) => {
          frontendData.push(dataConvertor(data));
        });
        setOptions({
          data: frontendData,
          theme: {
            overrides: {
              common: {
                axes: {
                  "time": {
                    label: {
                      formatter: ({ value }) => formatDateOnly(value),
                    },
                    crosshair: {
                      label: {
                        formatter: ({ value }) => formatDateOnly(value),
                      },
                    },
                  },
                  "ordinal-time": {
                    label: {
                      formatter: ({ value }) => formatDateOnly(value),
                    },
                    crosshair: {
                      label: {
                        formatter: ({ value }) => formatDateOnly(value),
                      },
                    },
                  },
                },
              },
            },
          },
          title: { 
            text: props.symbol,
            fontFamily: "Arial, sans-serif",
            fontSize: 17,
            // fontWeight: 'bold', 
          },
        })
    }, [backendData, props.symbol]);

  return (
    <div className={isFullscreen ? "fullscreen-chart" : "chart-container"}>
      <AgFinancialCharts options={options} />
      <button className="fullscreen-toggle" onClick={toggleFullscreen} aria-label="Toggle fullscreen"></button>     
    </div>
  );
}

export default AGCandleVol
