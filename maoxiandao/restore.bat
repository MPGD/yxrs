@echo off
setlocal enabledelayedexpansion
for /f "delims=" %%i in ('dir /a:-d /b /s .\*.dlpcrack') do (
set "f=%%~dpni"
rem set "f=!f::=_!"
rem set "f=!f:\=_!"
copy /y "%%i" "!f!"
del "%%i"
)
pause

