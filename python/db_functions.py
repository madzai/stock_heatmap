from sqlalchemy import create_engine, text
from typing import Dict, Any

import pandas as pd
import os
from dotenv import load_dotenv

load_dotenv() 
db_url = os.environ["DATABASE_URL"]

def drop_table(table_name: str, conn_str: str = db_url):
    engine = create_engine(conn_str)
    with engine.begin() as conn:
        conn.execute(text(f"DROP TABLE IF EXISTS {table_name}"))

def run_sql(sql_string: str, conn_str: str = db_url):
    engine = create_engine(conn_str)
    with engine.begin() as conn:
        conn.execute(text(f"{sql_string}"))

def save_df_to_db(df: pd.DataFrame, table_name: str, dtype: Dict[str, Any] = None, if_exists: str = 'replace', have_index = True, conn_str: str = db_url):
  db_engine = create_engine(conn_str)
  df.to_sql(
      name=table_name,
      con=db_engine,
      if_exists=if_exists,
      index=have_index, 
      dtype=dtype 
  ) 

