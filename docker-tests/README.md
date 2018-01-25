# CWDS CASE MANAGEMENT API TESTS

This image can be used to run a set of tests on CASE MANAGEMENT API 


# Configuration
## Application Configuration Parameters
- **CASE_MANAGEMENT_API_URL** -- URL of the case-management-api instance to test. 
**Required:** true.
**Example:** "http://tpt3.dev.cwds.io:8089/" 
- **TEST_TYPE** -- test type to be started in the container. 
**Required:** false.
**Possible values:** smoke; integration.
**Default value:** smoke.
- **DB_CMS_JDBC_URL** -- JDBC URL of the DB2 instance the case-management-api under test is using.
**Required:** true, when TEST_TYPE=integration.
**Example:** "jdbc:db2://localhost:50000/DB0TDEV"
- **DB_CMS_SCHEMA** -- Schema name in the DB2 instance. 
**Required:** true, when TEST_TYPE=integration.
**Example:** "CWSINT"
- **DB_CMS_USER** -- DB2 instance username.
**Required:** true, when TEST_TYPE=integration.
**Example:** "user"
- **DB_CMS_PASSWORD** -- DB2 instance password.
**Required:** true, when TEST_TYPE=integration. 
**Example:** "pass"

# Example commands to run:
## Run smoke test example
docker run -e "CASE_MANAGEMENT_API_URL=http://tpt3.dev.cwds.io:8089/" -e "TEST_TYPE=smoke" -it cwds/case-management-api-tests

## Run integration test example
docker run -e "CASE_MANAGEMENT_API_URL=http://10.10.1.253:8080/" \
	-e "TEST_TYPE=integration" \
	-e "DB_CMS_JDBC_URL=jdbc:db2://10.10.1.253:50000/DB0TDEV" \
	-e "DB_CMS_SCHEMA=CWSINT" \
	-e "DB_CMS_USER=db2inst1" \
	-e "DB_CMS_PASSWORD=db2inst1" \
	-it cwds/case-management-api-tests

# Result
The container returns 0 if tests finished successfully and 1 if tests failed.
