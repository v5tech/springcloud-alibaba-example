#!/usr/bin/env bash
n=1
while [ $n -le 10 ]
do
    echo `curl -s http://localhost:18084/gateway-route/echo/$n`
    # echo `curl -s http://localhost:18084/gateway-route/divide?a=$n&b=$n`
    let n++
done