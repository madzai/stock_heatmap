if type deactivate &> /dev/null; then
  echo "🔁 Deactivating enviroment..."
  deactivate
fi

if [ -d "heatmap-env" ]; then
  echo "🔁 Removing old virtual environment (if any)..."
  rm -rf heatmap-env
fi

echo "📦 Creating new virtual environment..."
python3 -m venv heatmap-env

echo "✅ Activating virtual environment..."
source heatmap-env/Scripts/activate

python -m pip install --upgrade pip
pip install -r requirements.txt

python -m ipykernel install --user --name=heatmap-env --display-name "Python (heatmap-env)"

# python --version
pip --version
python --version
jupyter --version

echo "✅ Environment Setup complete."