#!/bin/bash

JAVA_OPT="-Xms128m -Xmx512m"

if ([ -z "$CASE_MANAGEMENT_API_URL" ]); then
  echo "CASE_MANAGEMENT_API_URL variable is required"
  exit 1
fi

if ([ -z "$TEST_TYPE" ]); then
  TEST_TYPE="smoke"
  echo "Default value is set: TEST_TYPE = smoke"
fi

if [[ "$TEST_TYPE" == "smoke" ]]; then
  echo "Executing the Smoke Test..."
  TEST_CLASS=gov.ca.cwds.cm.SmokeTestSuite
elif [[ "$TEST_TYPE" == "integration" ]]; then
  if ([ -z "$DB_CMS_JDBC_URL" ]); then
    echo "DB_CMS_JDBC_URL variable is required"
    exit 1
  fi
  if ([ -z "$DB_CMS_SCHEMA" ]); then
    echo "DB_CMS_SCHEMA variable is required"
    exit 1
  fi
  if ([ -z "$DB_CMS_USER" ]); then
    echo "DB_CMS_USER variable is required"
    exit 1
  fi
  if ([ -z "$DB_CMS_PASSWORD" ]); then
    echo "DB_CMS_PASSWORD variable is required"
    exit 1
  fi
  echo "Executing the Integration Test..."
  TEST_CLASS=gov.ca.cwds.cm.IntegrationTestSuite
else
  echo "Unknown TEST_TYPE: '$TEST_TYPE'"
  echo "Known types: smoke, integration"
  exit 1
fi

echo "Starting tests: "
echo "CASE_MANAGEMENT_API_URL = '$CASE_MANAGEMENT_API_URL'"
echo "TEST_TYPE = '$TEST_TYPE'"
echo "TEST_CLASS = '$TEST_CLASS'"
echo "DB_CMS_JDBC_URL = '$DB_CMS_JDBC_URL'"
echo "DB_CMS_SCHEMA = '$DB_CMS_SCHEMA'"
echo "DB_CMS_USER = '$DB_CMS_USER'"
echo "DB_CMS_PASSWORD = ********"

java ${JAVA_OPT} -Dapi.url="${CASE_MANAGEMENT_API_URL}" -cp /opt/case-management-api-tests/resources:case-management-api-tests.jar org.junit.runner.JUnitCore ${TEST_CLASS}
