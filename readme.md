# Sireum Runtime Library

| Branch | macOS | Linux | Windows | JitPack |  
| :----: | :---: | :---: | :---: | :-----: | 
| release | [![Travis CI Status](https://travis-ci.org/sireum/runtime.svg?branch=release)](https://travis-ci.org/sireum/runtime) | [![Shippable Status](https://api.shippable.com/projects/569fb45b1895ca4474703965/badge?branch=release)](https://app.shippable.com/projects/569fb45b1895ca4474703965) |  [![Appveyor status](https://ci.appveyor.com/api/projects/status/je9df66d1gpr62rt/branch/release?svg=true)](https://ci.appveyor.com/project/robby-phd/runtime/branch/release) | [![](https://jitpack.io/v/org.sireum/runtime.svg)](https://jitpack.io/#org.sireum/runtime) |
| master | [![Travis CI Status](https://travis-ci.org/sireum/runtime.svg?branch=master)](https://travis-ci.org/sireum/runtime) | [![Shippable Status](https://api.shippable.com/projects/569fb45b1895ca4474703965/badge?branch=master)](https://app.shippable.com/projects/569fb45b1895ca4474703965) | [![Appveyor status](https://ci.appveyor.com/api/projects/status/je9df66d1gpr62rt/branch/master?svg=true)](https://ci.appveyor.com/project/robby-phd/runtime/branch/master) | [Link](https://jitpack.io/#org.sireum/runtime) |

This repository holds the [Slang](https://github.com/sireum/slang) runtime library.

## Testing

* **macOS/Linux**

  ```bash
  bin/init.sh
  bin/build.cmd test test-js
  ```
  
* **Windows**

  ```cmd
  bin\init.bat
  bin\build.cmd test test-js
  ```
