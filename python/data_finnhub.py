import pandas as pd
import os
from dotenv import load_dotenv
from datetime import datetime, date
import finnhub
from sqlalchemy.types import DECIMAL, JSON, DateTime, Date, String, BigInteger
import time
from sqlalchemy import create_engine, text
from db_functions import save_df_to_db, drop_table
from spx_symbols import load_spx_symbols

load_dotenv() 
finnhub_client = finnhub.Client(api_key=os.environ["FINNHUB_API_KEY"])
db_url = os.environ["DATABASE_URL"]
table_profile = os.environ["TABLE_PROFILE"]

# rest for t_rest seconds after every n_rest finnhub API calls
n_rest = 59
t_rest = 48

def stock_profile(symbol):
  company_profile = finnhub_client.company_profile2(symbol=symbol)
  logo, exchange, ipo, weburl = company_profile['logo'], company_profile['exchange'], company_profile['ipo'], company_profile['weburl']
  return logo, exchange, ipo, weburl
  
def save_spx_stock_profile(table_name=table_profile, conn_str=db_url):
  df_stocks = pd.DataFrame(columns=['symbol', 'market capitalization', 'shares outstanding', 'logo', 'exchange', 'ipo', 'url'])
  stocks = load_spx_symbols()
  n = len(stocks)
  tickers_with_error = []
  for i in range(n):
    stock = stocks[i]
    print(f"{i+1}: {stock}")
    try:
      logo, exchange, ipo, weburl = stock_profile(stock)
      stock_data = {                  
        'symbol' : stock,
        'logo' : logo,
        'exchange' : exchange,
        'ipo' : ipo,
        'url' : weburl
      }
      df_stocks.loc[len(df_stocks)] = stock_data
    except:
      print(f'Error with ticker {stock}')
      tickers_with_error.append(stock)
    if i > 0 and i % n_rest == 0:
      time.sleep(48)

  if(len(tickers_with_error) > 0):
    print(tickers_with_error)

  dtypes = {
    'symbol' : String(10),
    'logo' : String(255),
    'exchange' : String(100),
    'ipo' : Date,
    'url' : String(255)
  }

  drop_table(table_name)

  save_df_to_db(df=df_stocks, table_name=table_name, dtype=dtypes)
  print(f'Saving complete for database table {table_name}')



