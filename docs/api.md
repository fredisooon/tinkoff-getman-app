# API overview

> This is a draft version

## Version

Current API version is `0.0.0-dev.1`.

## API access

* Every API request is made with `POST` or `GET` request.
* Every request includes [schema version](#schema-version).
* Every private API requires [API token header](#token-header).
* All APIs are accessible according to [API URL Semantic](#url-semantic).

### URL Semantic

API endpoints are accessible via following forms:

* `<baseurl>/api/v/<version>/<endpoint>`
* `<baseurl>/api/<endpoint>`

Where:

* `<baseurl>` is base API URL.
* `<version>` is [schema version](#schema-version).
  * If schema is omitted from URL it must be supplied via other methods (see [schema version](#schema-version)).
* `<endpoint>` is endpoint path.

### Token header

Some APIs are must be private and therefore requires token.
Token is supplied via `HTTP` header `X-API-Token`.

### Schema version

Because schema may change in future, server **must** keep track of client's
schema version.

Schema version is follows [semver](https://semver.org/) semantic.

Schema version must be supplied via one of the following methods:

* Header `X-API-Schema-Version`.
* Query parameter `x-api-schema-version`.
* Distinguishable endpoint URL (see [URL Semantic](#url-semantic)).

If server's schema is not compatible with user's server must respond with
`HTTP` status [`406 Not Acceptable`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/406).

## Objects

### `Request`

Request is an object that abstracts single request and it's parameters.

Example:

```json5
{
	"http_version": "1.1",
	"method": "GET",
	"scheme": "http",
	"host": "example.com",
	"port": 80,
	"path": "",
	"headers": null, // Headers object or null
	"query": null, // Query object or null
	"payload": null, // Payload object or null
}
```

Where:

* `http_version` is HTTP protocol version, e.g. `0.9`, `1.0`, and so on.
* `method` is HTTP [request method](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods),
	e.g. `GET` or `POST`. (**must** be uppercase)
* `scheme` is HTTP scheme, e.g. `http` or `https` (**must** be lowercase).
* `host` is request target host.
* `port` is request target port.
* `path` is request URL path.
* `headers` is request HTTP headers (see [Headers](#headers) object).
* `query` is request URL query (see [Query](#query) object).
* `payload` is request payload (see [Payload](#payload) object).
  * **Must** be `null` for unsupported methods (e.g. `GET` or `HEAD`).

### `Headers`

Headers is an object that describes [Request](#request)'s HTTP headers part.

Essentially it's an `Array` with 2-items `Array`s of `String`s.

**Note**: Headers are allowed to duplicate, server must execute request with
provided headers in the same order and count.

Example:

```json5
[
	["X-Single-Header", "Header value 1"],
	["X-Multiple-Same-Name-Headers", "Header value 1"],
	["X-Multiple-Same-Name-Headers", "Header value 2"]
]
```

### `Query`

Query is an object that describes [Request](#request)'s URL query part.

Essentially it's an `Array` with 2-items `Array`s of `String`s.

**Note**: Query elements are allowed to duplicate, server must execute request
with provided queries in the same order and count.

Example:

```json5
[
	["query-1", "value"],
	["same-name-query", "value_1"],
	["same-name-query", "value_2"],
]
```

This example corresponds to the following query:
```query
?query-1=value&same-name-query=value_1&same-name-query=value_2
```

### `Payload`

Payload is an object that describes [Request](#request)'s HTTP payload part.

Server **may** parse payload's data and provide insights about it, e.g.
warnings, such as inconsistency of payload type and corresponding content type
header.

Example:

```json5
{
	"type": "plain",
	"data": "RGF0YSBpbiBCYXNlNjQ="
}
```

Where:

* `type` is payload type.
  * `plain` payload must be decoded added **as-is** to request.
    With this type payload's `data` is `Base64` encoded.
* `data` is payload's body.

> Editor note: in future versions there maybe more types of payloads, e.g.
> request streaming.
