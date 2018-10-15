#!/bin/bash -e
export SCRIPT_HOME=$( cd "$( dirname "$0" )" &> /dev/null && pwd )
$SCRIPT_HOME/prelude.sh
$SCRIPT_HOME/sireum slang tipe --verbose -r -s library
$SCRIPT_HOME/mill-standalone all runtime.library.shared.tests.test runtime.library.jvm.tests.test
if [ -x "$(command -v node)" ]; then
  $SCRIPT_HOME/mill-standalone runtime.library.js.tests.test
fi