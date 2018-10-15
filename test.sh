#!/bin/bash -e
export SCRIPT_HOME=$( cd "$( dirname "$0" )" &> /dev/null && pwd )
cd $SCRIPT_HOME
./prelude.sh
./sireum slang tipe --verbose -r -s library
./mill-standalone all runtime.library.shared.tests.test runtime.library.jvm.tests.test
if [ -x "$(command -v node)" ]; then
  ./mill-standalone runtime.library.js.tests.test
fi