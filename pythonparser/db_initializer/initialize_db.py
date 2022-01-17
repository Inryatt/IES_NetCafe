import base64
import json
import requests

def main():
    api_url = 'http://172.18.0.5:8080/api/'
    #api_url = 'http://api:8080/api/'

    with open('user_list.json', 'rb') as user_list:
        users = json.loads(user_list.read())
        for user in users:
            print(f'{user = }')
            requests.post(api_url + 'users', json=user)

    with open('software_list.json', 'rb') as software_list:
        softwares = json.loads(software_list.read())
        for software in softwares:
            print(f'{software = }')
            requests.post(api_url + 'softwares', json=software)

    with open('location_list.json', 'rb') as location_list:
        locations = json.loads(location_list.read())
        for location in locations:
            with open(location['map'], 'rb') as img_map:
                # TODO: check if the images show up as expected in the web app
                img = img_map.read()
                img64 = str(base64.b64encode(img))
                location['map'] = img64[2:-1]
                requests.post(api_url + 'locations', json=location)

    with open('machine_list.json', 'rb') as machine_list:
        machines = json.loads(machine_list.read())
        for machine in machines:
            print(f'{machine = }')
            requests.post(api_url + 'machines', json=machine)


if __name__ == '__main__':
    main()
