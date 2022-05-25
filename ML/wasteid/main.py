import os
from fastapi import FastAPI

from identifier import idwaste
from uvicorn import run

app = FastAPI()

@app.get("/identify")
async def root(image: str):
    return idwaste(image, "./model/WasteClassificationModel.h5")

if __name__ == "__main__":
	port = int(os.environ.get('PORT', 8000))
	run(app, host="0.0.0.0", port=port)
