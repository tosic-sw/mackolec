from schedule import every, run_pending
import time

from events_util import *
from http_util import *

def main():

    def cat1_hart(): 
        field_name = "heartBeat"
        request_body = generate_message("0001")
        print(request_body)

        status = post_data(request_body, field_name)
        print("Server responded with status " + str(status))

    def cat2_oxygen(): 
        field_name = "oxygenLevel"
        request_body = generate_message("0002", field_name, 95, 98)
        print(request_body)

        status = post_data(request_body, field_name)
        print("Server responded with status " + str(status))

    def cat3_infusion():
        field_name = "fluidFlow"
        request_body = generate_message("0003", field_name, 4, 10)
        print(request_body)

        status = post_data(request_body, field_name)
        print("Server responded with status " + str(status))


    def cat4_hart_problem(): 
        field_name = "heartBeat"
        request_body = generate_message("0004")
        print(request_body)
        
        status = post_data(request_body, field_name)
        print("Server responded with status " + str(status))

    def cat5_oxygen_problem(): 
        field_name = "oxygenLevel"
        request_body = generate_message("0005", field_name, 92, 93)
        print(request_body)
        
        status = post_data(request_body, field_name)
        print("Server responded with status " + str(status))

    every(2).seconds.do(cat1_hart)
    # every(5).seconds.do(cat2_oxygen)
    every(5).seconds.do(cat3_infusion)
    every(1).seconds.do(cat4_hart_problem)
    every(5).seconds.do(cat5_oxygen_problem)

    while True:
        run_pending()
        time.sleep(1)


if __name__ == "__main__":
    main()
