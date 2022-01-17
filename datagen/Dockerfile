FROM python:3
WORKDIR /usr/datagen/src
COPY ./datagen.py ./
COPY ./machine_list.json ./
COPY ./software_list.json ./
COPY ./requirements.txt ./
COPY ./db_initializer/ ./db_initializer
RUN pip install -r requirements.txt
RUN pip install -r db_initializer/requirements.txt
RUN python3 db_initializer/initialize_db.py
#CMD [ "python3", "./data_parser.py"]
