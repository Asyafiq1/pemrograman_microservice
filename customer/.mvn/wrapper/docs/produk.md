# customer API

## Create customer

Endpoint: POST /api/customer

request body:
'''json
{
"name":"customer a",
"email":"23000",
"address":"customer a customer terbaik"
}

response body (success):
'''json
{
"msg":"customer berhasil ditambahkan",
}

response body (failed):
'''json
{
"msg":"customer gagal ditambahkan",
}
