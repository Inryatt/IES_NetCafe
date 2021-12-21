FROM python:3
WORKDIR /usr/parser/src
COPY ./data_parser.py ./
COPY ./requirements.txt ./
COPY ./db_initializer ./db_initializer
RUN pip install -r requirements.txt
CMD ["python3", db_initializer/initialize_db.py]
#CMD [ "python3", "./data_parser.py"]