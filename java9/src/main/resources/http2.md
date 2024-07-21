# Http Client

Http client has been added to java 9 and standardized in java 11. It supports http/2 so what's the difference between http 1.1 and 2 ?

Well http 2 is the same as http 1 :
- Request/response based
- Get/Post/Put/etc. methods

Differences :
- http 2 is binary based : as http 1 request was in a plain text format, now http 2 is in binary format
- mandatory TLS : in http 1 you have the choice to use secure connections or not but in http 2 you don't have the choice anymore
- Multiplexing over single TCP connection (streams) : you cannot serve multiple requests/responses anymore in the same tcp connection. in http 1 tcp connections was reused but it's not possible with http2
- Server push capability : now it's possible to push resources from server to client instead of waiting for a request to respond

all these features are supported by Http Client