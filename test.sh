#!/bin/bash -e
export SCRIPT_HOME=$( cd "$( dirname "$0" )" &> /dev/null && pwd )
rm -fR runtime mill-standalone versions.properties out sireum
curl -Lo sireum http://files.sireum.org/sireum
chmod +x sireum
$SCRIPT_HOME/sireum slang tipe --verbose -r -s library
curl -Lo mill-standalone http://files.sireum.org/mill-standalone
chmod +x mill-standalone
curl -Lo versions.properties https://raw.githubusercontent.com/sireum/kekinian/master/versions.properties
$SCRIPT_HOME/mill-standalone all runtime.library.shared.tests.test runtime.library.jvm.tests.test
if [ -x "$(command -v node)" ]; then
  $SCRIPT_HOME/mill-standalone runtime.library.js.tests.test
fi
$SCRIPT_HOME/mill-standalone jitPack --owner sireum --repo runtime --lib library || true