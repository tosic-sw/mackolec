from schedule import every, run_pending
import time

from devices_util import *
from http_util import *

# KREIRATI FUNCKIJE ZA 3 PREDEFINISANE MACE
# IZMENITI IMENA PY FAJLOVA

def main():

    status_code, token, cookie = login("devices", "nisamadmin")

    if status_code != 200:
        print("QUIT")
        return

    def cat1(): #def generate_message(field_name, jmbm, value_min, value_max):
        request_body = generate_messages_from_devices()

        status = post_data_list(token, cookie, request_body)
        print("Server responded with status " + str(status))

    def cat2():
        request_body = generate_messages_from_devices()

        status = post_data_list(token, cookie, request_body)
        print("Server responded with status " + str(status))

    def cat3(): 
        request_body = generate_messages_from_devices()

        status = post_data_list(token, cookie, request_body)
        print("Server responded with status " + str(status))

    every(10).seconds.do(job)

    while True:
        run_pending()
        time.sleep(1)


if __name__ == "__main__":
    main()
