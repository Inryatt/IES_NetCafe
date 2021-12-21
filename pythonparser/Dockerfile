FROM python:3
WORKDIR /usr/parser/src
COPY ./data_parser.py ./
COPY ./requirements.txt ./
RUN pip install -r requirements.txt
#CMD [ "python3", "./data_parser.py"]