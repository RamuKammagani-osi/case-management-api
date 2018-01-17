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
  echo "Executing the Integration Test..."
  TEST_CLASS=gov.ca.cwds.cm.IntegrationTestSuite
else
  echo "Unknown TEST_TYPE: '$TEST_TYPE'"
  exit 1
fi

echo "Starting tests: "
echo "CASE_MANAGEMENT_API_URL = '$CASE_MANAGEMENT_API_URL'"
echo "TEST_TYPE = '$TEST_TYPE'"

java ${JAVA_OPT} -Dapi.url="${CASE_MANAGEMENT_API_URL}" -cp /opt/case-management-api-tests/resources:case-management-api-tests.jar org.junit.runner.JUnitCore ${TEST_CLASS}
