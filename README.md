This repo is created to reproduce this potential [bug](https://github.com/jacoco/jacoco/issues/1290) found in JaCoCo. This project includes 2 modules named `app` and `coroutinelaunch`.

* JaCoCo version:  0.8.7
* Operating system: MacOS
* Steps: When using Jacoco tool for reporting code coverage in my Android project with Kotlin version of `1.6.10`, the Jacoco reports for viewModelScope.launch { ... } block is always **NOT fully covered**.

### Expected behaviour
`viewModelScope.launch { ... }` should be marked as fully covered by jacoco for Kotlin 1.6.10.

### Actual behaviour
The report says 2 of 3 branches missed for `viewModelScope.launch { ... }` block.

<img width="700" alt="image" src="https://user-images.githubusercontent.com/48440224/156383320-7ea43fab-24f3-42b2-a603-996737af5875.png">

## Steps to produce a Jacoco report
-  `git clone https://github.com/AnneJ17/jacoco-miss-launch`
- `cd jacoco-miss-launch`
- `./gradlew ":coroutinelaunch:jacocoDebugTestReport"`
- `open coroutinelaunch/build/reports/jacoco/jacocoDebugTestReport/html/index.html .`
