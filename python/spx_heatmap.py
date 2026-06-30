from db_functions import drop_table, run_sql 
import os
from dotenv import load_dotenv

load_dotenv() 
db_url = os.environ["DATABASE_URL"]

table_stocks = os.environ["TABLE_STOCKS"]
table_profile = os.environ["TABLE_PROFILE"]
table_chart = os.environ["TABLE_CHART"]
table_growth = os.environ["TABLE_GROWTH"]
table_market_cap = os.environ["TABLE_MARKET_CAP"]
table_heatmap = os.environ["TABLE_HEATMAP"]

def save_growth_table(table_name = table_growth):
    drop_table(table_name)

    create_table_growth_sql = f"""
    CREATE TABLE IF NOT EXISTS {table_name} AS (
        WITH td AS (
            WITH today AS (
                SELECT         
                    symbol,
                    date as date_td,
                    close as close_td,
                    ROW_NUMBER() OVER (PARTITION BY symbol ORDER BY date DESC) as rnTd
                FROM {table_chart}
            )
            SELECT *
            FROM today
            WHERE rnTd = 1
            ORDER BY symbol
        ),
        ytd AS (
            WITH yesterday AS (
            SELECT         
                symbol,
                date as date_ytd,
                close as close_ytd,
                ROW_NUMBER() OVER (PARTITION BY symbol ORDER BY date DESC) as rnYtd
            FROM {table_chart}
            )
            SELECT *
            FROM yesterday
            WHERE rnYtd = 2
            ORDER BY symbol
        )
        SELECT 
            td.symbol,
            td.date_td,
            ytd.date_ytd, 
            td.close_td, 
            ytd.close_ytd,
            ((td.close_td - ytd.close_ytd) / ytd.close_ytd * 100) AS pct_change
        FROM td JOIN ytd
        ON td.symbol = ytd.symbol
        );
    """

    run_sql(sql_string=create_table_growth_sql)

    print(f'Saving complete for database table {table_name}')

def save_heatmap_table(table_name = table_heatmap):
    drop_table(table_name)

    create_table_heatmap_sql = f"""
    CREATE TABLE IF NOT EXISTS {table_name} AS (
        SELECT  
            stocks.index,
            stocks.symbol,
            stocks.name,
            stocks.sector,
            stocks.industry,
            profile.logo,
            growth.date_td as date,
            growth.close_td as price,
            growth.pct_change as pctchange,
            marketCapitalization.market_cap as marketcap
        FROM
        {table_stocks} stocks 
        JOIN {table_profile} profile 
        ON stocks.symbol = profile.symbol
        JOIN {table_growth} growth
        ON growth.symbol = stocks.symbol
        JOIN {table_market_cap} marketCapitalization
        ON marketCapitalization.symbol = stocks.symbol
    );
    """

    run_sql(sql_string=create_table_heatmap_sql)

    print(f'Saving complete for database table {table_name}')

