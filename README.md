This repo is created to reproduce this potential [bug](https://github.com/jacoco/jacoco/issues/1290) found in JaCoCo.

* JaCoCo version:  0.8.7
* Operating system: MacOS
* Steps: When using Jacoco tool for reporting code coverage in my Android project with Kotlin version of `1.6.10`, the Jacoco reports for viewModelScope.launch { ... } block is always **NOT fully covered**.

### Expected behaviour
`viewModelScope.launch { ... }` should be marked as fully covered by jacoco for Kotlin 1.6.10.

### Actual behaviour
The report says 2 of 3 branches missed for `viewModelScope.launch { ... }` block.

<img width="776" alt="image" src="https://user-images.githubusercontent.com/48440224/156380466-6bfd8f14-da4e-4465-9113-2c7f1c534ada.png">
