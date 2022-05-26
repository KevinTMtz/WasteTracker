FROM python:3.8.13-slim

# Maintainer info
LABEL maintainer="A01420128@tec.mx"

# Make working directories
RUN  mkdir -p  /wasteid
WORKDIR  /wasteid

# Upgrade pip with no cache
RUN pip install --no-cache-dir -U pip

# Copy application requirements file to the created working directory
COPY requirements.txt .

# Install application dependencies from the requirements file
RUN pip install -r requirements.txt

# Copy every file in the source folder to the created working directory
COPY  . .

# Run the python application
CMD ["python", "main.py"]
