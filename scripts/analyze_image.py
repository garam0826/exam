# 파일명: analyze_image.py
import sys
import requests
from PIL import Image
from io import BytesIO
import torch
from transformers import AutoImageProcessor, ConvNextV2ForImageClassification

# 이미지 URL을 명령줄 인자에서 받기
image_url = sys.argv[1]

# 모델 초기화
extractor = AutoImageProcessor.from_pretrained("Pavarissy/ConvNextV2-large-DogBreed")
model = ConvNextV2ForImageClassification.from_pretrained("Pavarissy/ConvNextV2-large-DogBreed")
idx2label = model.config.id2label

def analyze_image(image_url):
    try:
        # 이미지 로드
        response = requests.get(image_url)
        image = Image.open(BytesIO(response.content))

        # 이미지를 모델 입력 형식으로 변환
        inputs = extractor(image, return_tensors="pt")

        # 모델 예측
        with torch.no_grad():
            logits = model(**inputs).logits
            probabilities = torch.softmax(logits, dim=1)

        # 확률 계산 및 상위 3개 클래스 추출
        top_prob, top_cls = probabilities.topk(3)

        # 결과 출력 (CSV 형식)
        for i in range(top_cls.size(1)):
            cls = idx2label[top_cls[0, i].item()]
            prob = top_prob[0, i].item()
            sys.stdout.write(f"{cls},{prob}\n")

    except Exception as e:
        sys.stderr.write(f"An error occurred: {e}\n")
        sys.exit(1)

# 이미지 분석 함수 실행
analyze_image(image_url)
