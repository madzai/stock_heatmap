const renderChart = (data) => {
  Highcharts.chart("container", {
    chart: {
      backgroundColor: "#252931",
    },
    plotOptions: {
      series: {
        point: {
          events: {
            click: function () {
              if (this.custom?.url) window.open(this.custom?.url, "_blank");
            },
          },
        },
      },
    },
    series: [
      {
        name: "All",
        type: "treemap",
        layoutAlgorithm: "squarified",
        allowTraversingTree: true,
        animationLimit: 1000,
        borderColor: "#252931",
        color: "#252931",
        opacity: 0.01,
        nodeSizeBy: "leaf",
        dataLabels: {
          enabled: false,
          allowOverlap: true,
          style: {
            fontSize: "0.9em",
            textOutline: "none",
          },
        },
        levels: [
          {
            level: 1,
            dataLabels: {
              enabled: true,
              headers: true,
              align: "left",
              style: {
                fontWeight: "bold",
                fontSize: "0.7em",
                lineClamp: 1,
                textTransform: "uppercase",
              },
              padding: 3,
            },
            borderWidth: 3,
            levelIsConstant: false,
          },
          {
            level: 2,
            dataLabels: {
              enabled: true,
              headers: true,
              align: "center",
              shape: "callout",
              backgroundColor: "gray",
              borderWidth: 1,
              borderColor: "#252931",
              padding: 0,
              style: {
                color: "white",
                fontWeight: "normal",
                fontSize: "0.6em",
                lineClamp: 1,
                textOutline: "none",
                textTransform: "uppercase",
              },
            },
            groupPadding: 1,

            // The companies
          },
          {
            level: 3,
            dataLabels: {
              enabled: true,
              align: "center",
              format:
                '{point.name}<br><span style="font-size: 0.7em">' +
                "{point.custom.performance}</span>",
              style: {
                color: "white",
              },
            },
          },
        ],
        accessibility: {
          exposeAsGroupOnly: true,
        },
        breadcrumbs: {
          buttonTheme: {
            style: {
              color: "silver",
            },
            states: {
              hover: {
                fill: "#333",
              },
              select: {
                style: {
                  color: "white",
                },
              },
            },
          },
        },
        data,
      },
    ],
    title: {
      // text: "S&P 500 Companies",
      text: "",
      align: "center",
      style: {
        color: "white",
      },
    },
    subtitle: {
      text: "",
      align: "center",
      style: {
        color: "silver",
      },
    },
    tooltip: {
      outside: true,
      formatter: function () {
        const formatLargeNumber = (value) => {
          const num = Number(value);
          if (!Number.isFinite(num)) return value;
          const abs = Math.abs(num);
          if (abs >= 1e12) return `${(num / 1e12).toFixed(2)}T`;
          if (abs >= 1e9) return `${(num / 1e9).toFixed(2)}B`;
          if (abs >= 1e6) return `${(num / 1e6).toFixed(2)}M`;
          if (abs >= 1e3) return `${(num / 1e3).toFixed(2)}K`;
          return num.toString();
        };

        const fullName = this.point.custom?.fullName || this.point.name;
        const valueText = formatLargeNumber(this.point.value);
        const perfText = this.point.custom?.performance
          ? `<br/><b>Change 1D:</b> ${this.point.custom.performance}`
          : "";
        const priceText = this.point.custom?.price
          ? `<br/><b>Price:</b> ${this.point.custom.price}`
          : "";

        return `
          <span style="font-size: 0.9em">${fullName}</span><br/>
          <b>Market Cap: </b>${valueText}${priceText}${perfText}
        `;
      },
    },
    colorAxis: {
      minColor: "#f73539",
      maxColor: "#2ecc59",
      stops: [
        [0, "#f73539"],
        [0.5, "#414555"],
        [1, "#2ecc59"],
      ],
      min: -10,
      max: 10,
      gridLineWidth: 0,
      labels: {
        overflow: "allow",
        format: "{#gt value 0}+{value}{else}{value}{/gt}%",
        style: {
          color: "white",
        },
      },
    },
    legend: {
      itemStyle: {
        color: "white",
      },
    },
    exporting: {
      sourceWidth: 1200,
      sourceHeight: 800,
      buttons: {
        fullscreen: {
          text: '<i class="fa fa-arrows-alt"></i> Fullscreen',
          onclick: function () {
            this.fullscreen.toggle();
          },
        },
        contextButton: {
          menuItems: [
            "downloadPNG",
            "downloadJPEG",
            "downloadPDF",
            "downloadSVG",
          ],
          text: '<i class="fa fa-share-alt"></i> Export',
          symbol: void 0,
          y: -2,
        },
      },
    },
    navigation: {
      buttonOptions: {
        theme: {
          fill: "#252931",
          style: {
            color: "silver",
            whiteSpace: "nowrap",
          },
          states: {
            hover: {
              fill: "#333",
              style: {
                color: "white",
              },
            },
          },
        },
        symbolStroke: "silver",
        useHTML: true,
        y: -2,
      },
    },
  });
};

(async () => {
  // Plugin for relative font size
  Highcharts.addEvent(Highcharts.Series, "drawDataLabels", function () {
    if (this.type === "treemap") {
      this.points.forEach((point) => {
        // Color the level 2 headers (Industry) with the combined performance of
        // its children (stocks)
        if (point.node.level === 2 && Number.isFinite(point.value)) {
          const previousValue = point.node.children.reduce(
            (acc, child) =>
              acc +
              (child.point.value || 0) -
              ((child.point.value || 0) * (child.point.colorValue || 0)) / 100,
            0,
          );

          // Percentage change from previous value to point.value
          const perf =
            (100 * (point.value - previousValue)) / (previousValue || 1);

          point.custom = {
            performance: (perf < 0 ? "" : "+") + perf.toFixed(2) + "%",
          };

          if (point.dlOptions) {
            point.dlOptions.backgroundColor = this.colorAxis.toColor(perf);
          }
        }

        // Set font size based on area of the point (level 3: stocks)
        if (point.node.level === 3 && point.shapeArgs) {
          const area = point.shapeArgs.width * point.shapeArgs.height;
          point.dlOptions.style.fontSize = `${Math.min(32, 7 + Math.round(area * 0.0008))}px`;
        }
      });
    }
  });

  const response = await fetch("http://localhost:8102/getHeatMap");
  const backendData = await response.json();
  let frontendData = [];

  // Build Sector parent (top level)
  backendData.forEach((item) => {
    const sector = item.sector;
    if (!frontendData.find((point) => point.id === sector))
      frontendData.push({
        id: sector,
      });
  });

  // Build Industry children under Sector parent (2nd level)
  backendData.forEach((item) => {
    const sector = item.sector;
    const industry = item.industry;

    if (!frontendData.find((point) => point.id === industry))
      frontendData.push({
        id: industry,
        parent: sector,
      });
  });

  frontendData.forEach((point) => {
    point.name = point.id;
    point.custom = {
      fullName: point.id,
    };
  });

  // Build Stock node under Industry parent (3rd level)
  backendData.forEach((item) => {
    const sector = item.sector;
    const industry = item.industry;
    const name = item.name;
    const symbol = item.symbol;
    const close = item.close;
    const marketCap = item.marketCap;
    const price = item.price;
    const pcChange = Math.round(item.pcChange * 100) / 100;

    frontendData.push({
      name: symbol, // shown on each cell
      id: symbol,
      value: marketCap, // size of each cell
      parent: industry,
      colorValue: pcChange,
      custom: {
        fullName: name,
        performance: (pcChange < 0 ? "" : "+") + pcChange + "%",
        price: price,
        url: "http://localhost:5174/stockpage/" + symbol,
      },
    });
  });

  renderChart(frontendData);
})();
