@echo off
title E-Voting dApp Server - Assignment 18 Mini Project 3
cls
echo ================================================================================
echo  JSPM BSIOTR PUNE - DEPARTMENT OF COMPUTER ENGINEERING
echo  LABORATORY PRACTICE III (410246)
echo  ASSIGNMENT NO : 18 - MINI PROJECT 3 : DECENTRALIZED E-VOTING DAPP
echo ================================================================================
echo.
echo Starting E-Voting dApp on http://localhost:3000 ...
start "" "http://localhost:3000"
node dapp_server.js
pause

