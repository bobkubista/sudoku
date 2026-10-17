# sudoku

## Build

Requires Java 25 and Maven 3.9 or newer.

- `mvn verify` runs the tests, JaCoCo coverage and the Spotless format check.
- `mvn spotless:apply` fixes the formatting when the check fails.

Jenkins on the kvm-server cluster builds every branch; builds of `master` are also analysed in SonarQube (Community Edition keeps one analysis per project, so feature branches are not sent).
