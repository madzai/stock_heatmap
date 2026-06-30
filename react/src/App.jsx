import { BrowserRouter, Routes, Route } from 'react-router-dom';
import StockPage from './StockPage';

function App() {
  return (
    <>       
        <BrowserRouter>          
          <Routes>
            <Route path="/stockpage/:symbol" element={<StockPage />} />
          </Routes>
        </BrowserRouter>
    </>
  )
}

export default App
