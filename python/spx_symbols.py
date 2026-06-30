from db_functions import save_df_to_db, drop_table
from sqlalchemy import create_engine
from sqlalchemy import String
import pandas as pd
import os
from dotenv import load_dotenv

load_dotenv() 
db_url = os.environ["DATABASE_URL"]
table_stocks = os.environ["TABLE_STOCKS"]

def save_spx_symbols(table_name=table_stocks, conn_str: str = db_url):
  df = pd.read_csv('SP500.csv', usecols=[0, 1, 2, 3])

  # rename columns
  df.columns = ['symbol', 'name', 'sector', 'industry']

  # Replace some deprecated or unreadable symbols
  mapping = {
    'BRK.B': 'BRK-B',
    'BF.B':  'BF-B',
  }
  df['symbol'] = df['symbol'].replace(mapping)

  # Remove GOOG (duplicate symbol for Google)
  df = df[df['symbol'] != 'GOOG']

  drop_table(table_name=table_name)

  save_df_to_db(df=df, table_name=table_name)
  print(f'Saving complete for {table_name}')

def load_spx_symbols(table_name: String = table_stocks, conn_str: str = db_url):
    db_engine = create_engine(conn_str)
    df = pd.read_sql("SELECT * FROM " + table_name, db_engine)
    return df['symbol'].to_list()




