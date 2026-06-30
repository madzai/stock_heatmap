import yfinance as yf
from sqlalchemy.types import DECIMAL, String
from sqlalchemy import Date, BigInteger
import pandas as pd
import numpy as np
from db_functions import save_df_to_db, drop_table
from spx_symbols import load_spx_symbols
import os
from dotenv import load_dotenv

load_dotenv() 
db_url = os.environ["DATABASE_URL"]
table_chart = os.environ["TABLE_CHART"]
table_market_cap = os.environ["TABLE_MARKET_CAP"]    

def save_yfin_chart_data(symbol, period="1y", table_name=table_chart, if_exists='append'):
  ticker = yf.Ticker(symbol)

  historical_data = ticker.history(period=period) 

  # Convert index (Date) to a column
  historical_data = historical_data.reset_index()

  # Insert a column of stock symbol
  historical_data.insert(0, 'Symbol', symbol)

  historical_data = historical_data.round({'High': 2, 'Low': 2, 'Open': 2, 'Close': 2})

  historical_data = historical_data[['Symbol', 'Date', 'Volume', 'Close', 'Open', 'High', 'Low']]
  col = ['Symbol', 'Date', 'Volume', 'Close', 'Open', 'High', 'Low']
  rename = {
      'Symbol': 'symbol',
      'Date': 'date',
      'Volume': 'volume',
      'Close': 'close',
      'Open': 'open',
      'High': 'high',
      'Low': 'low'
  }  
  historical_data = historical_data[col].rename(columns=rename)

  dtypes = {
    'symbol' : String(10),
    'date' : Date,
    'volume' : BigInteger, 
    'close' : DECIMAL(8,2), 
    'open' : DECIMAL(8,2),
    'high' : DECIMAL(8,2), 
    'low' : DECIMAL(8,2)
  } 

  save_df_to_db(df=historical_data, table_name=table_name, dtype=dtypes, have_index=True, if_exists=if_exists)

def save_spx_yfin_chart_data(period="2y", table_name=table_chart, if_exists='append', conn_str: str = db_url):  
  drop_table(table_name = table_name)
  
  stocks = load_spx_symbols()
  tickers_not_found = []
  for stock in stocks:
    try:
      save_yfin_chart_data(symbol=stock, period=period, table_name=table_name, if_exists=if_exists)
    except:
      print(f'Error with ticker {stock}')
      tickers_not_found.append(stock)
  print(f'Saving complete for {table_name}')
  if(len(tickers_not_found) > 0): 
    print(tickers_not_found)
  else:
    print("All stocks are saved successfully")

def get_market_cap(symbol):
  ticker = yf.Ticker(symbol)
  info = ticker.info
  market_cap = info.get('marketCap', np.nan)
  return market_cap

def save_yfin_market_cap(table_name=table_market_cap, if_exists='append'):
  drop_table(table_name = table_name)
  
  stocks = load_spx_symbols()
  tickers_not_found = []
  marketCap_list = []
  for stock in stocks:
    try:
      market_cap = get_market_cap(stock)
      marketCap_list.append({"symbol": stock, "market_cap": market_cap})
    except:
      print(f'Error with ticker {stock}')
      tickers_not_found.append(stock)
  df_marketCap = pd.DataFrame(marketCap_list)
  save_df_to_db(df=df_marketCap, table_name=table_name)
  print(f'Saving complete for {table_name}')
  if(len(tickers_not_found) > 0): 
    print(tickers_not_found)
  else:
    print("All stocks are saved successfully")

