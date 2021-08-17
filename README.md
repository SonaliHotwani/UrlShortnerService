# UrlShortnerService

# Running the app locally
1. `./gradlew clean build`
2. `./gradlew run`

# Request Sample
 1. Endpoint: http://localhost:8080/shorten/url
 2. Body: 
 ```
{
	"url": "http://localhost:2121/andbdsgsddgdsufdf/newResource/sdgdsugdsdfedfe"
}
```

3. Validations are present on the Url structure, if the input url is not in a proper format, bad request response will be present

# Sample Request

1. Valid scenario

- Request
 ```
{
	"url": "http://localhost:2121/andbdsgsddgdsufdf/newResource/sdgdsugdsdfedfe"
}
```
2. Invalid Scenario

- Request
 ```
{
	"url": "Dummy-Url-Not-In-Proper-Format"
}
```

