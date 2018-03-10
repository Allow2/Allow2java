# Allow2java

todo: Port https://github.com/Allow2/Allow2node to a Java library
(Note: there is a prototype full offline C library we should consider after the base port. Just keep a note here to do so).

Oh, and we should build this library to be easy to install via a package manager if applicable/appropriate. (Maven?)

refer to https://github.com/Allow2/Allow2.github.io/wiki for more details.

# Curl examples

to get you started.

These are based on a device token of: **jJ5GOIaJ028Ywt6K**

Make your own device tokens here: https://developer.allow2.com


## 1. Pairing

First, pair with Allow2 (this is using the username/password method):

```sh
curl -H "Content-Type: application/json" -X POST -d @- https://api.allow2.com/api/pairDevice << EOF
{
   "user": "*EMAIL*",
   "pass":"*PASSWORD*",
   "deviceToken": "jJ5GOIaJ028Ywt6K",
   "name":"Test Device 1"
}
EOF
```

this returns a payload with information that you pass back to the app to persist for future use against the service:
```yaml
{
  "status":"success",
  "pairId":12345,                                     # PAIR_ID
  "token":"6742b233-de46-4c86-2ac9-7b9e5729f999",     # PAIR_TOKEN
  "name":"Test Device 1",
  "userId": 1234,                                     # USER_ID
  "children":[
    { "id":682, "name":"Bob" },                       # CHILD_ID
    { "id":691, "name":"Grace" },
    { "id":1795,"name":"Fred"}
  ]
}
```

## 2. Checking and Logging Usage

Make a call like this every 20 seconds or so to check and log usage.

This example checks if the given child is currently allowed to be gaming (activity "3").
The list of activities and their ids is available on the developer portal: https://developer.allow2.com/ltr/activities

```sh
curl -H "Content-Type: application/json" -X POST -d @- https://service.allow2.com/serviceapi/check << EOF
{
    "userId": *USER_ID*,
    "pairId": *PAIR_ID*,
    "pairToken": "*PAIR_TOKEN*",
    "deviceToken": "jJ5GOIaJ028Ywt6K",
    "tz": "Australia/Brisbane",
    "childId": *CHILD_ID*,
    "activities": [{
      "id": 3,
      "log": true
    }]
}
EOF
```

this returns a payload with success/failure and permissions/blocks/limits/etc.

For allowed usage you can simply check for "allowed":
```yaml
{
   "allowed": true,
   ...
}
```

If not allowed, interrogate the payload to work out why:
```yaml
{
   "allowed": false,
   ...
}
```

## 3. Checking Status

If you want to check if the device is still connected (in response to a user request perhaps). You can optionally use this endpoint:

**TBC**
