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
----

API endpoints are accessible via following forms:

* `<baseurl>/api/v/<version>/<endpoint>`
* `<baseurl>/api/<endpoint>`

Where:

* `<baseurl>` is base API URL.
* `<version>` is [schema version](#schema-version).
  * If schema is omitted from URL it must be supplied via other methods (see [schema version](#schema-version)).
* `<endpoint>` is endpoint path.

### Token header
----

Some APIs are must be private and therefore requires token.
Token is supplied via `HTTP` header `X-API-Token`.

### Schema version
----

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

### `ID<T, Parent>`
----

ID is an object that contains relatively unique object and parent relatively
unique ID. Together they form globally unique identity.

Type parameter `T` specifies type of the object this ID is created for.
It can be nullable. By default it is `Any?`.

Type parameter `Parent` specifies type of parent object. It can be nullable.
By default it is `Any?`.


Example:
```json5
{
	"id": 1,
	"parent": null
}
```

Where:

* `id` is relatively unique local ID of the object.
* `parent` is relatively unique local ID of the parent object or `null`.

For example for [`Request`](#request) parent ID is [`Workspace`](#workspace) ID.

### `Request`
----

Request is an object that abstracts single request and it's parameters.

Example:

```json5
{
	"id": { "id": 1, "parent": 0 }, // ID<Request, Workspace> object
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

* `id` is [ID](#idt-parent)\<[Request](#request), [Workspace](#workspace)\>
  object of request.
* `http_version` is HTTP protocol version, e.g. `0.9`, `1.0`, and so on.
* `method` is HTTP [request method](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods),
	e.g. `GET` or `POST`. (**must** be uppercase)
* `scheme` is HTTP scheme, e.g. `http` or `https`. (**must** be lowercase)
* `host` is request target host.
* `port` is request target port.
* `path` is request URL path.
* `headers` is request HTTP headers (see [Headers](#headers) object).
* `query` is request URL query (see [Query](#query) object).
* `payload` is request payload (see [Payload](#payload) object).
  * **Must** be `null` for unsupported methods (e.g. `GET` or `HEAD`).

### `Headers`
----

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
----

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

### `RequestSnapshot`
----

Request snapshot is an unmodifiable copy of [Request](#request).

It's used for referencing exact parameters used to perform [Request](#request)
in the [Response](#response) object.

It has following differences from [Request](#request):

* `id` is [ID](#idt-parent)\<[RequestSnapshot](#requestsnapshot), void?\> object
  of request snapshot where `parent` is **always** null.
  > Editor note: this is done because request snapshot is used to inspect
  > parameters and is read-only by design, so it doesn't need to belong to
  > anything.

### `Payload`
----

Payload is an object that describes HTTP payload part.

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

* `type` is `enum` payload type.
  * `plain`:
    * With this type payload's `data` is `Base64` encoded.
	* (if request) Payload must be decoded and added **as-is** to request.
* `data` is payload's body.

> Editor note: in future versions there maybe more types of payloads, e.g.
> request/response streaming.

### `Response`
----

Response is an object that describes performed [Request](#request) response.

It's using [RequestSnapshot](#requestsnapshot) to preserve original
[Request](#request) parameters (because [Request](#request) is a mutable preset).

Example:

```json5
{
	"id": { "id": 1, "parent": null }, // ID<Response, ResponseSet?> object
	"request_snapshot": 1,
	"executed_at": 0,
	"closed_at": null,
	"status": {
		"code": 200,
		"text": "OK"
	},
	"headers": null, // Headers object or null
	"payload": null // Payload object or null
}
```

Where:

* `id` is
  [ID](#idt-parent)\<[Response](#response), [ResponseSet](#responseset)?\>
  object of response.
  * If response doesn't belong to [ResponseSet](#responseset) `parent` will be
    `null`.
* `request_snapshot` is an `int` ID of [RequestSnapshot](#requestsnapshot)
  object.
* `executed_at` is UNIX timestamp in milliseconds when request begun to execute.
* `closed_at` is UNIX timestamp in milliseconds when request closed connection
  or `null` if request is still in progress (e.g. downloading body, or
  connection is still alive for some reason).
* `status` is `null` if network error is occurred otherwise it's an `Object`
  where:
  * `code` is an `int` HTTP response code.
  * `text` is a `String` HTTP response description as provided by the server.
* `headers` is response HTTP headers (see [Headers](#headers) object) or `null`
  if network error is occurred.
* `payload` is response payload (see [Payload](#payload) object).
  * This field can be `null` when server responded without body, network error
    is occurred, payload is still downloading or other reasons that makes
	payload unavailable.
* (optional) `error` is an `Object` where:
  * `code` is an `int` code of error.
  * `text` is `String` indicating network error text.


### `ResponseSet`
----

> Editor note: unfinished part. 

### `Workspace`
----

Workspace is an object that holds multiple [`Request`](#request) objects and
sub-workspaces, allowing for easy objects grouping.

Example:

```json5
{
	"id": { "id": 1, "parent": 0 }, // ID<Workspace, Workspace> object
	"name": "Workspace name",
	"description": "Workspace description",
	"requests": [], // Array<int>
	"workspaces:": [] // Array<int>
}
```

Where:

* `id` is [ID](#idt-parent)\<[Workspace](#workspace), [Workspace](#workspace)\>
  object of workspace.
* `name` is a workspace name.
* `description` is a workspace description.
* `requests` is an `Array` of `int` IDs of [`Request`](#request) objects within
  this workspace.
* `workspaces` is an `Array` of `int` IDs of children [Workspace](#workspace)s
  within this workspace.

#### Special workspaces
----

There are exists some predefined virtual workspaces with different purposes.
Such workspaces have negative IDs (with exception of
[Root Workspace](#root-workspace)).

It's **impossible to modify or delete** special workspaces attributes, server
must respond with corresponding error if such attempt is made.
It's still possible to move objects in and out of such workspaces.

##### Root Workspace
----

Root workspace is default workspace that is used as placeholder parent
workspace.

Root workspace have ID of **`0`**.

Root workspace also is the only technically recursive workspace (because it
have parent ID `0`, which is itself).

Root workspace can host **only** other workspaces. It's an error to place
[Request](#request) within root workspace.

##### Recycle Bin
----

Recycle Bin is a special predefined workspace that is used to temporarily store
"recycled" objects. Such objects are permanently deleted after some time
(server defined).

Recycle Bin workspace have ID of **`-100`**.

## Endpoints

URLs are corresponds to `endpoint` in  [URL semantic](#url-semantic).

Some properties of bodies can be explicitly set to `null`, therefore server
must distinguish between **omitted** property and property with
**`null` value**.

Schema allows moving objects to [Recycle Bin](#recycle-bin)'s subworkspaces,
but there could be condition when target workspace could be deleted while moving
objects.
In such situation it's server responsibility to fix this by deleting now orphan
objects.

> Editor note: it's good idea to implement deletion locks and queue to prevent
> this.

**General exceptions**:

* If server cannot recognize endpoint it must respond with
   [`404 Not Found`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404).
* If request is missing some required parameters server must respond with 
  [`400 Bad Request`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400).
* If server recognize endpoint, but requested method is not supported server
  must respond with
  [405 Method Not Allowed](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/405).
* For `GET`, `PUT` and `DELETE` requests:
  * If there is no such object server must respond with
    [`404 Not Found`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404).
* For `POST` and `PUT` requests:
  * If server cannot recognize body or body is not conform to specs server must
    respond with 
    [`400 Bad Request`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400).
  * If server exhausted available storage quota for the data it must respond
    with
    [`507 Insufficient Storage`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/507).

### Request
----

#### `GET` `/request/<id>`
----

Retrieve existing [`Request`](#request) object from the server.

**URI parameters**:

* `id` - ID of the requested [`Request`](#request) object.

**Returns**:

* [`Request`](#request) - requested object.

#### `PUT` `/request/<id>`
----

Partially update existing [`Request`](#request) object.

**URI parameters**:

* `id` - ID of the [`Request`](#request) object to update.

**Body**:

* [`Request`](#request)-like object with some properties **omitted**.

**Returns**:

* [`Request`](#request) - updated object.

#### `POST` `/request`
----

Creates new [`Request`](#request) object and stores it.

**Query parameters**:

* `workspace` is `int` ID of the [`Workspace`](#workspace) to place this object
  to.

**Body**:

* [`Request`](#request) object.

**Returns**:

* [ID](#idt-parent)\<[Request](#request), [Workspace](#workspace)\> object - ID
  of created object.

**Exceptions**:

* [`Request`](#request) cannot be created within
  [Root Workspace](#root-workspace) or [Recycle Bin](#recycle-bin), server
  must respond with
  [`400 Bad Request`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400)
  if such attempt is made.

#### `DELETE` `/request/<id>`
----

Deletes existing [`Request`](#request) object.

**URI parameters**:

* `id` - ID of the [`Request`](#request) object to delete.

**Returns**:

* `bool` - whether object was deleted or not.

#### `POST` `/request/<id>/move`
----

Moves existing [`Request`](#request) object to the other
[`Workspace`](#workspace).

**URI parameters**:

* `id` - ID of the [`Request`](#request) object to move.

**Body**:

* `Object` where:
  * `workspace` is `int` ID of [`Workspace`](#workspace) where to move object.

**Returns**:

* `bool` - whether object was moved or not.

**Exceptions**:

* [`Request`](#request) cannot be moved to
  [Root Workspace](#root-workspace), server must respond with
  [`400 Bad Request`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400)
  if such attempt is made.


#### `POST` `/request/<id>/execute`
----

Executes [`Request`](#request) preset on the server and returns
[`Response`](#response) ID.

Server must keep connection alive while performing request and respond as soon
as it will receive HTTP status and headers.

If network error is occurred while performing request, server still respond as
usual, because [`Response`](#response) contains all related information.

Response's `payload` can be `null` while it's still downloading.

**URI parameters**:

* `id` - ID of the [`Request`](#request) object to execute.

**Returns**:

* [ID](#idt-parent)\<[Response](#response), [ResponseSet](#responseset)?\>
  object - ID of created response.

### Workspace
----

#### `GET` `/workspace/<id>`
----

Retrieve existing [`Workspace`](#workspace) object from the server.

**URI parameters**:

* `id` - ID of the requested [`Workspace`](#workspace) object.

**Returns**:

* [`Workspace`](#workspace) - requested object.

#### `PUT` `/workspace/<id>`
----

Partially update existing [`Workspace`](#workspace) object.

**Note**: this method cannot edit children of workspace. It is done via move
methods.

**URI parameters**:

* `id` - ID of the [`Workspace`](#workspace) object to update.

**Body**:

* [`Workspace`](#workspace)-like object with some properties **omitted**,
  excluding `requests` and `workspaces` properties.

**Returns**:

* [`Workspace`](#workspace) - updated object.

#### `POST` `/workspace`
----

Creates new [`Workspace`](#workspace) object and stores it.

**Query parameters**:

* (optional, default) `workspace` is `int` ID of the [`Workspace`](#workspace)
  to place this object to.
  * Default value is `0` ([Root Workspace](#root-workspace)).

**Body**:

* [`Workspace`](#workspace) object.

**Returns**:

* [`ID`](#id) object - ID of created object.

#### `DELETE` `/workspace/<id>`
----

Deletes existing [`Workspace`](#workspace) object.

**Query parameters**:

* (optional, default) `cascade` - Cascade deletion of children objects.
  * Default value is `false`.

**URI parameters**:

* `id` - ID of the [`Workspace`](#workspace) object to delete.

**Returns**:

* `bool` - whether object was deleted or not.

**Exceptions**:

* [`Workspace`](#workspace) cannot be deleted with `cascade` set to `false` if
  it have children elements, server must respond with
  [`405 Method Not Allowed`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/405)
  if such attempt is made.


#### `POST` `/workspace/<id>/move`
----

Moves existing [`Workspace`](#workspace) object to the other
[`Workspace`](#workspace).

**URI parameters**:

* `id` - ID of the [`Workspace`](#workspace) object to move.

**Body**:

* `Object` where:
  * `workspace` is `int` ID of [`Workspace`](#workspace) where to move object.

### Mass move
----

#### `POST` `/move`
----

Mass move objects to target destinations. This method allows simultaneous bulk
movement of different objects to different destinations.

**Note**: If the same object is requested to move to different destinations, it
must be moved to the last defined destination. This means that from user
perspective it will look like object was moved multiple times according to
the order user requested.

**Note**: If some object is failed to move, server must revert **all** movement
operations made with this request. This ensures that either all operations are
competed successfully or none of them.

**Body**:

* `Array` of `Object`s where
  * `workspace` is `int` ID of the [`Workspace`](#workspace) to place this
    group of objects to.
  * (optional) `workspaces` is an `Array` of `int` IDs of
    [`Workspace`](#workspace) objects that must be moved.
  * (optional) `requests` is an `Array` of `int` IDs of [`Request`](#request)
    objects that must be moved.

**Returns**:

* `bool` - whether all objects were moved or not.

**Exceptions**:

* [`Request`](#request) cannot be moved to
  [Root Workspace](#root-workspace), server must respond with
  [`400 Bad Request`](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400)
  if such attempt is made.

**Example**:

Following request will move [`Workspace`](#workspace) `1` to
[Root Workspace](#root-workspace) and [`Workspace`](#workspace) `2` and
[`Request`](#request) `1` to [Recycle Bin](#recycle-bin).

```json
[
	{
		"workspace": -100,
		"workspaces": [1,2],
		"requests": [1],
	},
	{
		"workspace": 0,
		"workspaces": [1]
	}
]
```

### RequestSnapshot
----

#### `GET` `/request_snapshot/<id>`
----

Retrieve existing [`RequestSnapshot`](#requestsnapshot) object from the server.

**URI parameters**:

* `id` - ID of the requested [`RequestSnapshot`](#requestsnapshot) object.

**Returns**:

* [`RequestSnapshot`](#requestsnapshot) - requested object.

### Response
----

#### `GET` `/response/<id>`
----

Retrieve existing [`Response`](#response) object from the server.

**URI parameters**:

* `id` - ID of the requested [`Response`](#response) object.

**Returns**:

* [`Response`](#response) - requested object.

### `GET` `/response/<id>/await`

Awaits [Response](#response) within a given timeout, waiting for connection
being closed.
This method achieves "long-pooling" of [Response](#response) payload.

**Query parameters**:

* (optional, default) `timeout` - Await timeout in seconds.
  * Default value is `60` seconds.

**Returns**:

* `bool`:
  * `false` if await is timed out and connection is still open.
  * `true` if [Response](#response) is fully ready.
