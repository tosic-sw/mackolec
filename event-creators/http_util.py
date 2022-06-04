import requests
import json

def post_data(request_body, endpoint):
    print(40 * "-" + "   POST_DATA   " + 40 * "-" + "\n")
    print(request_body)
    print(40 * "-" + "   ---------   " + 40 * "-" + "\n")
    
    res = requests.post(
        "http://localhost:8080/api/" + endpoint,
        json=request_body,
    )

    return res.status_code