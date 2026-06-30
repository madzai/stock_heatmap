import { useParams } from 'react-router-dom';

import AGCandleVol from './AGCandleVol';
import StockHeader from './StockHeader';

function StockPage() {
  const { symbol } = useParams();

  return (
    <>      
      <StockHeader symbol={symbol} />
      <AGCandleVol symbol={symbol} />
    </>    
  ) 
}

export default StockPage
