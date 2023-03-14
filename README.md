# tinkoff-getman-app

# docker notes

To run the app generate cert, or provide your own via overriding.

```
mkdir -p ./data/certs
openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout ./data/certs/priv.key -out ./data/certs/fullchain.cer -subj "/C=XX/ST=Unknown/L=Unknown/O=Unknown/OU=Unknown/CN=localhost"
openssl dhparam -out ./data/certs/dh.pem 8162
```

In order to Lombok start working without any problems you need to install a plugin in your IDE
Do next actions:
  Go to File > Settings > Plugins
  Click on Browse repositories...
  Search for Lombok Plugin
  Click on Install plugin
  Restart IntelliJ IDEA
## How to make a request for an external API

For example, we want to check GET `/fact` endpoint with `max_lenth=100` queries.
We need to manually compose a POST request (e.g. in Postman) with JSON body of the form:
URI of checked resourse: https://catfact.ninja/fact?max_length=100
```json
{
	"httpVersion": "1.1", 
	"method": "GET",
	"scheme": "https",
	"host": "catfact.ninja",
	"port": 80,
	"path": "/fact",
	"headers": [],
	"query": [["max_length", "100"]],
	"payload": []
}
```
After the request, we will receive a response of the form:

```json
{
    "id": null,
    "requestSnapshot": null,
    "executed_at": 1677267936224,
    "closed_a": 1677267937792,
    "status": {
        "code": 200,
        "text": "200 OK"
    },
    "headers": {
        "Server": [
            "nginx"
        ],
        "Date": [
            "Fri, 24 Feb 2023 19:45:38 GMT"
        ],
        "Content-Type": [
            "application/json"
        ],
        "Transfer-Encoding": [
            "chunked"
        ],
        "Connection": [
            "keep-alive"
        ],
        "Vary": [
            "Accept-Encoding"
        ],
        "Cache-Control": [
            "no-cache, private"
        ],
        "X-Ratelimit-Limit": [
            "100"
        ],
        "X-Ratelimit-Remaining": [
            "99"
        ],
        "Access-Control-Allow-Origin": [
            "*"
        ],
        "Set-Cookie": [
            "XSRF-TOKEN=eyJpdiI6Ilo1MVhEMS9EVi9Id3NhLzdMSXUvSlE9PSIsInZhbHVlIjoiV2liUy9EOS9UaG9FcWNZQ1FxRXVNd095Z0pBdlE1ZUwyVVhwKzNKb3U2czBBNG5ySmR5eHFrdVcxN2gxUFBSTHNBWVFJL05yenFOeHhPRG5vdkVQNUl4b3NneW1qcW52K3M0aGRLbkMxRmxuRVFSZmNSTjlFOVhNZkJ1N1ZiL3YiLCJtYWMiOiI3YmM3YjdkNzVmYTc0NDYwYzY2YWE1MjY2MWI4N2UyZGE2YmE4ZWNkYzljOTUxN2ZjNDEwY2NkN2NlN2IxODI1IiwidGFnIjoiIn0%3D; expires=Fri, 24-Feb-2023 21:45:38 GMT; path=/; samesite=lax",
            "catfacts_session=eyJpdiI6Ik80RFZDT0d1cnhEYURFam1UTGxadUE9PSIsInZhbHVlIjoiMDVmZFBpVjlkRnZONXZMQlJaYzhzNWZmKy9acU1TQmRsd3BxOHRwYTJpYzltU2dTQVFzc05Sa3AyaXNmOFFjRjlsc3J3dHlQUUtHVFBFTUY1RlRwS1BZQWFkVlAvalZmdis3ZUJrdlRXQkVzd3lhYWk4dXZwN2VtRlRkMGFrS24iLCJtYWMiOiJmOGQ0YjZhYjNkMTU2NjI1MGIxYmZhMDI1MDc4ZTY5ZTFjMGFlMmMzYjdiYTdkMGEwZTcxZTY5YzNhZDk2YTQ1IiwidGFnIjoiIn0%3D; expires=Fri, 24-Feb-2023 21:45:38 GMT; path=/; httponly; samesite=lax"
        ],
        "X-Frame-Options": [
            "SAMEORIGIN"
        ],
        "X-XSS-Protection": [
            "1; mode=block"
        ],
        "X-Content-Type-Options": [
            "nosniff"
        ]
    },
    "payload": {
        "type": "application/json",
        "data": "{\"fact\":\"Tylenol and chocolate are both poisionous to cats.\",\"length\":50}"
    }
}
```
As we can see, the response contains the `Status`, `Headers` and `Payloads`, which contains json with both the type of data returned and the data directly returned (e.g. an interesting fact about cats)
