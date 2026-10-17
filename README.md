# sudoku

## Build

Requires Java 25 and Maven 3.9 or newer.

- `mvn verify` runs the tests, JaCoCo coverage and the Spotless format check.
- `mvn spotless:apply` fixes the formatting when the check fails.

Jenkins on the kvm-server cluster builds every branch and sends the results to SonarQube.
