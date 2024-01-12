#!/bin/bash
set -e

mongosh <<EOF
use admin 
db.createUser(
  {
    user: "${MONGO_USER}",
    pwd: "${MONGO_PASS}",
    roles: [ { role: "userAdminAnyDatabase", db: "admin" }, "readWriteAnyDatabase" ]
  }
)

use ${MONGO_DB_NAME}
db.createCollection("xdata")
db.xdata.insert({"value": "999"})
EOF