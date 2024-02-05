import requests
from PIL import Image
from io import BytesIO
import torch
from transformers import AutoImageProcessor, ConvNextV2ForImageClassification
import numpy as np
from sklearn.decomposition import PCA
import matplotlib.pyplot as plt
import json


# 확률 데이터를 저장할 리스트
probabilities_list = []

# 모델 초기화
extractor = AutoImageProcessor.from_pretrained("Pavarissy/ConvNextV2-large-DogBreed")
model = ConvNextV2ForImageClassification.from_pretrained("Pavarissy/ConvNextV2-large-DogBreed")
idx2label = model.config.id2label

# API 호출
url = 'https://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?serviceKey=XoUKvRBvuJnQoblCd23mVs4%2F4%2FwBHivSh8CpK47qsUpJViqLxxEjo2BOEF18WpLUqBID%2FxnnJMNq8NdiDf83pw%3D%3D&bgnde=20230101&endde=20240114&upkind=417000&kind=000114&upr_cd=6110000&org_cd=3220000&care_reg_no=311322200900001&pageNo=1&numOfRows=10&_type=json'  
response = requests.get(url)
data = response.json()

# popfile 링크 추출
items = data['response']['body']['items']['item']
popfile_links = [item['popfile'] for item in items]



# 분석 결과를 백엔드에 전송
def send_to_backend(image_url, predictions):
    url = "http://localhost:8080/api/predictions"
    headers = {'Content-Type': 'application/json'}
    data = json.dumps({"imageUrl": image_url, "predictions": predictions})
    response = requests.post(url, headers=headers, data=data)
    print(response.text)





# 각 이미지에 대해 모델 적용
for image_url in popfile_links:
    response = requests.get(image_url)
    image = Image.open(BytesIO(response.content))

    # 이미지를 모델 입력 형식으로 변환
    inputs = extractor(image, return_tensors="pt")

    # 모델 예측
    with torch.no_grad():
        logits = model(**inputs).logits
        probabilities = torch.softmax(logits, dim=1)
        probabilities_list.append(probabilities[0].numpy())  # 확률 데이터 추가

    # 확률 계산 및 상위 3개 클래스 추출
    top_prob, top_cls = probabilities.topk(3)

    # # 결과 출력
    # print(f"Image URL: {image_url}")
    # for i in range(3):
    #     cls = idx2label[top_cls[0, i].item()]
    #     prob = top_prob[0, i].item()
    #     print(f"  {i+1}: {cls} with probability {prob:.4f}")
    # print("\n")

    # 결과를 JSON 형식으로 출력
    top_predictions = []
    for i in range(3):
        cls = idx2label[top_cls[0, i].item()]
        prob = top_prob[0, i].item()
        top_predictions.append({"className": cls, "probability": prob})

    # 이미지 분석 후 결과를 전송하는 코드 부분
    send_to_backend(image_url, top_predictions)

    #print문 일단 생략
    #print(json.dumps({"image_url": image_url, "predictions": top_predictions}))
    

    # with open('words.json','w',encoding="utf-8") as make_file:
    #     json.dump(file_Data, make_file,ensure_ascii=False, indent="\t")



