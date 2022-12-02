Invoke-WebRequest -Uri "https://raw.githubusercontent.com/sireum/kekinian/master/bin/init.bat" -OutFile "$PSScriptRoot\prelude.bat"
& "$PSScriptRoot\prelude.bat"