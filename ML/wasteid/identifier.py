from tensorflow import expand_dims
from tensorflow import keras
from numpy import argmax
from PIL import Image
from urllib import request
from io import BytesIO
from waste import CLASSES

def idwaste(url: str, modelpath: str):
    model = keras.models.load_model(modelpath)

    res = request.urlopen(url).read()
    img = Image.open(BytesIO(res)).resize((256,256))
    img_array = keras.preprocessing.image.img_to_array(img)
    img_array = expand_dims(img_array, 0)
    predictions = model.predict(img_array)

    class_type = CLASSES[argmax(predictions)]
    class_type["certainty"] = predictions[0][argmax(predictions)]*100
    return class_type
