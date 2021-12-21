FROM python:3
WORKDIR /usr/datagen/src
COPY ./datagen.py ./
COPY ./machine_list.json ./
COPY ./software_list.json ./
COPY ./requirements.txt ./
RUN pip install -r requirements.txt
#CMD [ "python3", "./data_parser.py"]